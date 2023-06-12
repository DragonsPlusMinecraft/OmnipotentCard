package plus.dragons.omnicard.registry;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import plus.dragons.omnicard.OmniCard;
import plus.dragons.omnicard.block.SpecialCardBlock;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, OmniCard.MODID);
    public static final RegistryObject<Block> SPECIAL_CARD_BLOCK = BLOCKS.register("special_card_block", SpecialCardBlock::new);
}
