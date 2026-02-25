package net.superkat.explosiveenhancement.particles.normal;

import net.minecraft.client.particle.Particle;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.superkat.explosiveenhancement.registry.ParticleTextureRegistry;

@SideOnly(Side.CLIENT)
public class SparkParticle extends Particle {
    public SparkParticle(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double scale, double velY,
                          double velZ, boolean isUnderwater) {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn);
        this.particleMaxAge = Math.max(1, (int) ((5 + Math.floor(scale / 5))
                / net.superkat.explosiveenhancement.config.ExplosiveEnhancementConfig.sparksSpeed));

        float sizeModifier = isUnderwater
                ? net.superkat.explosiveenhancement.config.ExplosiveEnhancementConfig.underwaterSparkSize
                : net.superkat.explosiveenhancement.config.ExplosiveEnhancementConfig.sparkSize;

        if (scale == 0) {
            this.particleScale = sizeModifier;
        } else {
            this.particleScale = sizeModifier * ((float) scale * 0.25f);
        }

        this.particleScale *= (float) net.superkat.explosiveenhancement.config.ExplosiveEnhancementConfig.sparksScale;

        this.motionX = 0;
        this.motionY = 0;
        this.motionZ = 0;
        this.particleAlpha = isUnderwater
                ? (float) net.superkat.explosiveenhancement.config.ExplosiveEnhancementConfig.underwaterSparkOpacity
                : (float) net.superkat.explosiveenhancement.config.ExplosiveEnhancementConfig.sparkOpacity;
        this.setParticleTexture(ParticleTextureRegistry.SPARKS_SPRITES[0]);
    }

    @Override
    public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (this.particleAge++ >= this.particleMaxAge) {
            this.setExpired();
        } else {
            this.move(this.motionX, this.motionY, this.motionZ);

            int frame = (int) (((float) this.particleAge / this.particleMaxAge)
                    * ParticleTextureRegistry.SPARKS_SPRITES.length);
            if (frame >= ParticleTextureRegistry.SPARKS_SPRITES.length) {
                frame = ParticleTextureRegistry.SPARKS_SPRITES.length - 1;
            }
            this.setParticleTexture(ParticleTextureRegistry.SPARKS_SPRITES[frame]);
        }
    }

    @Override
    public int getFXLayer() {
        return 1;
    }

    @Override
    public int getBrightnessForRender(float p_189214_1_) {
        // Sparks glow in the dark like 1.21.1 emissive particles
        return 15728880;
    }

    @Override
    public void renderParticle(net.minecraft.client.renderer.BufferBuilder buffer, net.minecraft.entity.Entity entityIn,
                               float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY,
                               float rotationXZ) {
        if (this.particleAlpha > 0.0F) {
            super.renderParticle(buffer, entityIn, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY,
                    rotationXZ);
        }
    }
}
