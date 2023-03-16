package plus.dragons.omnicard.renderer;

import plus.dragons.omnicard.entity.CardTrapEntity;
import plus.dragons.omnicard.model.CardTrapEntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

public class CardTrapEntityRenderer extends GeoProjectilesRenderer<CardTrapEntity> {
    public CardTrapEntityRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new CardTrapEntityModel());
        shadowRadius = 0.03F;
    }
}
