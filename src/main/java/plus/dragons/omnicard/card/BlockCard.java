package plus.dragons.omnicard.card;

import plus.dragons.omnicard.block.blockentity.SpecialCardBlockTileEntity;
import plus.dragons.omnicard.card.function.ISpecialCardBlockTick;

import java.util.Objects;


public class BlockCard extends AbstractCard {
    private final ISpecialCardBlockTick serverTickHandler;
    private final ISpecialCardBlockTick clientTickHandler;
    private final int lifetime;
    private final boolean canRetrieveByBreak;

    public int getLifetime() {
        return lifetime;
    }

    public boolean canRetrieve() {
        return canRetrieveByBreak;
    }


    public BlockCard(Builder builder) {
        super(builder);
        serverTickHandler = builder.serverTickHandler;
        clientTickHandler = builder.clientTickHandler;
        lifetime = builder.lifetime;
        canRetrieveByBreak = builder.canRetrieveByBreak;

    }

    public void handleServerTick(SpecialCardBlockTileEntity specialCardBlockTile) {
        if (specialCardBlockTile != null && serverTickHandler != null) {
            serverTickHandler.handle(specialCardBlockTile);
        }
    }

    public void handleClientTick(SpecialCardBlockTileEntity specialCardBlockTile) {
        if (specialCardBlockTile != null && clientTickHandler != null) {
            clientTickHandler.handle(specialCardBlockTile);
        }
    }

    public static class Builder extends AbstractCard.Builder<Builder> {
        private ISpecialCardBlockTick serverTickHandler;
        private ISpecialCardBlockTick clientTickHandler;
        private final int lifetime;
        private boolean canRetrieveByBreak = false;

        public Builder(String name, String category, int lifetime) {
            super(name, category);
            this.lifetime = lifetime;
        }

        public Builder isRetrievableWhenBreak() {
            canRetrieveByBreak = true;
            return this;
        }

        @Override
        public BlockCard build() {
            return new BlockCard(this);
        }

        @Override
        protected Builder self() {
            return this;
        }

        public Builder setServerTickHandler(ISpecialCardBlockTick serverTickHandler) {
            this.serverTickHandler = Objects.requireNonNull(serverTickHandler);
            return this;
        }

        public Builder setClientTickHandler(ISpecialCardBlockTick serverTickHandler) {
            this.clientTickHandler = Objects.requireNonNull(serverTickHandler);
            return this;
        }


    }
}
