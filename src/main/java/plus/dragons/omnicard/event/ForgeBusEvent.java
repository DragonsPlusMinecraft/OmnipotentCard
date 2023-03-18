package plus.dragons.omnicard.event;

import plus.dragons.omnicard.blockentity.SpecialCardBlockTileEntity;
import plus.dragons.omnicard.card.BlockCards;
import plus.dragons.omnicard.misc.MiscUtil;
import plus.dragons.omnicard.registry.BlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeBusEvent {
    @SubscribeEvent
    public static void preventHostileNearSEALCard(LivingSpawnEvent.CheckSpawn event) {
        // It only stops natural spawning
        if (event.getSpawnReason() == MobSpawnType.NATURAL) {
            if (MiscUtil.isHostile(event.getEntity(), false)) {
                for (BlockPos pos : BlockPos.betweenClosed(new BlockPos((int) (event.getX() - 8), (int) event.getY() - 8, (int) (event.getZ() - 8)), new BlockPos((int) (event.getX() + 8), (int) (event.getY() + 8), (int) (event.getZ() + 8)))) {
                    BlockState blockState = event.getLevel().getBlockState(pos);
                    if (blockState.getBlock() == BlockRegistry.SPECIAL_CARD_BLOCK.get()) {
                        BlockEntity tileEntity = event.getLevel().getBlockEntity(pos);
                        if (tileEntity instanceof SpecialCardBlockTileEntity) {
                            SpecialCardBlockTileEntity specialCardBlockTile = (SpecialCardBlockTileEntity) tileEntity;
                            if (specialCardBlockTile.getCardType() == BlockCards.SEAL) {
                                event.setResult(net.minecraftforge.eventbus.api.Event.Result.DENY);
                            }
                        }
                    }
                }
            }
        }
    }
}
