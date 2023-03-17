package plus.dragons.omnicard.model;

import plus.dragons.omnicard.OmniCard;
import plus.dragons.omnicard.entity.StoneSpikeEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class StoneSpikeEntityModel extends GeoModel<StoneSpikeEntity> {
    @Override
    public ResourceLocation getModelResource(StoneSpikeEntity object) {
        return new ResourceLocation(OmniCard.MODID, "geo/entity/stone_spike.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(StoneSpikeEntity object) {
        return new ResourceLocation(OmniCard.MODID, "textures/entity/stone_entity.png");
    }

    @Override
    public ResourceLocation getAnimationResource(StoneSpikeEntity animatable) {
        return new ResourceLocation(OmniCard.MODID, "animations/entity/stone_spike.animation.json");
    }
}
