package org.mythic_studios.dungeon.item.hammer_types;

import net.minecraft.block.BlockState;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;
import java.util.function.Predicate;

public class HammerOfPoison extends Item {
    public final int StatusEffectLength;

    public HammerOfPoison(Settings settings, int statusEffectLength) {
        super(settings);
        StatusEffectLength = statusEffectLength;
    }
    private static final int ATTACK_DAMAGE_MODIFIER_VALUE = 5;
    private static final float ATTACK_SPEED_MODIFIER_VALUE = -3.4F;
    public static final float MINING_SPEED_MULTIPLIER = 1.5F;
    private static final float field_50141 = 5.0F;
    public static final float KNOCKBACK_RANGE = 3.5F;
    private static final float KNOCKBACK_POWER = 0.7F;


    public static AttributeModifiersComponent createAttributeModifiers() {
        return AttributeModifiersComponent.builder().add(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, (double)5.0F, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND).add(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, (double)-3.4F, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND).build();
    }

    public static ToolComponent createToolComponent() {
        return new ToolComponent(List.of(), 1.0F, 2);
    }

    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        return !miner.isCreative();
    }

    public int getEnchantability() {
        return 15;
    }

    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, StatusEffectLength, 5, false, false), attacker);

        if (attacker instanceof ServerPlayerEntity serverPlayerEntity) {
            if (shouldDealAdditionalDamage(serverPlayerEntity)) {
                ServerWorld serverWorld = (ServerWorld)attacker.getWorld();
                if (serverPlayerEntity.shouldIgnoreFallDamageFromCurrentExplosion() && serverPlayerEntity.currentExplosionImpactPos != null) {
                    if (serverPlayerEntity.currentExplosionImpactPos.y > serverPlayerEntity.getPos().y) {
                        serverPlayerEntity.currentExplosionImpactPos = serverPlayerEntity.getPos();
                    }
                } else {
                    serverPlayerEntity.currentExplosionImpactPos = serverPlayerEntity.getPos();
                }

                serverPlayerEntity.setIgnoreFallDamageFromCurrentExplosion(true);
                serverPlayerEntity.setVelocity(serverPlayerEntity.getVelocity().withAxis(Direction.Axis.Y, (double)0.01F));
                serverPlayerEntity.networkHandler.sendPacket(new EntityVelocityUpdateS2CPacket(serverPlayerEntity));
                if (target.isOnGround()) {
                    serverPlayerEntity.setSpawnExtraParticlesOnFall(true);
                    SoundEvent soundEvent = serverPlayerEntity.fallDistance > 5.0F ? SoundEvents.ITEM_MACE_SMASH_GROUND_HEAVY : SoundEvents.ITEM_MACE_SMASH_GROUND;
                    serverWorld.playSound((PlayerEntity)null, serverPlayerEntity.getX(), serverPlayerEntity.getY(), serverPlayerEntity.getZ(), soundEvent, serverPlayerEntity.getSoundCategory(), 1.0F, 1.0F);
                } else {
                    serverWorld.playSound((PlayerEntity)null, serverPlayerEntity.getX(), serverPlayerEntity.getY(), serverPlayerEntity.getZ(), SoundEvents.ITEM_MACE_SMASH_AIR, serverPlayerEntity.getSoundCategory(), 1.0F, 1.0F);
                }

                knockbackNearbyEntities(serverWorld, serverPlayerEntity, target);
            }
        }

        return true;
    }

    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (shouldDealAdditionalDamage(attacker)) {
            attacker.onLanding();
        }

    }

    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return ingredient.isOf(Items.BREEZE_ROD);
    }

    public float getBonusAttackDamage(Entity target, float baseAttackDamage, DamageSource damageSource) {
        Entity f = damageSource.getSource();
        if (f instanceof LivingEntity livingEntity) {
            if (!shouldDealAdditionalDamage(livingEntity)) {
                return 0.0F;
            } else {
                float g = 8.0F;
                float h = livingEntity.fallDistance;
                float i;
                if (h <= 3.0F) {
                    i = 4.0F * h;
                } else if (h <= 8.0F) {
                    i = 12.0F + 2.0F * (h - 3.0F);
                } else {
                    i = 22.0F + h - 8.0F;
                }

                World var10 = livingEntity.getWorld();
                if (var10 instanceof ServerWorld) {
                    ServerWorld serverWorld = (ServerWorld)var10;
                    return i + EnchantmentHelper.getSmashDamagePerFallenBlock(serverWorld, livingEntity.getWeaponStack(), target, damageSource, 0.0F) * h;
                } else {
                    return i;
                }
            }
        } else {
            return 0.0F;
        }
    }

    private static void knockbackNearbyEntities(World world, PlayerEntity player, Entity attacked) {
        world.syncWorldEvent(2013, attacked.getSteppingPos(), 750);
        world.getEntitiesByClass(LivingEntity.class, attacked.getBoundingBox().expand((double)3.5F), getKnockbackPredicate(player, attacked)).forEach((entity) -> {
            Vec3d vec3d = entity.getPos().subtract(attacked.getPos());
            double d = getKnockback(player, entity, vec3d);
            Vec3d vec3d2 = vec3d.normalize().multiply(d);
            if (d > (double)0.0F) {
                entity.addVelocity(vec3d2.x, (double)0.7F, vec3d2.z);
                if (entity instanceof ServerPlayerEntity) {
                    ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)entity;
                    serverPlayerEntity.networkHandler.sendPacket(new EntityVelocityUpdateS2CPacket(serverPlayerEntity));
                }
            }

        });
    }

    private static Predicate<LivingEntity> getKnockbackPredicate(PlayerEntity player, Entity attacked) {
        return (entity) -> {
            boolean bl;
            boolean bl2;
            boolean bl3;
            boolean var10000;
            label62: {
                bl = !entity.isSpectator();
                bl2 = entity != player && entity != attacked;
                bl3 = !player.isTeammate(entity);
                if (entity instanceof TameableEntity tameableEntity) {
                    if (tameableEntity.isTamed() && player.getUuid().equals(tameableEntity.getOwnerUuid())) {
                        var10000 = true;
                        break label62;
                    }
                }

                var10000 = false;
            }

            boolean bl4;
            label55: {
                bl4 = !var10000;
                if (entity instanceof ArmorStandEntity armorStandEntity) {
                    if (armorStandEntity.isMarker()) {
                        var10000 = false;
                        break label55;
                    }
                }

                var10000 = true;
            }

            boolean bl5 = var10000;
            boolean bl6 = attacked.squaredDistanceTo(entity) <= Math.pow((double)3.5F, (double)2.0F);
            return bl && bl2 && bl3 && bl4 && bl5 && bl6;
        };
    }

    private static double getKnockback(PlayerEntity player, LivingEntity attacked, Vec3d distance) {
        return ((double)3.5F - distance.length()) * (double)0.7F * (double)(player.fallDistance > 5.0F ? 2 : 1) * ((double)1.0F - attacked.getAttributeValue(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE));
    }

    public static boolean shouldDealAdditionalDamage(LivingEntity attacker) {
        return attacker.fallDistance > 1.5F && !attacker.isFallFlying();
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {

        tooltip.add(Text.literal(" "));

        if (Screen.hasShiftDown()) {
            if (StatusEffectLength == 20) {
                tooltip.add(Text.translatable("tooltip.duration.20"));
            } else if (StatusEffectLength == 40) {
                tooltip.add(Text.translatable("tooltip.duration.40"));
            } else if (StatusEffectLength == 60) {
                tooltip.add(Text.translatable("tooltip.duration.60"));
            } else if (StatusEffectLength == 80) {
                tooltip.add(Text.translatable("tooltip.duration.80"));
            } else if (StatusEffectLength == 100) {
                tooltip.add(Text.translatable("tooltip.duration.100"));
            } else if (StatusEffectLength == 140) {
                tooltip.add(Text.translatable("tooltip.duration.140"));
            }
        } else {
            tooltip.add(Text.translatable("tooltip.shift.ability"));
        }

        tooltip.add(Text.literal(" "));
        tooltip.add(Text.translatable("tooltip.poison_ability_blade.1"));
        tooltip.add(Text.translatable("tooltip.poison_ability_blade.2"));
    }
}
