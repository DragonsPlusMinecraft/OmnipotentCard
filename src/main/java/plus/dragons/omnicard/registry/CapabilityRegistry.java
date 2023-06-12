package plus.dragons.omnicard.registry;

import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import plus.dragons.omnicard.capability.cardtype.CardTypeData;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CapabilityRegistry {
    @SubscribeEvent
    public static void onSetUpEvent(RegisterCapabilitiesEvent event) {
        event.register(CardTypeData.class);
    }
}
