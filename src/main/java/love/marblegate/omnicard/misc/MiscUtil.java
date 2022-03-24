package love.marblegate.omnicard.misc;

import love.marblegate.omnicard.registry.EffectRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.monster.piglin.PiglinEntity;
import net.minecraft.particles.IParticleData;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;


public class MiscUtil {
    public static boolean canTeleportTo(BlockPos pos, LivingEntity livingEntity, boolean isPlayer) {
        World level = livingEntity.level;
        if (level.getBlockState(pos).getBlock().isPossibleToRespawnInThis() || level.getBlockState(pos.above()).getBlock().isPossibleToRespawnInThis()) {
            if (isPlayer) {
                for (int i = -1; i > -6; i--) {
                    if (level.getBlockState(pos.below(i)).getMaterial().isSolid()) {
                        return true;
                    }
                }
                return false;
            }
            return true;
        }
        return false;
    }

    public static void applyKnockback(LivingEntity livingEntity, double x, double y, double z) {
        livingEntity.setDeltaMovement(livingEntity.getDeltaMovement().add(x, y, z));
        livingEntity.hurtMarked = true;
        livingEntity.hasImpulse = true;
    }

    public static <T extends IParticleData> void addParticle(ServerWorld world, T particle, double x, double y, double z, double xDist, double yDist, double zDist, double maxSpeed, int count) {
        world.sendParticles(particle, x, y, z, count, xDist, yDist, zDist, maxSpeed);
    }

    public static BlockPos getBlockRandomPos(int x, int y, int z, int radius, Random random) {
        return getBlockRandomPos(x, y, z, radius, radius, random);
    }

    public static BlockPos getBlockRandomPos(int x, int y, int z, int width, int height, Random random) {
        return new BlockPos(x + random.nextInt(2 * width + 1) - width,
                y + random.nextInt(2 * height + 1) - height,
                z + random.nextInt(2 * width + 1) - width);
    }

    public static boolean isHostile(LivingEntity livingEntity, boolean restrictMode) {
        if (restrictMode) {
            return (livingEntity instanceof MonsterEntity && !(livingEntity instanceof PiglinEntity) && !(livingEntity instanceof SpiderEntity) && !(livingEntity instanceof EndermanEntity)) ||
                    livingEntity instanceof SlimeEntity ||
                    livingEntity instanceof FlyingEntity ||
                    livingEntity instanceof HoglinEntity ||
                    livingEntity instanceof EnderDragonEntity;
        } else {
            return livingEntity instanceof MonsterEntity ||
                    livingEntity instanceof SlimeEntity ||
                    livingEntity instanceof FlyingEntity ||
                    livingEntity instanceof HoglinEntity ||
                    livingEntity instanceof EnderDragonEntity;
        }
    }

    public static void applyHolyFlameInArea(ServerWorld world, AxisAlignedBB aabb, int ticks) {
        world.getEntities((Entity) null, aabb, entity -> entity instanceof LivingEntity && isHostile((LivingEntity) entity, false))
                .forEach(entity -> ((LivingEntity) entity).addEffect(new EffectInstance(EffectRegistry.HOLY_FLAME.get(), ticks)));
    }

    public static void applyHugeDamageThenApplyFireInArea(ServerWorld world, AxisAlignedBB aabb, float damage, int fireSecond) {
        world.getEntities((Entity) null, aabb, entity -> entity instanceof LivingEntity && isHostile((LivingEntity) entity, false))
                .forEach(entity -> {
                    entity.hurt(DamageSource.MAGIC,damage);
                    entity.setSecondsOnFire(fireSecond);
                });
    }

    public static AxisAlignedBB buildAABB(BlockPos pos, int width, int height) {
        return new AxisAlignedBB(pos.getX() - width, pos.getY() - height, pos.getZ() - width, pos.getX() + width, pos.getY() + height, pos.getZ() + width);
    }

    public static AxisAlignedBB buildAABB(BlockPos pos, int radius) {
        return buildAABB(pos, radius, radius);
    }

    public static IFormattableTextComponent tooltip(String id, Color color) {
        return new TranslationTextComponent(id).setStyle(Style.EMPTY.withColor(color).withBold(false));
    }

    public static IFormattableTextComponent tooltipBold(String id, Color color) {
        return new TranslationTextComponent(id).setStyle(Style.EMPTY.withColor(color).withBold(true));
    }

}
