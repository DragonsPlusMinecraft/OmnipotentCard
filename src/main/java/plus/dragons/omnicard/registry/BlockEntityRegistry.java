package plus.dragons.omnicard.registry;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import plus.dragons.omnicard.OmniCard;
import plus.dragons.omnicard.blockentity.SpecialCardBlockTileEntity;

public class BlockEntityRegistry {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, OmniCard.MODID);
    public static final RegistryObject<BlockEntityType<SpecialCardBlockTileEntity>> SPECIAL_CARD_BLOCK_TILEENTITY = BLOCK_ENTITIES.register("special_card_block_tileentity", () -> BlockEntityType.Builder.of(SpecialCardBlockTileEntity::new, BlockRegistry.SPECIAL_CARD_BLOCK.get()).build(null));
}
