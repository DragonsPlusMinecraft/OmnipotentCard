package plus.dragons.omnicard.card.function;

import net.minecraft.world.entity.LivingEntity;
import plus.dragons.omnicard.entity.FlyingCardEntity;

@FunctionalInterface
public interface IFlyingCardHitEntityHandler {
    void handleHit(FlyingCardEntity card, LivingEntity victim);
}
