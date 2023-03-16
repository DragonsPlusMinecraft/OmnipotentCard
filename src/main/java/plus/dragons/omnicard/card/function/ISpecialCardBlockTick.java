package plus.dragons.omnicard.card.function;

import plus.dragons.omnicard.block.blockentity.SpecialCardBlockTileEntity;

@FunctionalInterface
public interface ISpecialCardBlockTick {
    void handle(SpecialCardBlockTileEntity tileEntity);
}
