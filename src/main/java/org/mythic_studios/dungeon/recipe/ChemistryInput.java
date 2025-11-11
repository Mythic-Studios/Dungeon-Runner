package org.mythic_studios.dungeon.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

public class ChemistryInput implements RecipeInput {
    public final ItemStack input1;
    public final ItemStack input2;
    public final ItemStack input3;
    public final ItemStack input4;
    public final ItemStack input5;

    public ChemistryInput(ItemStack input1, ItemStack input2, ItemStack input3, ItemStack input4, ItemStack input5) {
        this.input1 = input1;
        this.input2 = input2;
        this.input3 = input3;
        this.input4 = input4;
        this.input5 = input5;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return switch (slot) {
            case 0 -> input1;
            case 1 -> input2;
            case 2 -> input3;
            case 3 -> input4;
            case 4 -> input5;
            default -> ItemStack.EMPTY;
        };
    }

    @Override
    public int getSize() {
        return 5;
    }

    @Override
    public boolean isEmpty() {
        return input1.isEmpty() && input2.isEmpty() && input3.isEmpty() && input4.isEmpty() && input5.isEmpty();
    }
}