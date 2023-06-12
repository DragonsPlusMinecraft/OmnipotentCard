package plus.dragons.omnicard.registry;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import plus.dragons.omnicard.OmniCard;
import plus.dragons.omnicard.card.BlockCards;
import plus.dragons.omnicard.card.CommonCards;
import plus.dragons.omnicard.item.BlankCard;
import plus.dragons.omnicard.item.CardSwitcher;
import plus.dragons.omnicard.item.ElementalCard;
import plus.dragons.omnicard.item.PlaceableSpecialCard;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, OmniCard.MODID);

    public static final RegistryObject<Item> CARD_STACK = ITEMS.register("card_stack", CardSwitcher::new);
    public static final RegistryObject<Item> BLANK_CARD = ITEMS.register("blank_card", BlankCard::new);
    public static final RegistryObject<Item> PROTOTYPE_CORE = ITEMS.register("prototype_core", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FLAME_CARD = ITEMS.register("flame_card", () -> new ElementalCard(CommonCards.FLAME));
    public static final RegistryObject<Item> TORRENT_CARD = ITEMS.register("torrent_card", () -> new ElementalCard(CommonCards.TORRENT));
    public static final RegistryObject<Item> THUNDER_CARD = ITEMS.register("thunder_card", () -> new ElementalCard(CommonCards.THUNDER));
    public static final RegistryObject<Item> BRAMBLE_CARD = ITEMS.register("bramble_card", () -> new ElementalCard(CommonCards.BRAMBLE));
    public static final RegistryObject<Item> EARTH_CARD = ITEMS.register("earth_card", () -> new ElementalCard(CommonCards.EARTH));
    public static final RegistryObject<Item> END_CARD = ITEMS.register("end_card", () -> new ElementalCard(CommonCards.END));

    public static final RegistryObject<Item> FIELD_CARD = ITEMS.register("field_card", () -> new PlaceableSpecialCard(BlockCards.FIELD));
    public static final RegistryObject<Item> PURIFICATION_CARD = ITEMS.register("purification_card", () -> new PlaceableSpecialCard(BlockCards.PURIFICATION));
    public static final RegistryObject<Item> SEAL_CARD = ITEMS.register("seal_card", () -> new PlaceableSpecialCard(BlockCards.SEAL));
    public static final RegistryObject<Item> SUNNY_CARD = ITEMS.register("sunny_card", () -> new PlaceableSpecialCard(BlockCards.SUNNY));
    public static final RegistryObject<Item> RAINY_CARD = ITEMS.register("rainy_card", () -> new PlaceableSpecialCard(BlockCards.RAINY));
    public static final RegistryObject<Item> THUNDERSTORM_CARD = ITEMS.register("thunderstorm_card", () -> new PlaceableSpecialCard(BlockCards.THUNDERSTORM));
    public static final RegistryObject<Item> BLOOM_CARD = ITEMS.register("bloom_card", () -> new PlaceableSpecialCard(BlockCards.BLOOM));

    public static final RegistryObject<Item> FIELD_CORE = ITEMS.register("field_core", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PURIFICATION_CORE = ITEMS.register("purification_core", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SEAL_CORE = ITEMS.register("seal_core", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SUNNY_CORE = ITEMS.register("sunny_core", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAINY_CORE = ITEMS.register("rainy_core", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> THUNDERSTORM_CORE = ITEMS.register("thunderstorm_core", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BLOOM_CORE = ITEMS.register("bloom_core", () -> new Item(new Item.Properties()));


}
