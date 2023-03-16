package plus.dragons.omnicard.card.function;

import plus.dragons.omnicard.entity.FlyingCardEntity;
import net.minecraft.world.entity.LivingEntity;

@FunctionalInterface
public interface IFlyingCardHitEntityHandler {
    void handleHit(FlyingCardEntity card, LivingEntity victim);
}
