package plus.dragons.omnicard.registry;

import plus.dragons.omnicard.capability.cardtype.CardTypeData;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CapabilityRegistry {
    @SubscribeEvent
    public static void onSetUpEvent(RegisterCapabilitiesEvent event) {
        event.register(CardTypeData.class);
    }
}
