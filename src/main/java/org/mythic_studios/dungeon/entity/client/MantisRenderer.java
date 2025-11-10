package org.mythic_studios.dungeon.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.mythic_studios.dungeon.DungeonRunner;
import org.mythic_studios.dungeon.entity.MantisEntity;

public class MantisRenderer extends MobEntityRenderer<MantisEntity, MantisModel<MantisEntity>> {
    public MantisRenderer(EntityRendererFactory.Context context) {
        super(context, new MantisModel<>(context.getPart(MantisModel.MANTIS)), 0.75f);
    }

    @Override
    public Identifier getTexture(MantisEntity entity) {
        return Identifier.of(DungeonRunner.MOD_ID, "textures/entity/mantis/mantis.png");
    }

    @Override
    public void render(MantisEntity livingEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {

        matrixStack.scale(1f, 1f, 1f);

        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}