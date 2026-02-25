package net.superkat.explosiveenhancement;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.superkat.explosiveenhancement.config.ExplosiveEnhancementConfig;
import net.superkat.explosiveenhancement.particles.normal.BlastWaveParticle;
import net.superkat.explosiveenhancement.particles.normal.FireballParticle;
import net.superkat.explosiveenhancement.particles.normal.ShockwaveParticle;
import net.superkat.explosiveenhancement.particles.normal.SmokeParticle;
import net.superkat.explosiveenhancement.particles.underwater.BubbleParticle;
import net.superkat.explosiveenhancement.particles.underwater.UnderwaterBlastwaveParticle;

public class ExplosiveHandler {
    public static void spawnParticles(
            World world,
            double x, double y, double z,
            float power,
            boolean isUnderWater,
            boolean didDestroyBlocks
    ) {
        if (ExplosiveEnhancementConfig.debugLogs) {
            ExplosiveEnhancement.logger.info("ExplosiveEnhancement Mixin has triggered particle spawn!");
        }

        if (!ExplosiveEnhancementConfig.modEnabled) {
            showDefaultParticles(world, x, y, z, power, didDestroyBlocks);

            return;
        }

        float actualPower = isUnderWater ? (ExplosiveEnhancementConfig.dynamicUnderwater ? power : 4.0F)
                : (ExplosiveEnhancementConfig.dynamicSize ? power : 4.0F);
        y = ExplosiveEnhancementConfig.attemptBetterSmallExplosions && power == 1
                ? y + ExplosiveEnhancementConfig.smallExplosionYOffset
                : y;

        float blastwavePower = actualPower * 1.75f;
        float fireballPower = actualPower * 1.25f;

        Minecraft mc = Minecraft.getMinecraft();

        if (isUnderWater && ExplosiveEnhancementConfig.underwaterExplosions) {
            if (ExplosiveEnhancementConfig.showUnderwaterBlastWave) {
                mc.effectRenderer
                        .addEffect(new UnderwaterBlastwaveParticle(world, x, y + 0.5, z, blastwavePower, 0, 0));
            }

            if (ExplosiveEnhancementConfig.showShockwave) {
                mc.effectRenderer.addEffect(new ShockwaveParticle(world, x, y + 0.5, z, fireballPower, 1, 0));
            } else if (ExplosiveEnhancementConfig.showUnderwaterSparks) {
                // Spawn invisible shockwave just to spawn underwater sparks
                mc.effectRenderer.addEffect(new ShockwaveParticle(world, x, y + 0.5, z, fireballPower, 1, 1));
            }

            if (ExplosiveEnhancementConfig.showBubbles) {
                for (int total = ExplosiveEnhancementConfig.bubbleAmount; total > 0; total--) {
                    mc.effectRenderer
                            .addEffect(new BubbleParticle(world, x, y, z, nextBetween(1, 7) * 0.3 * nextBetween(-1, 1),
                                    nextBetween(1, 10) * 0.1, nextBetween(1, 7) * 0.3 * nextBetween(-1, 1)));
                }
            }
        } else {
            if (ExplosiveEnhancementConfig.showBlastWave) {
                mc.effectRenderer.addEffect(new BlastWaveParticle(world, x, y, z, blastwavePower, 0, 0));
            }

            if (ExplosiveEnhancementConfig.showFireball) {
                mc.effectRenderer.addEffect(new FireballParticle(world, x, y + 0.5, z, fireballPower, 1, 0));
            } else if (ExplosiveEnhancementConfig.showSparks) {
                // Spawn invisible fireball just to spawn sparks later
                mc.effectRenderer.addEffect(new FireballParticle(world, x, y + 0.5, z, fireballPower, 1, 1));
            }

            if (ExplosiveEnhancementConfig.showMushroomCloud) {
                float reducedPower = actualPower * 0.4f;
                float xzVel = 0.15f * actualPower * 0.5f;
                float velY = reducedPower / 1.85f;

                mc.effectRenderer
                        .addEffect(new SmokeParticle(world, x, y, z, 0, (actualPower * 0.25) / 1.85f, 0, actualPower));
                mc.effectRenderer.addEffect(new SmokeParticle(world, x, y, z, 0, velY, 0, actualPower));
                mc.effectRenderer.addEffect(new SmokeParticle(world, x, y, z, xzVel, velY, 0, actualPower));
                mc.effectRenderer.addEffect(new SmokeParticle(world, x, y, z, -xzVel, velY, 0, actualPower));
                mc.effectRenderer.addEffect(new SmokeParticle(world, x, y, z, 0, velY, xzVel, actualPower));
                mc.effectRenderer.addEffect(new SmokeParticle(world, x, y, z, 0, velY, -xzVel, actualPower));
            }
        }
    }

    private static void showDefaultParticles(World world, double x, double y, double z, float power,
                                             boolean didDestroyBlocks) {
        if (!(power < 2.0f) && didDestroyBlocks) {
            world.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, x, y, z, 1.0, 0.0, 0.0);
        } else {
            world.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, x, y, z, 1.0, 0.0, 0.0);
        }
    }

    private static int nextBetween(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
}
