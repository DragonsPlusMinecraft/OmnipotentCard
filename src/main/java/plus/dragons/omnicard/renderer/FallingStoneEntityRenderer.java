package plus.dragons.omnicard.renderer;

import plus.dragons.omnicard.entity.FallingStoneEntity;
import plus.dragons.omnicard.model.FallingStoneEntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class FallingStoneEntityRenderer extends GeoEntityRenderer<FallingStoneEntity> {
    public FallingStoneEntityRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new FallingStoneEntityModel());
    }
}
