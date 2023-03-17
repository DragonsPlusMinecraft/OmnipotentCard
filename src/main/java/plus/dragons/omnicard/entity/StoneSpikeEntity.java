package plus.dragons.omnicard.entity;

import net.minecraft.network.protocol.game.ClientGamePacketListener;
import plus.dragons.omnicard.registry.EntityRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class StoneSpikeEntity extends Entity implements GeoAnimatable {
    private final AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);
    private static final EntityDataAccessor<Boolean> DONE_STRIKE = SynchedEntityData.defineId(StoneSpikeEntity.class, EntityDataSerializers.BOOLEAN);
    private int lifetime;


    public StoneSpikeEntity(EntityType<?> p_i48580_1_, Level p_i48580_2_) {
        super(p_i48580_1_, p_i48580_2_);
    }

    public StoneSpikeEntity(Level world) {
        super(EntityRegistry.STONE_SPIKE.get(), world);
        lifetime = 25;
    }

    @Override
    public void tick() {
        if (!level.isClientSide()) {
            if (lifetime <= 0)
                remove(RemovalReason.DISCARDED);
            else
                lifetime--;
        }
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(DONE_STRIKE, false);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compoundNBT) {
        getEntityData().set(DONE_STRIKE, compoundNBT.getBoolean("done_strike"));
        lifetime = compoundNBT.getInt("done_strike");
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compoundNBT) {
        compoundNBT.putBoolean("done_strike", getEntityData().get(DONE_STRIKE));
        compoundNBT.putInt("lifetime", lifetime);
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    private <E extends GeoAnimatable> PlayState predicate(AnimationState<E> state) {
        if (getEntityData().get(DONE_STRIKE))
            return PlayState.STOP;
        else {
            state.getController().setAnimation(RawAnimation.begin().thenPlayAndHold("rise"));
            return PlayState.CONTINUE;
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "falling_stone_controller", 1, this::predicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return factory;
    }

    @Override
    public double getTick(Object o) {
        return 0;
    }
}
