package plus.dragons.omnicard.misc;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.CreativeModeTabEvent;
import plus.dragons.omnicard.OmniCard;
import plus.dragons.omnicard.registry.ItemRegistry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModGroup {

    public static CreativeModeTab GENERAL;

    public static void registerTabs(CreativeModeTabEvent.Register event) {
        GENERAL = event.registerCreativeModeTab(new ResourceLocation(OmniCard.MODID, "general"), builder -> builder
                .icon(()->ItemRegistry.BLANK_CARD.get().getDefaultInstance())
                .title(Component.translatable("itemGroup.omni_card.general"))
                .displayItems((featureFlags, output, hasOp) -> {
                    output.accept(ItemRegistry.BLANK_CARD.get());
                    output.accept(ItemRegistry.FLAME_CARD.get());
                    output.accept(ItemRegistry.TORRENT_CARD.get());
                    output.accept(ItemRegistry.THUNDER_CARD.get());
                    output.accept(ItemRegistry.BRAMBLE_CARD.get());
                    output.accept(ItemRegistry.EARTH_CARD.get());
                    output.accept(ItemRegistry.END_CARD.get());
                    output.accept(ItemRegistry.CARD_STACK.get());
                    output.accept(ItemRegistry.FIELD_CARD.get());
                    output.accept(ItemRegistry.PURIFICATION_CARD.get());
                    output.accept(ItemRegistry.SEAL_CARD.get());
                    output.accept(ItemRegistry.SUNNY_CARD.get());
                    output.accept(ItemRegistry.RAINY_CARD.get());
                    output.accept(ItemRegistry.THUNDERSTORM_CARD.get());
                    output.accept(ItemRegistry.BLOOM_CARD.get());
                    output.accept(ItemRegistry.PROTOTYPE_CORE.get());
                    output.accept(ItemRegistry.FIELD_CORE.get());
                    output.accept(ItemRegistry.PURIFICATION_CORE.get());
                    output.accept(ItemRegistry.SEAL_CORE.get());
                    output.accept(ItemRegistry.SUNNY_CORE.get());
                    output.accept(ItemRegistry.RAINY_CORE.get());
                    output.accept(ItemRegistry.THUNDERSTORM_CORE.get());
                    output.accept(ItemRegistry.BLOOM_CORE.get());
                })
        );
    }
}
