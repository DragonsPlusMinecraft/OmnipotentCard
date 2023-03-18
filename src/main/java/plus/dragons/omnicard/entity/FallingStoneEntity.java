package plus.dragons.omnicard.entity;

import net.minecraft.network.protocol.game.ClientGamePacketListener;
import plus.dragons.omnicard.misc.ModDamage;
import plus.dragons.omnicard.registry.EntityRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;
import java.util.stream.Collectors;

public class FallingStoneEntity extends Entity implements GeoEntity {
    private static final RawAnimation DISAPPEAR = RawAnimation.begin().thenPlayAndHold("disappear");
    private static final RawAnimation FALL = RawAnimation.begin().thenPlayAndHold("falling");
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final EntityDataAccessor<Boolean> DONE_HIT = SynchedEntityData.defineId(FallingStoneEntity.class, EntityDataSerializers.BOOLEAN);
    private int disappearCountdown;


    public FallingStoneEntity(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    public FallingStoneEntity(Level world) {
        super(EntityRegistry.FALLING_STONE.get(), world);
        disappearCountdown = 17;
    }

    @Override
    public void tick() {
        if (!level.isClientSide()) {
            List<LivingEntity> targets = getLivingEntityBeneath();
            if (!targets.isEmpty()) {
                for (LivingEntity livingEntity : targets) {
                    livingEntity.hurt(ModDamage.causeCardDamage(this.damageSources(),this, null), 6);
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100));
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100));
                }
                getEntityData().set(DONE_HIT, true);
            }
            if (isInWall()) {
                getEntityData().set(DONE_HIT, true);
            }
            if (getEntityData().get(DONE_HIT)) {
                if (disappearCountdown <= 0) {
                    remove(RemovalReason.DISCARDED);
                } else
                    disappearCountdown--;
            }
        }
        if (!getEntityData().get(DONE_HIT)) {
            setDeltaMovement(0, -0.25D, 0);
        } else {
            setDeltaMovement(0, -0.2D, 0);
        }
        move(MoverType.SELF, this.getDeltaMovement());
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(DONE_HIT, false);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compoundNBT) {
        getEntityData().set(DONE_HIT, compoundNBT.getBoolean("done_hit"));
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compoundNBT) {
        compoundNBT.putBoolean("done_hit", getEntityData().get(DONE_HIT));
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "falling_stone_controller", 1, state->{
            if (state.getAnimatable().getEntityData().get(DONE_HIT)) {
                state.getController().setAnimation(DISAPPEAR);
            } else {
                state.getController().setAnimation(FALL);
            }
            return PlayState.CONTINUE;
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    private List<LivingEntity> getLivingEntityBeneath() {
        return level.getEntities(this, makeBoundingBox().expandTowards(0, -0.3, 0), entity -> entity instanceof LivingEntity)
                .stream().map(entity -> (LivingEntity) entity).collect(Collectors.toList());
    }
}
