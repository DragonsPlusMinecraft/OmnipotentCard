package plus.dragons.omnicard.block.blockentity;

import plus.dragons.omnicard.card.BlockCard;
import plus.dragons.omnicard.card.BlockCards;
import plus.dragons.omnicard.registry.BlockEntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import javax.annotation.Nullable;

public class SpecialCardBlockTileEntity extends BlockEntity implements IAnimatable {
    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);
    private BlockCard card;

    public int getLifetime() {
        return lifetime;
    }

    private int lifetime;
    private boolean preparedVanish;

    public SpecialCardBlockTileEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.SPECIAL_CARD_BLOCK_TILEENTITY.get(), pos, state);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (preparedVanish) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("card_on_disappear"));
        } else {
            event.getController().setAnimation(new AnimationBuilder().loop("card_floating"));
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "card_block_controller", 1, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    public void tickServer() {
        if (lifetime == 0)
            level.setBlockAndUpdate(this.getBlockPos(), Blocks.AIR.defaultBlockState());
        else {
            // Transfer to Disappear Animation
            if (lifetime == 30) {
                preparedVanish = true;
                SyncToClient();
            }
            if (lifetime > 0) {
                lifetime--;
                if (lifetime % 20 == 0)
                    SyncToClient();
            }
            // Handle Special Card Logic
            if (card != null) {
                card.handleServerTick(this);
            }
        }
    }

    public void tickClient() {
        lifetime--;
        if (card != null) {
            card.handleClientTick(this);
        }
    }

    private void SyncToClient() {
        level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_ALL_IMMEDIATE);
    }

    public BlockCard getCardType() {
        return card;
    }

    public void initializingData(BlockCard card) {
        this.card = card;
        lifetime = card.getLifetime();
        preparedVanish = false;
    }


    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        card = BlockCards.fromByte(nbt.getByte("card_type"));
        lifetime = nbt.getInt("lifetime");
        preparedVanish = nbt.getBoolean("should_disappear");
    }

    /*
    Some notes about BlockEntity saving:
    1.18 doesn't really use save() in the same way older versions used to. If you continue overriding save, it will not work.
    Instead, you should override saveAdditional.
    If you are storing BlockEntities somewhere,
    don't call save. Call saveWithFullMetadata(), saveWithId() or saveWithoutMetadata(), depending on your needs.
     */
    @Override
    protected void saveAdditional(CompoundTag tag) {
        tag.putByte("card_type", BlockCards.toByte(card));
        tag.putInt("lifetime", lifetime);
        tag.putBoolean("should_disappear", preparedVanish);
        super.saveAdditional(tag);
    }

    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        handleUpdateTag(pkt.getTag());

    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag compoundNBT = super.getUpdateTag();
        compoundNBT.putByte("card_type", BlockCards.toByte(card));
        compoundNBT.putBoolean("should_disappear", preparedVanish);
        compoundNBT.putInt("lifetime", lifetime);
        return compoundNBT;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        super.handleUpdateTag(tag);
        card = BlockCards.fromByte(tag.getByte("card_type"));
        preparedVanish = tag.getBoolean("should_disappear");
        lifetime = tag.getInt("lifetime");
    }

}
