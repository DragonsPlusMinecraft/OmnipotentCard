package plus.dragons.omnicard.model;

import plus.dragons.omnicard.OmniCard;
import plus.dragons.omnicard.entity.FallingStoneEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class FallingStoneEntityModel extends GeoModel<FallingStoneEntity> {
    @Override
    public ResourceLocation getModelResource(FallingStoneEntity object) {
        return new ResourceLocation(OmniCard.MODID, "geo/entity/falling_stone.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(FallingStoneEntity object) {
        return new ResourceLocation(OmniCard.MODID, "textures/entity/stone_entity.png");
    }

    @Override
    public ResourceLocation getAnimationResource(FallingStoneEntity animatable) {
        return new ResourceLocation(OmniCard.MODID, "animations/entity/falling_stone.animation.json");
    }
}
