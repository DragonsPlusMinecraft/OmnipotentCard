package plus.dragons.omnicard.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import plus.dragons.omnicard.OmniCard;
import plus.dragons.omnicard.item.CardSwitcher;

public class CreativeTabRegistry {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, OmniCard.MODID);
    public static final RegistryObject<CreativeModeTab> GENERAL = TABS.register("general", ()->CreativeModeTab.builder()
            .title(Component.literal("itemGroup.omni_card.general"))
            .icon(() -> ItemRegistry.BLANK_CARD.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
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
            }).build());
}
