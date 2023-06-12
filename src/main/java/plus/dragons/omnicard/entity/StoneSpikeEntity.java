package plus.dragons.omnicard.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import plus.dragons.omnicard.registry.EntityRegistry;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

public class StoneSpikeEntity extends Entity implements GeoEntity {
    private static final RawAnimation RISE = RawAnimation.begin().thenPlayAndHold("rise");
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final EntityDataAccessor<Boolean> DONE_STRIKE = SynchedEntityData.defineId(StoneSpikeEntity.class, EntityDataSerializers.BOOLEAN);
    private int lifetime;


    public StoneSpikeEntity(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    public StoneSpikeEntity(Level world) {
        super(EntityRegistry.STONE_SPIKE.get(), world);
        lifetime = 25;
    }

    @Override
    public void tick() {
        if (!level().isClientSide()) {
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
    public @NotNull Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, state -> state.setAndContinue(RISE))
                .setAnimationSpeedHandler(stoneSpikeEntity -> stoneSpikeEntity.getEntityData().get(DONE_STRIKE) ? 0 : 1D));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
