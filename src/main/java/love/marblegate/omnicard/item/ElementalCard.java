package love.marblegate.omnicard.item;

import love.marblegate.omnicard.card.CommonCard;
import love.marblegate.omnicard.entity.CardTrapEntity;
import love.marblegate.omnicard.entity.FlyingCardEntity;
import love.marblegate.omnicard.misc.Configuration;
import love.marblegate.omnicard.misc.MiscUtil;
import love.marblegate.omnicard.misc.ModGroup;
import love.marblegate.omnicard.misc.ThemeColor;
import love.marblegate.omnicard.registry.SoundRegistry;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.*;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.List;

public class ElementalCard extends Item {
    public final CommonCard card;

    public ElementalCard(CommonCard card) {
        super(new Properties().tab(ModGroup.GENERAL));
        this.card = card;
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (!worldIn.isClientSide()) {
            Vector3d vector3d = player.getViewVector(1.0F);

            double x = (vector3d.x * Configuration.FLYING_CARD_SPEED.get());
            double y = (vector3d.y * Configuration.FLYING_CARD_SPEED.get());
            double z = (vector3d.z * Configuration.FLYING_CARD_SPEED.get());

            worldIn.playSound((PlayerEntity) null, player.getX(), player.getY(), player.getZ(), SoundRegistry.THROW_CARD.get(), SoundCategory.PLAYERS, 1.0F, 1.0F);

            FlyingCardEntity flyingCardEntity = new FlyingCardEntity(player, x, y, z, worldIn, card);
            flyingCardEntity.setPos(player.getX(), player.getY() + player.getEyeHeight(), player.getZ());
            flyingCardEntity.setOwner(player);
            worldIn.addFreshEntity(flyingCardEntity);

            double d0 = -MathHelper.sin(player.yRot * ((float)Math.PI / 180F));
            double d1 = MathHelper.cos(player.yRot * ((float)Math.PI / 180F));
            ((ServerWorld)worldIn).sendParticles(ParticleTypes.SWEEP_ATTACK, player.getX() + d0, player.getY(0.5D), player.getZ() + d1, 0, d0, 0.0D, d1, 0.0D);

            if (!player.abilities.instabuild) {
                itemStack.shrink(1);
            }
        }
        return ActionResult.sidedSuccess(itemStack, worldIn.isClientSide());
    }

    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
        if (context.getPlayer().isShiftKeyDown()) {
            if (context.getClickedFace() == Direction.UP) {
                if (!context.getLevel().isClientSide()) {
                    CardTrapEntity cardTrapEntity = new CardTrapEntity(context.getLevel(), card);
                    cardTrapEntity.setOwner(context.getPlayer());
                    cardTrapEntity.setPos(context.getClickLocation().x, context.getClickLocation().y, context.getClickLocation().z);
                    cardTrapEntity.yRot = context.getPlayer().yRot;
                    context.getLevel().addFreshEntity(cardTrapEntity);

                    if (!context.getPlayer().abilities.instabuild) {
                        stack.shrink(1);
                    }
                }
                return ActionResultType.SUCCESS;
            } else {
                return ActionResultType.FAIL;
            }
        } else {
            return super.onItemUseFirst(stack, context);
        }
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable World world, List<ITextComponent> tooltips, ITooltipFlag iTooltipFlag) {
        tooltips.add(MiscUtil.tooltip("tooltip.omni_card.elemental_card.function." + card.getCardName(), ThemeColor.HINT));
        tooltips.add(MiscUtil.tooltipBold("tooltip.omni_card.elemental_card.operation_throw", ThemeColor.OPERATION)
                .append(MiscUtil.tooltip("tooltip.omni_card.elemental_card.to_throw", ThemeColor.OPERATION_EXPLAIN)));
        tooltips.add(MiscUtil.tooltipBold("tooltip.omni_card.elemental_card.operation_place", ThemeColor.OPERATION)
                .append(MiscUtil.tooltip("tooltip.omni_card.elemental_card.to_place", ThemeColor.OPERATION_EXPLAIN)));
        super.appendHoverText(itemStack, world, tooltips, iTooltipFlag);
    }
}
