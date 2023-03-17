package plus.dragons.omnicard.renderer;

import plus.dragons.omnicard.entity.StoneSpikeEntity;
import plus.dragons.omnicard.model.StoneSpikeEntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class StoneSpikeEntityRenderer extends GeoEntityRenderer<StoneSpikeEntity> {
    public StoneSpikeEntityRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new StoneSpikeEntityModel());
    }
}
