package org.mythic_studios.dungeon.block.entity;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.mythic_studios.dungeon.block.WeaponForgeBlock;
import org.mythic_studios.dungeon.init.ModBlockEntities;
import org.mythic_studios.dungeon.init.ModRecipes;
import org.mythic_studios.dungeon.recipe.WeaponForging;
import org.mythic_studios.dungeon.recipe.WeaponForgingInput;
import org.mythic_studios.dungeon.screen.WeaponForgingScreenHandler;

import java.util.Objects;
import java.util.Optional;

public class WeaponForgeBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<BlockPos>, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(4, ItemStack.EMPTY);

    private static final int INPUT_SLOT = 0;
    private static final int INPUT_SLOT_2 = 1;
    private static final int INPUT_SLOT_3 = 2;
    private static final int OUTPUT_SLOT = 3;

    private int progress = 0;
    private int maxProgress = 120;
    private final int DEFAULT_MAX_PROGRESS = 120;

    protected final PropertyDelegate propertyDelegate = new PropertyDelegate() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> progress;
                case 1 -> maxProgress;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0 -> progress = value;
                case 1 -> maxProgress = value;
            }
        }

        @Override
        public int size() {
            return 2;
        }
    };

    public WeaponForgeBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.WEAPON_FORGE_BE, pos, state);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("gui.dungeon.weapon_forge");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new WeaponForgingScreenHandler(syncId, playerInventory, this, propertyDelegate);
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayerEntity player) {
        return this.pos;
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, inventory, registryLookup);
        nbt.putInt("forging.progress", progress);
        nbt.putInt("forging.max_progress", maxProgress);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        Inventories.readNbt(nbt, inventory, registryLookup);
        this.progress = nbt.getInt("forging.progress");
        this.maxProgress = nbt.getInt("forging.max_progress");
        super.readNbt(nbt, registryLookup);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient) return;

        boolean wasLit = state.get(WeaponForgeBlock.LIT);
        boolean isLit = this.hasRecipe();

        if (wasLit != isLit) {
            world.setBlockState(pos, state.with(WeaponForgeBlock.LIT, isLit), 3);
        }



        if (hasRecipe()) {
            increaseCraftingProgress();
            markDirty(world, pos, state);

            if (hasCraftingFinished()) {
                craftItem();
                resetProgress();
            }
        } else {
            resetProgress();
        }
    }

    private boolean hasCraftingFinished() {
        if (progress == DEFAULT_MAX_PROGRESS) {
            return true;
        }
        return false;
    }

    private void increaseCraftingProgress() {
        if (progress < maxProgress) {
            this.progress++;
        }
    }


    private void resetProgress() {
        this.progress = 0;
        this.maxProgress = DEFAULT_MAX_PROGRESS;
    }

    private void craftItem() {
        getCurrentRecipe().ifPresent(recipe -> {
            ItemStack output = recipe.value().output();
            // Only craft if the recipe is valid
            if (hasRecipe()) {
                // Remove ingredients and add the output item
                removeStack(INPUT_SLOT, 1);
                removeStack(INPUT_SLOT_2, 1);
                removeStack(INPUT_SLOT_3, 1);
                setStack(OUTPUT_SLOT, new ItemStack(output.getItem(),
                        getStack(OUTPUT_SLOT).getCount() + output.getCount()));
            }
        });
    }


    private boolean hasRecipe() {
        Optional<RecipeEntry<WeaponForging>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) return false;

        ItemStack output = recipe.get().value().output();
        return canInsertItemIntoOutputSlot(output) && canInsertAmountIntoOutputSlot(output.getCount());
    }

    public Optional<RecipeEntry<WeaponForging>> getCurrentRecipe() {
        return Objects.requireNonNull(this.getWorld()).getRecipeManager()
                .getFirstMatch(ModRecipes.WEAPON_FORGING_TYPE, new WeaponForgingInput(
                        inventory.get(0),
                        inventory.get(1),
                        inventory.get(2)
                ), this.getWorld());
    }




    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        ItemStack current = getStack(OUTPUT_SLOT);
        return current.isEmpty() || current.getItem() == output.getItem();
    }

    private boolean canInsertAmountIntoOutputSlot(int amount) {
        ItemStack current = getStack(OUTPUT_SLOT);
        return current.getCount() + amount <= current.getMaxCount();
    }


    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        return createNbt(registryLookup);
    }
}