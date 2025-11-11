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
import org.mythic_studios.dungeon.recipe.Chemistry;

public class ChemistTableSerializer implements RecipeSerializer<Chemistry> {

    // Custom Codec for ItemStack
    public static final Codec<ItemStack> ITEMSTACK_CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.STRING.fieldOf("item").forGetter(itemStack -> Registries.ITEM.getId(itemStack.getItem()).toString()),
                    Codec.INT.fieldOf("count").forGetter(ItemStack::getCount)
            ).apply(instance, (item, count) -> new ItemStack(Registries.ITEM.get(Identifier.tryParse(item)), count))
    );

    public static final MapCodec<Chemistry> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("ingredient1").forGetter(Chemistry::getinputItem1),
            Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("ingredient2").forGetter(Chemistry::getinputItem2),
            Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("ingredient3").forGetter(Chemistry::getinputItem3),
            Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("ingredient4").forGetter(Chemistry::getinputItem4),
            Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("ingredient5").forGetter(Chemistry::getinputItem5),
            ITEMSTACK_CODEC.fieldOf("result").forGetter(Chemistry::output)
    ).apply(inst, Chemistry::new));

    public static final PacketCodec<RegistryByteBuf, Chemistry> STREAM_CODEC = PacketCodec.tuple(
            Ingredient.PACKET_CODEC, Chemistry::getinputItem1,
            Ingredient.PACKET_CODEC, Chemistry::getinputItem2,
            Ingredient.PACKET_CODEC, Chemistry::getinputItem3,
            Ingredient.PACKET_CODEC, Chemistry::getinputItem4,
            Ingredient.PACKET_CODEC, Chemistry::getinputItem5,
            ItemStack.PACKET_CODEC, Chemistry::output,
            Chemistry::new
    );


    @Override
    public MapCodec<Chemistry> codec() {
        return CODEC;
    }

    @Override
    public PacketCodec<RegistryByteBuf, Chemistry> packetCodec() {
        return STREAM_CODEC;
    }
}