package net.superkat.explosiveenhancement.mixin.mod.oceanicexpanse;

import com.sirsquidly.oe.util.ExplosionUnderwater;
import net.minecraft.block.material.Material;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.superkat.explosiveenhancement.ExplosiveHandler;
import net.superkat.explosiveenhancement.config.ExplosiveEnhancementConfig;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ExplosionUnderwater.class)
public class OEExplosionUnderwaterMixin {
    @Shadow
    @Final
    private World world;

    @Shadow
    @Final
    private double x;

    @Shadow
    @Final
    private double y;

    @Shadow
    @Final
    private double z;

    @Shadow
    @Final
    private float size;

    @Shadow
    @Final
    private boolean damagesTerrain;

    @Redirect(method = "doExplosionParticles", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;spawnParticle(Lnet/minecraft/util/EnumParticleTypes;DDDDDD[I)V"))
    private void redirectSpawnParticle(World instance, EnumParticleTypes particleType, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed, int[] parameters) {
        if (instance.isRemote && ExplosiveEnhancementConfig.modEnabled) {
            if (particleType == EnumParticleTypes.EXPLOSION_LARGE || particleType == EnumParticleTypes.EXPLOSION_HUGE) {
                boolean isUnderWater = instance.getBlockState(new BlockPos(this.x, this.y, this.z)).getMaterial() == Material.WATER;

                ExplosiveHandler.spawnParticles(instance, this.x, this.y, this.z, this.size, isUnderWater, this.damagesTerrain);

                boolean shouldShowVanillaBase = isUnderWater ? ExplosiveEnhancementConfig.showDefaultExplosionUnderwater : ExplosiveEnhancementConfig.showDefaultExplosion;

                if (!shouldShowVanillaBase) {
                    return;
                }
            } else if (particleType == EnumParticleTypes.EXPLOSION_NORMAL || particleType == EnumParticleTypes.WATER_BUBBLE) {
                boolean isUnderWater = instance.getBlockState(new BlockPos(this.x, this.y, this.z)).getMaterial() == Material.WATER;

                boolean shouldShowVanillaSmoke = isUnderWater ? ExplosiveEnhancementConfig.showDefaultSmokeUnderwater : ExplosiveEnhancementConfig.showDefaultSmoke;

                if (!shouldShowVanillaSmoke) {
                    return;
                }
            }
        }

        instance.spawnParticle(particleType, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed, parameters);
    }
}
