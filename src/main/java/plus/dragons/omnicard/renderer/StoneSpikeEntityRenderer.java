package plus.dragons.omnicard.renderer;

import plus.dragons.omnicard.entity.StoneSpikeEntity;
import plus.dragons.omnicard.model.StoneSpikeEntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

public class StoneSpikeEntityRenderer extends GeoProjectilesRenderer<StoneSpikeEntity> {
    public StoneSpikeEntityRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new StoneSpikeEntityModel());
    }
}
