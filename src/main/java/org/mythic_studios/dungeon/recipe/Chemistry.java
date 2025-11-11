package org.mythic_studios.dungeon.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;
import org.mythic_studios.dungeon.init.ModRecipes;

public class Chemistry implements Recipe<ChemistryInput> {
    private final Ingredient inputItem1;
    private final Ingredient inputItem2;
    private final Ingredient inputItem3;
    private final Ingredient inputItem4;
    private final Ingredient inputItem5;
    private final ItemStack output;

    public Chemistry(Ingredient inputItem1, Ingredient inputItem2, Ingredient inputItem3, Ingredient inputItem4, Ingredient inputItem5, ItemStack output) {
        this.inputItem1 = inputItem1;
        this.inputItem2 = inputItem2;
        this.inputItem3 = inputItem3;
        this.inputItem4 = inputItem4;
        this.inputItem5 = inputItem5;
        this.output = output;
    }

    @Override
    public boolean matches(ChemistryInput input, World world) {
        return (inputItem1.test(input.getStackInSlot(0)) && inputItem2.test(input.getStackInSlot(1))) && inputItem3.test(input.getStackInSlot(2)) && inputItem4.test(input.getStackInSlot(3)) && inputItem5.test(input.getStackInSlot(4));
    }

    @Override
    public ItemStack craft(ChemistryInput input, RegistryWrapper.WrapperLookup lookup) {
        return output.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup lookup) {
        return output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.CHEMISTRY_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.CHEMISTRY_TYPE;
    }

    public ItemStack output() {
        return output;
    }

    public Ingredient getinputItem1() {
        return inputItem1;
    }

    public Ingredient getinputItem2() {
        return inputItem2;
    }

    public Ingredient getinputItem3() {
        return inputItem3;
    }
    
    public Ingredient getinputItem4() {
        return inputItem4;
    }

    public Ingredient getinputItem5() {
        return inputItem5;
    }
}