package plus.dragons.omnicard.misc;

import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

import javax.annotation.Nullable;

public class ModDamage {

    private static DamageType HOLY_FIRE = new DamageType("omni_card.holy_fire", 0.1F, DamageEffects.BURNING);

    private static DamageType LETHAL_POISON = new DamageType("omni_card.lethal_poison", 0.1F, DamageEffects.BURNING);

    private static DamageType EXPLOSION = new DamageType("omni_card.explosion", 0.1F, DamageEffects.BURNING);

    public static DamageSource causeHolyFlameDamage() {
        return new SimpleDeathMessageDamageSource(Holder.direct(HOLY_FIRE));
    }

    public static DamageSource causeLethalPoisonDamage() {
        return new SimpleDeathMessageDamageSource(Holder.direct(LETHAL_POISON));
    }

    public static DamageSource causeExplosion() {
        return new SimpleDeathMessageDamageSource(Holder.direct(EXPLOSION));
    }

    public static DamageSource causeCardDamage(DamageSources sources, Entity from, @Nullable Entity owner) {
        if (owner instanceof LivingEntity)
            return sources.mobAttack((LivingEntity) owner);
        else
            return sources.thrown(from, owner);
    }

    public static class SimpleDeathMessageDamageSource extends DamageSource {

        public SimpleDeathMessageDamageSource(Holder<DamageType> typeHolder) {
            super(typeHolder, null, null);
        }

        @Override
        public Component getLocalizedDeathMessage(LivingEntity livingEntity) {
            String s = "death.attack." + type().msgId();
            return Component.translatable(s, livingEntity.getDisplayName());
        }
    }
}
