package plus.dragons.omnicard.card.function;

import net.minecraft.world.entity.LivingEntity;
import plus.dragons.omnicard.entity.CardTrapEntity;

@FunctionalInterface
public interface ITrapCardActivationHandler {
    void handleTrap(CardTrapEntity trap, LivingEntity victim);
}
