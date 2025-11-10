package org.mythic_studios.dungeon.entity;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.TimeHelper;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.mythic_studios.dungeon.DungeonRunner;

import java.util.UUID;

public class MantisEntity extends GolemEntity implements Angerable {
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    protected static final TrackedData<Byte> IRON_GOLEM_FLAGS;
    private int attackTicksLeft;
    private static final UniformIntProvider ANGER_TIME_RANGE;
    private int angerTime;
    @Nullable
    private UUID angryAt;

    public MantisEntity(EntityType<? extends GolemEntity> entityType, World world) {
        super(entityType, world);
    }

    private final ServerBossBar bossBar = new ServerBossBar(Text.literal("Overworld Lord"),
            BossBar.Color.GREEN, BossBar.Style.NOTCHED_10);

    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(IRON_GOLEM_FLAGS, (byte)0);
    }

    @Override
    protected RegistryKey<LootTable> getLootTableId() {
        return RegistryKey.of(
                RegistryKeys.LOOT_TABLE,
                Identifier.of(DungeonRunner.MOD_ID, "entity/mantis")
        );
    }

    @Override
    protected boolean shouldDropLoot() {
        return true;
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new MeleeAttackGoal(this, 1.0F, true));
        this.goalSelector.add(2, new WanderNearTargetGoal(this, 0.9, 32.0F));
        this.goalSelector.add(2, new WanderAroundPointOfInterestGoal(this, 0.6, false));
        this.goalSelector.add(4, new IronGolemWanderAroundGoal(this, 0.6));
        this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, PlayerEntity.class, 10, true, false, this::shouldAngerAt));
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, MobEntity.class, 5, false, false, (entity) -> entity instanceof Monster && !(entity instanceof CreeperEntity)));
        this.targetSelector.add(4, new UniversalAngerGoal<>(this, false));
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 1000)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, (double)0.25F)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, (double)1.0F)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, (double)4.5F)
                .add(EntityAttributes.GENERIC_STEP_HEIGHT, (double)1.0F);
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 40;
            this.idleAnimationState.start(this.age);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (this.getWorld().isClient()) {
            this.setupAnimationStates();
        }
    }

    public void tickMovement() {
        super.tickMovement();
        if (this.attackTicksLeft > 0) {
            --this.attackTicksLeft;
        }

        if (!this.getWorld().isClient) {
            this.tickAngerLogic((ServerWorld)this.getWorld(), true);
        }

    }

    public void chooseRandomAngerTime() {
        this.setAngerTime(ANGER_TIME_RANGE.get(this.random));
    }

    public void setAngerTime(int angerTime) {
        this.angerTime = angerTime;
    }

    public int getAngerTime() {
        return this.angerTime;
    }

    public void setAngryAt(@Nullable UUID angryAt) {
        this.angryAt = angryAt;
    }

    @Nullable
    public UUID getAngryAt() {
        return this.angryAt;
    }


    public boolean shouldSpawnSprintingParticles() {
        return this.getVelocity().horizontalLengthSquared() > (double)2.5000003E-7F && this.random.nextInt(5) == 0;
    }

    public boolean canTarget(EntityType<?> type) {
        return type == EntityType.CREEPER ? false : super.canTarget(type);
    }

    private float getAttackDamage() {
        return (float)this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
    }

    public boolean tryAttack(Entity target) {
        this.attackTicksLeft = 10;
        this.getWorld().sendEntityStatus(this, (byte)4);
        float f = this.getAttackDamage();
        float g = (int)f > 0 ? f / 2.0F + (float)this.random.nextInt((int)f) : f;
        DamageSource damageSource = this.getDamageSources().mobAttack(this);
        boolean bl = target.damage(damageSource, g);
        if (bl) {
            double var10000;
            if (target instanceof LivingEntity) {
                LivingEntity livingEntity = (LivingEntity)target;
                var10000 = livingEntity.getAttributeValue(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE);
            } else {
                var10000 = (double)0.0F;
            }

            double d = var10000;
            double e = Math.max((double)0.0F, (double)1.0F - d);
            target.setVelocity(target.getVelocity().add((double)0.0F, (double)0.4F * e, (double)0.0F));
            World var11 = this.getWorld();
            if (var11 instanceof ServerWorld) {
                ServerWorld serverWorld = (ServerWorld)var11;
                EnchantmentHelper.onTargetDamaged(serverWorld, target, damageSource);
            }
        }

        this.playSound(SoundEvents.ENTITY_IRON_GOLEM_ATTACK, 1.0F, 1.0F);
        return bl;
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        boolean damaged = super.damage(source, amount);

        if (damaged && source.getAttacker() instanceof LivingEntity) {
            LivingEntity attacker = (LivingEntity) source.getAttacker();

            // Make the entity angry at whoever attacked it
            if (!this.getWorld().isClient) {
                this.setAngryAt(attacker.getUuid());
                this.chooseRandomAngerTime();
                this.setTarget(attacker);
            }
        }

        return damaged;
    }

    public void handleStatus(byte status) {
        if (status == 4) {
            this.attackTicksLeft = 10;
            this.playSound(SoundEvents.ENTITY_IRON_GOLEM_ATTACK, 1.0F, 1.0F);
        } else {
            super.handleStatus(status);
        }

    }

    public int getAttackTicksLeft() {
        return this.attackTicksLeft;
    }

    // Boss Bar
    @Override
    public void onStartedTrackingBy(ServerPlayerEntity player) {
        super.onStartedTrackingBy(player);
        this.bossBar.addPlayer(player);
    }

    @Override
    public void onStoppedTrackingBy(ServerPlayerEntity player) {
        super.onStoppedTrackingBy(player);
        this.bossBar.removePlayer(player);
    }

    @Override
    protected void mobTick() {
        super.mobTick();
        this.bossBar.setPercent(this.getHealth() / this.getMaxHealth());
    }

    static {
        IRON_GOLEM_FLAGS = DataTracker.registerData(MantisEntity.class, TrackedDataHandlerRegistry.BYTE);
        ANGER_TIME_RANGE = TimeHelper.betweenSeconds(20, 39);
    }
}