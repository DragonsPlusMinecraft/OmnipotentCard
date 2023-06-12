package plus.dragons.omnicard.event;

import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import plus.dragons.omnicard.data.ModRecipeGen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBusEvent {

    @SubscribeEvent
    public static void dataGen(GatherDataEvent event) {
        event.getGenerator().addProvider(true, new ModRecipeGen(event.getGenerator()));
    }
}
