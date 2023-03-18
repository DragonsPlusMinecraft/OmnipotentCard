package plus.dragons.omnicard.model;

import plus.dragons.omnicard.OmniCard;
import plus.dragons.omnicard.entity.FlyingCardEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;


public class FlyingCardEntityModel extends GeoModel<FlyingCardEntity> {
    @Override
    public ResourceLocation getModelResource(FlyingCardEntity object) {
        return new ResourceLocation(OmniCard.MODID, "geo/entity/card.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(FlyingCardEntity object) {
        return new ResourceLocation(OmniCard.MODID, "textures/card/" + object.getCardType().getTexturePath());
    }

    @Override
    public ResourceLocation getAnimationResource(FlyingCardEntity animatable) {
        return new ResourceLocation(OmniCard.MODID, "animations/entity/card.animation.json");
    }
}
