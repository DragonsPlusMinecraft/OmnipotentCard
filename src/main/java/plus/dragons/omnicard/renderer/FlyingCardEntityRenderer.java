package plus.dragons.omnicard.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.util.Mth;
import plus.dragons.omnicard.entity.FlyingCardEntity;
import plus.dragons.omnicard.model.FlyingCardEntityModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class FlyingCardEntityRenderer extends GeoEntityRenderer<FlyingCardEntity> {

    public FlyingCardEntityRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new FlyingCardEntityModel());
        shadowRadius = 0.03F;
    }

    @Override
    protected void applyRotations(FlyingCardEntity animatable, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTick) {
        poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTick, animatable.yRotO, animatable.getYRot()) - 90));
        poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTick, animatable.xRotO, animatable.getXRot())));
    }
}
