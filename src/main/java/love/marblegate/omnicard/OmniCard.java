package love.marblegate.omnicard;

import love.marblegate.omnicard.capability.cardtype.CardTypeData;
import love.marblegate.omnicard.capability.cardtype.ICardTypeData;
import love.marblegate.omnicard.card.CommonCard;
import love.marblegate.omnicard.card.CommonCards;
import love.marblegate.omnicard.item.CardSwitcher;
import love.marblegate.omnicard.misc.Configuration;
import love.marblegate.omnicard.registry.*;
import net.minecraft.item.ItemModelsProperties;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import software.bernie.geckolib3.GeckoLib;

@Mod("omni_card")
public class OmniCard {
    public static final String MODID = "omni_card";

    public OmniCard() {
        GeckoLib.initialize();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Configuration.COMMON_CONFIG);

        ItemRegistry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        EntityRegistry.ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        EffectRegistry.EFFECT.register(FMLJavaModLoadingContext.get().getModEventBus());
        BlockRegistry.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TileEntityRegistry.TILE_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        SoundRegistry.SOUNDS.register(FMLJavaModLoadingContext.get().getModEventBus());

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // Item Property Override
        ItemModelsProperties.register(ItemRegistry.CARD_STACK.get(),
                CardSwitcher.CARD_CATEGORY_PROPERTY_NAME, (stack, world, living) -> {
                    ICardTypeData cardTypeData = stack.getCapability(CardTypeData.CARD_TYPE_DATA_CAPABILITY, null).orElseThrow(() -> new RuntimeException("Capability of CardTypeData goes wrong!"));
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
    }
}
