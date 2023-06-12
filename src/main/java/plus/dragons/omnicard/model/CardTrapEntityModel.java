package plus.dragons.omnicard.model;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import plus.dragons.omnicard.OmniCard;
import plus.dragons.omnicard.entity.CardTrapEntity;
import software.bernie.geckolib.model.GeoModel;

import java.util.UUID;

public class CardTrapEntityModel extends GeoModel<CardTrapEntity> {
    @Override
    public ResourceLocation getModelResource(CardTrapEntity object) {
        return new ResourceLocation(OmniCard.MODID, "geo/entity/card.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CardTrapEntity object) {
        UUID uuid = object.getOwnerUUID();
        boolean flag = false;
        if (uuid != null && Minecraft.getInstance().player.getUUID() != null)
            if (uuid.equals(Minecraft.getInstance().player.getUUID()))
                flag = true;
        if (flag)
            return new ResourceLocation(OmniCard.MODID, "textures/card/" + object.getCardType().getTexturePath());
        else
            return new ResourceLocation(OmniCard.MODID, "textures/card/trap_unknown.png");

    }

    @Override
    public ResourceLocation getAnimationResource(CardTrapEntity animatable) {
        return new ResourceLocation(OmniCard.MODID, "animations/entity/card.animation.json");
    }
}
