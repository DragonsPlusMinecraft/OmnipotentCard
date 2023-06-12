package plus.dragons.omnicard.card.function;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import plus.dragons.omnicard.entity.FlyingCardEntity;

@FunctionalInterface
public interface IFlyingCardHitBlockHandler {
    void handleHit(FlyingCardEntity card, BlockPos pos, Direction face);
}
