package plus.dragons.omnicard;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import plus.dragons.omnicard.capability.cardtype.CardTypeData;
import plus.dragons.omnicard.card.CommonCard;
import plus.dragons.omnicard.card.CommonCards;
import plus.dragons.omnicard.item.CardSwitcher;
import plus.dragons.omnicard.misc.Configuration;
import plus.dragons.omnicard.registry.*;
import software.bernie.geckolib.GeckoLib;

;

@Mod(OmniCard.MODID)
public class OmniCard {

    public static final String MODID = "omni_card";

    public OmniCard() {
        GeckoLib.initialize();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Configuration.COMMON_CONFIG);
        var modEventbus = FMLJavaModLoadingContext.get().getModEventBus();

        ItemRegistry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        EntityRegistry.ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        MobEffectRegistry.MOB_EFFECT.register(FMLJavaModLoadingContext.get().getModEventBus());
        BlockRegistry.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BlockEntityRegistry.BLOCK_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        SoundRegistry.SOUNDS.register(FMLJavaModLoadingContext.get().getModEventBus());
        CreativeTabRegistry.TABS.register(FMLJavaModLoadingContext.get().getModEventBus());

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // Item Property Override
        event.enqueueWork(() ->
        {
            ItemProperties.register(ItemRegistry.CARD_STACK.get(),
                    CardSwitcher.CARD_CATEGORY_PROPERTY_NAME, (stack, world, living, id) -> {
                        CardTypeData cardTypeData = stack.getCapability(CardTypeData.CAPABILITY, null).orElseThrow(() -> new RuntimeException("Capability of CardTypeData goes wrong!"));
                        CommonCard card = cardTypeData.get();
                        if (card == CommonCards.RED) {
                            return 0.1F;
                        } else if (card == CommonCards.CORAL) {
                            return 0.2F;
                        } else if (card == CommonCards.GOLD) {
                            return 0.3F;
                        } else if (card == CommonCards.SEA_GREEN) {
                            return 0.4F;
                        } else if (card == CommonCards.AZURE) {
                            return 0.5F;
                        } else if (card == CommonCards.CERULEAN_BLUE) {
                            return 0.6F;
                        } else if (card == CommonCards.HELIOTROPE) {
                            return 0.7F;
                        } else if (card == CommonCards.INK) {
                            return 0.8F;
                        } else
                            return 0;
                    });
        });

    }
}
