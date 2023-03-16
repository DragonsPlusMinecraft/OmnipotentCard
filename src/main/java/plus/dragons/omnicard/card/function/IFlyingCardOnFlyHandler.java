package plus.dragons.omnicard.card.function;

import plus.dragons.omnicard.entity.FlyingCardEntity;

@FunctionalInterface
public interface IFlyingCardOnFlyHandler {
    void handle(FlyingCardEntity card);
}
