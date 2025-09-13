package org.mythic_studios.dungeon.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

public class WeaponForgingInput implements RecipeInput {
    public final ItemStack input1;
    public final ItemStack input2;
    public final ItemStack input3;

    public WeaponForgingInput(ItemStack input1, ItemStack input2, ItemStack input3) {
        this.input1 = input1;
        this.input2 = input2;
        this.input3 = input3;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return switch (slot) {
            case 0 -> input1;
            case 1 -> input2;
            case 2 -> input3;
            default -> ItemStack.EMPTY;
        };
    }

    @Override
    public int getSize() {
        return 3;
    }

    @Override
    public boolean isEmpty() {
        return input1.isEmpty() && input2.isEmpty() && input3.isEmpty();
    }
}