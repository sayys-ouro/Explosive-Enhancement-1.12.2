package net.superkat.explosiveenhancement.config;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.superkat.explosiveenhancement.Reference;

@Config(modid = Reference.MOD_ID, name = Reference.MOD_NAME)
public class ExplosiveEnhancementConfig {
    @Config.Comment("If set to false, the mod won't replace vanilla explosion particles.")
    public static boolean modEnabled = true;

    @Config.Comment("If set to false, the mod won't spawn underwater specific particles.")
    public static boolean underwaterExplosions = true;

    @Config.Comment("Whether to show the custom blast wave particle. Default: true")
    public static boolean showBlastWave = true;

    @Config.Comment("Whether to show the custom fireball particle. Default: true")
    public static boolean showFireball = true;

    @Config.Comment("Whether to show the mushroom cloud smoke effect. Default: true")
    public static boolean showMushroomCloud = true;

    @Config.Comment("Whether to show the custom sparks particles. Default: true")
    public static boolean showSparks = true;

    @Config.Comment("Size of the sparks particles. Default: 5.3")
    public static float sparkSize = 5.3f;

    @Config.Comment("Opacity of the sparks particles. Default: 0.70")
    @Config.RangeDouble(min = 0.0, max = 1.0)
    public static double sparkOpacity = 0.70;

    @Config.Comment("Whether to KEEP showing the default vanilla explosion particles alongside the new ones. Default: false")
    public static boolean showDefaultExplosion = false;

    @Config.Comment("Whether to KEEP showing the default vanilla smoke particles alongside the new ones. Default: false")
    public static boolean showDefaultSmoke = false;

    @Config.Comment("Whether to show the custom underwater blast wave particle. Default: true")
    public static boolean showUnderwaterBlastWave = true;

    @Config.Comment("Whether to show the custom underwater shockwave particle. Default: true")
    public static boolean showShockwave = true;

    @Config.Comment("Whether to show custom underwater sparks. Default: false")
    public static boolean showUnderwaterSparks = false;

    @Config.Comment("Size of the underwater sparks particles. Default: 4.0")
    public static float underwaterSparkSize = 4.0f;

    @Config.Comment("Opacity of the underwater sparks particles. Default: 0.30")
    @Config.RangeDouble(min = 0.0, max = 1.0)
    public static double underwaterSparkOpacity = 0.30;

    @Config.Comment("If set to false, the bubble particles won't spawn.")
    public static boolean showBubbles = true;

    @Config.Comment("Amount of bubbles to spawn in underwater explosions. Default: 50")
    @Config.RangeInt(min = 0, max = 1000)
    public static int bubbleAmount = 50;

    @Config.Comment("Whether to KEEP showing the default vanilla explosion particles underwater. Default: false")
    public static boolean showDefaultExplosionUnderwater = false;

    @Config.Comment("Whether to KEEP showing the default vanilla smoke particles underwater. Default: false")
    public static boolean showDefaultSmokeUnderwater = false;

    @Config.Comment("Animation speed multiplier for the blast wave particle. Default: 1.0 (Higher = Faster)")
    @Config.RangeDouble(min = 0.1, max = 10.0)
    public static double blastWaveSpeed = 1.0;

    @Config.Comment("Animation speed multiplier for the fireball particle. Default: 1.0 (Higher = Faster)")
    @Config.RangeDouble(min = 0.1, max = 10.0)
    public static double fireballSpeed = 1.0;

    @Config.Comment("Animation speed multiplier for the smoke particles. Default: 1.0 (Higher = Faster)")
    @Config.RangeDouble(min = 0.1, max = 10.0)
    public static double smokeSpeed = 1.0;

    @Config.Comment("Animation speed multiplier for the sparks particles. Default: 1.0 (Higher = Faster)")
    @Config.RangeDouble(min = 0.1, max = 10.0)
    public static double sparksSpeed = 1.0;

    @Config.Comment("Animation speed multiplier for the underwater blast wave particle. Default: 1.0 (Higher = Faster)")
    @Config.RangeDouble(min = 0.1, max = 10.0)
    public static double underwaterBlastWaveSpeed = 1.0;

    @Config.Comment("Animation speed multiplier for the underwater shockwave particle. Default: 1.0 (Higher = Faster)")
    @Config.RangeDouble(min = 0.1, max = 10.0)
    public static double shockwaveSpeed = 1.0;

    @Config.Comment("Scale multiplier for the blast wave particle. Default: 1.0 (Higher = Larger)")
    @Config.RangeDouble(min = 0.1, max = 10.0)
    public static double blastWaveScale = 1.0;

    @Config.Comment("Scale multiplier for the fireball particle. Default: 1.0 (Higher = Larger)")
    @Config.RangeDouble(min = 0.1, max = 10.0)
    public static double fireballScale = 1.0;

    @Config.Comment("Scale multiplier for the smoke particles. Default: 1.0 (Higher = Larger)")
    @Config.RangeDouble(min = 0.1, max = 10.0)
    public static double smokeScale = 1.0;

    @Config.Comment("Scale multiplier for the sparks particles. Default: 1.0 (Higher = Larger)")
    @Config.RangeDouble(min = 0.1, max = 10.0)
    public static double sparksScale = 1.0;

    @Config.Comment("Scale multiplier for the underwater blast wave particle. Default: 1.0 (Higher = Larger)")
    @Config.RangeDouble(min = 0.1, max = 10.0)
    public static double underwaterBlastWaveScale = 1.0;

    @Config.Comment("Scale multiplier for the underwater shockwave particle. Default: 1.0 (Higher = Larger)")
    @Config.RangeDouble(min = 0.1, max = 10.0)
    public static double shockwaveScale = 1.0;

    @Config.Comment("Whether the size of the explosion matters for the visual scale of particles. Default: true")
    public static boolean dynamicSize = true;

    @Config.Comment("Whether the size of the explosion matters for the visual scale of underwater particles. Default: true")
    public static boolean dynamicUnderwater = true;

    @Config.Comment("Whether to add extra power (size) to the visual explosion artificially. Default: false")
    public static boolean extraPower = false;

    @Config.Comment("Amount of extra power to add to big explosions when extraPower is true. Default: 0.0")
    public static float bigExtraPower = 0.0f;

    @Config.Comment("Amount of extra power to add to small explosions when extraPower is true. Default: 0.0")
    public static float smallExtraPower = 0.0f;

    @Config.Comment("Attempt to offset small 1-power explosions slightly downwards to look better. Default: true")
    public static boolean attemptBetterSmallExplosions = true;

    @Config.Comment("The Y offset to apply to small 1-power explosions. Default: -0.5")
    public static double smallExplosionYOffset = -0.5;

    @Config.Comment("Whether the main explosion particles should be brightly emissive (glow in the dark). Default: true")
    public static boolean emissiveExplosion = true;

    @Config.Comment("Whether the underwater explosion particles should be brightly emissive (glow in the dark). Default: true")
    public static boolean emissiveWaterExplosion = true;

    @Config.Comment("Always render particles regardless of distance/culling. Default: false")
    public static boolean alwaysShow = false;

    @Config.Comment("If set to true, the mod will log debug information.")
    public static boolean debugLogs = false;

    @Mod.EventBusSubscriber(modid = Reference.MOD_ID)
    private static class EventHandler {
        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(Reference.MOD_ID)) {
                ConfigManager.sync(Reference.MOD_ID, Config.Type.INSTANCE);
            }
        }
    }
}
