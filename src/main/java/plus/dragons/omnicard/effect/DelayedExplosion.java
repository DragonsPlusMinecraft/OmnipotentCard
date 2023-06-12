package plus.dragons.omnicard.effect;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import plus.dragons.omnicard.misc.ModDamage;
import plus.dragons.omnicard.registry.MobEffectRegistry;

public class DelayedExplosion extends HiddenEffect {
    public DelayedExplosion(MobEffectCategory p_i50391_1_) {
        super(p_i50391_1_);
    }

    @Override
    public void applyEffectTick(LivingEntity livingEntity, int amplifier) {
        if (this == MobEffectRegistry.READY_TO_EXPLODE.get() && !livingEntity.level().isClientSide()) {
            Level.ExplosionInteraction interaction = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(livingEntity.level(), livingEntity) ? Level.ExplosionInteraction.TNT : Level.ExplosionInteraction.NONE;
            livingEntity.level().explode(livingEntity, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), 3, interaction);
            livingEntity.hurt(ModDamage.causeExplosion(), 6);
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration == 1;
    }
}
