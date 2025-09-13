package org.mythic_studios.dungeon.recipe.serializer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.mythic_studios.dungeon.recipe.WeaponForging;

public class WeaponForgingSerializer implements RecipeSerializer<WeaponForging> {

    // Custom Codec for ItemStack
    public static final Codec<ItemStack> ITEMSTACK_CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.STRING.fieldOf("item").forGetter(itemStack -> Registries.ITEM.getId(itemStack.getItem()).toString()),
                    Codec.INT.fieldOf("count").forGetter(ItemStack::getCount)
            ).apply(instance, (item, count) -> new ItemStack(Registries.ITEM.get(Identifier.tryParse(item)), count))
    );

    public static final MapCodec<WeaponForging> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("ingredient1").forGetter(WeaponForging::getinputItem1),
            Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("ingredient2").forGetter(WeaponForging::getinputItem2),
            Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("ingredient3").forGetter(WeaponForging::getinputItem3),
            ITEMSTACK_CODEC.fieldOf("result").forGetter(WeaponForging::output)
    ).apply(inst, WeaponForging::new));

    public static final PacketCodec<RegistryByteBuf, WeaponForging> STREAM_CODEC = PacketCodec.tuple(
            Ingredient.PACKET_CODEC, WeaponForging::getinputItem1,
            Ingredient.PACKET_CODEC, WeaponForging::getinputItem2,
            Ingredient.PACKET_CODEC, WeaponForging::getinputItem3,
            ItemStack.PACKET_CODEC, WeaponForging::output,
            WeaponForging::new
    );


    @Override
    public MapCodec<WeaponForging> codec() {
        return CODEC;
    }

    @Override
    public PacketCodec<RegistryByteBuf, WeaponForging> packetCodec() {
        return STREAM_CODEC;
    }
}