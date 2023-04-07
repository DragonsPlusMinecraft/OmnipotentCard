package love.marblegate.omnicard.event;

import love.marblegate.omnicard.block.tileentity.SpecialCardBlockTileEntity;
import love.marblegate.omnicard.card.BlockCards;
import love.marblegate.omnicard.misc.Configuration;
import love.marblegate.omnicard.misc.MiscUtil;
import love.marblegate.omnicard.registry.BlockRegistry;
import net.minecraft.entity.SpawnReason;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.stream.StreamSupport;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeBusEvent {
    @SubscribeEvent
    public static void preventHostileNearSEALCard(LivingSpawnEvent.CheckSpawn event) {
        if (event.getSpawnReason() != SpawnReason.NATURAL || !MiscUtil.isHostile(event.getEntityLiving(), false)) return;
        float radius = (float) (Configuration.SEAL_CARD_VALID_RANGE.get() + 1) / 2;
        if (StreamSupport.stream(BlockPos.betweenClosed(new BlockPos(event.getX() - radius, (int) event.getY() - radius, event.getZ() - radius), new BlockPos(event.getX() + radius, event.getY() + radius, event.getZ() + radius)).spliterator(), false).anyMatch(pos -> {
            if (event.getWorld().getBlockState(pos).getBlock() == BlockRegistry.SPECIAL_CARD_BLOCK.get()) {
                TileEntity tileEntity = event.getWorld().getBlockEntity(pos);
                if (tileEntity instanceof SpecialCardBlockTileEntity) return ((SpecialCardBlockTileEntity) tileEntity).getCardType() == BlockCards.SEAL;
            }
            return false;
        })) {
            event.setResult(Event.Result.DENY);
        }
    }
}
