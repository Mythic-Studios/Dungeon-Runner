package org.mythic_studios.dungeon.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;
import org.mythic_studios.dungeon.init.ModRecipes;

public class WeaponForging implements Recipe<WeaponForgingInput> {
    private final Ingredient inputItem1;
    private final Ingredient inputItem2;
    private final Ingredient inputItem3;
    private final ItemStack output;

    public WeaponForging(Ingredient inputItem1, Ingredient inputItem2, Ingredient inputItem3, ItemStack output) {
        this.inputItem1 = inputItem1;
        this.inputItem2 = inputItem2;
        this.inputItem3 = inputItem3;
        this.output = output;
    }

    @Override
    public boolean matches(WeaponForgingInput input, World world) {
        return (inputItem1.test(input.getStackInSlot(0)) && inputItem2.test(input.getStackInSlot(1))) && inputItem3.test(input.getStackInSlot(2));
    }

    @Override
    public ItemStack craft(WeaponForgingInput input, RegistryWrapper.WrapperLookup lookup) {
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
        return ModRecipes.WEAPON_FORGING_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.WEAPON_FORGING_TYPE;
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
}