package plus.dragons.omnicard.card.function;

import plus.dragons.omnicard.entity.CardTrapEntity;
import net.minecraft.world.entity.LivingEntity;

@FunctionalInterface
public interface ITrapCardActivationHandler {
    void handleTrap(CardTrapEntity trap, LivingEntity victim);
}
