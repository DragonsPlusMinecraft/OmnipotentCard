package plus.dragons.omnicard.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import plus.dragons.omnicard.entity.CardTrapEntity;
import plus.dragons.omnicard.model.CardTrapEntityModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class CardTrapEntityRenderer extends GeoEntityRenderer<CardTrapEntity> {
    public CardTrapEntityRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new CardTrapEntityModel());
        shadowRadius = 0.03F;
    }
}
