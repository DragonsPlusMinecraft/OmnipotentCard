package plus.dragons.omnicard.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraftforge.client.extensions.common.IClientMobEffectExtensions;

import java.util.function.Consumer;

public class HiddenEffect extends MobEffect {
    public HiddenEffect(MobEffectCategory p_i50391_1_) {
        super(p_i50391_1_, 0);
    }

    @Override
    public void initializeClient(Consumer<IClientMobEffectExtensions> consumer) {
        consumer.accept(new IClientMobEffectExtensions() {

            @Override
            public boolean isVisibleInGui(MobEffectInstance effect) {
                return false;
            }

            @Override
            public boolean isVisibleInInventory(MobEffectInstance effect) {
                return false;
            }
        });
    }
}
