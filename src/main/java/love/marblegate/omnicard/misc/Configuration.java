package love.marblegate.omnicard.misc;

import net.minecraftforge.common.ForgeConfigSpec;

public class Configuration {
    public static final ForgeConfigSpec COMMON_CONFIG;

    public static ForgeConfigSpec.BooleanValue HURT_MOUNT, HURT_PET;
    public static ForgeConfigSpec.DoubleValue FLYING_CARD_SPEED, FLYING_CARD_BRIGHTNESS, TRAP_CARD_BRIGHTNESS;
    public static ForgeConfigSpec.IntValue FIELD_CARD_BRIGHTNESS, SEAL_CARD_VALID_RANGE, FLAME_CARD_DAMAGE, FLAME_CARD_FIRE_DURATION, THUNDER_CARD_DAMAGE, BRAMBLE_CARD_DAMAGE, BRAMBLE_CARD_POISON_DURATION, BRAMBLE_CARD_DO_NOT_MOVE_DURATION, EARTH_CARD_FALLING_STONE_DAMAGE, EARTH_CARD_FALLING_STONE_WEAKNESS_DURATION, EARTH_CARD_FALLING_STONE_MOVEMENT_SLOWDOWN_DURATION, FIELD_CARD_HOLY_FLAME_BURNING_RADIUS, PURIFICATION_CARD_DAMAGE, PURIFICATION_CARD_VALID_RANGE, PURIFICATION_CARD_FIRE_DURATION;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.push("flying_card_setting");
        FLYING_CARD_SPEED = builder
                .comment("Flying Speed Of Card.", "Default is 15.0")
                .defineInRange("FLYING_CARD_SPEED", 15.0, 0.1, 99.9);
        HURT_MOUNT = builder
                .comment("Should Flying Card Hurt Your Mount?")
                .define("HURT_MOUNT", false);
        HURT_PET = builder
                .comment("Should Flying Card Hurt Your Pet?")
                .define("HURT_PET", false);
        FLYING_CARD_SPEED = builder
                .comment("Flying Card Brightness.", "Default is 0.2")
                .defineInRange("FLYING_CARD_SPEED", 0.2, 0.0, 1.0);
        builder.pop();

        builder.push("trap_card_setting");
        TRAP_CARD_BRIGHTNESS = builder
                .comment("Trap Card Brightness.", "Default is 0")
                .defineInRange("TRAP_CARD_BRIGHTNESS", 0.0, 0.0, 1.0);
        builder.pop();

        builder.push("field_card_setting");
        FIELD_CARD_BRIGHTNESS = builder
                .comment("Field Card Brightness.", "Default is 3")
                .defineInRange("FIELD_CARD_BRIGHTNESS", 3, 0, 15);
        builder.pop();

        builder.push("seal_card_setting");
        SEAL_CARD_VALID_RANGE = builder
                .comment("Seal Card Valid Range.", "Default is 15*15")
                .defineInRange("SEAL_CARD_VALID_RANGE", 15, 1, 100);
        builder.pop();

        builder.push("flame_card_setting");
        FLAME_CARD_DAMAGE = builder
                .comment("Flame Card Damage.", "Default is 4")
                .defineInRange("FLAME_CARD_DAMAGE", 4, 1, Integer.MAX_VALUE);
        FLAME_CARD_FIRE_DURATION = builder
                .comment("Flame Card Fire Duration.", "Default is 3")
                .defineInRange("FLAME_CARD_FIRE_DURATION", 3, 1, Integer.MAX_VALUE);
        builder.pop();

        builder.push("thunder_card_setting");
        THUNDER_CARD_DAMAGE = builder
                .comment("Thunder Card Damage.", "Default is 10")
                .defineInRange("THUNDER_CARD_DAMAGE", 10, 1, Integer.MAX_VALUE);
        builder.pop();

        builder.push("bramble_card_setting");
        BRAMBLE_CARD_DAMAGE = builder
                .comment("Bramble Card Damage.", "Default is 4")
                .defineInRange("THUNDER_CARD_DAMAGE", 4, 1, Integer.MAX_VALUE);
        BRAMBLE_CARD_POISON_DURATION = builder
                .comment("Bramble Card Poison Effect Duration.", "Default is 80 ticks")
                .defineInRange("BRAMBLE_CARD_POISON_DURATION", 80, 1, Integer.MAX_VALUE);
        BRAMBLE_CARD_DO_NOT_MOVE_DURATION = builder
                .comment("Bramble Card Do Not Move Effect Duration.", "Default is 60 ticks")
                .defineInRange("BRAMBLE_CARD_DO_NOT_MOVE_DURATION", 60, 1, Integer.MAX_VALUE);
        builder.pop();

        builder.push("earth_card_setting");
        EARTH_CARD_FALLING_STONE_DAMAGE = builder
                .comment("Falling Stone (Summoned by Earth Card) Damage.", "Default is 6")
                .defineInRange("EARTH_CARD_FALLING_STONE_DAMAGE", 6, 1, Integer.MAX_VALUE);
        EARTH_CARD_FALLING_STONE_WEAKNESS_DURATION = builder
                .comment("Falling Stone (Summoned by Earth Card) Weakness Effect Duration.", "Default is 100 ticks")
                .defineInRange("EARTH_CARD_FALLING_STONE_WEAKNESS_DURATION", 100, 1, Integer.MAX_VALUE);
        EARTH_CARD_FALLING_STONE_MOVEMENT_SLOWDOWN_DURATION = builder
                .comment("Falling Stone (Summoned by Earth Card) Slowness Effect Duration.", "Default is 100 ticks")
                .defineInRange("EARTH_CARD_FALLING_STONE_MOVEMENT_SLOWDOWN_DURATION", 100, 1, Integer.MAX_VALUE);
        builder.pop();

        builder.push("field_card_setting");
        FIELD_CARD_HOLY_FLAME_BURNING_RADIUS = builder
                .comment("Holy Flame (Triggered by Field Card) Burning Radius.", "Default is 15*15")
                .defineInRange("FIELD_CARD_HOLY_FLAME_BURNING_RADIUS", 15, 1, 100);
        builder.pop();

        builder.push("purification_card_setting");
        PURIFICATION_CARD_DAMAGE = builder
                .comment("Purification Card Damage.", "Default is 20")
                .defineInRange("PURIFICATION_CARD_DAMAGE", 20, 1, Integer.MAX_VALUE);
        PURIFICATION_CARD_VALID_RANGE = builder
                .comment("Purification Card Valid Range.", "Default is 15*15")
                .defineInRange("PURIFICATION_CARD_VALID_RANGE", 15, 1, 100);
        PURIFICATION_CARD_FIRE_DURATION = builder
                .comment("Purification Card Fire Duration.", "Default is 3 seconds")
                .defineInRange("PURIFICATION_CARD_FIRE_DURATION", 3, 1, Integer.MAX_VALUE);
        builder.pop();

        COMMON_CONFIG = builder.build();
    }
}
