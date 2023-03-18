package plus.dragons.omnicard.card.function;

import plus.dragons.omnicard.blockentity.SpecialCardBlockTileEntity;

@FunctionalInterface
public interface ISpecialCardBlockTick {
    void handle(SpecialCardBlockTileEntity tileEntity);
}
