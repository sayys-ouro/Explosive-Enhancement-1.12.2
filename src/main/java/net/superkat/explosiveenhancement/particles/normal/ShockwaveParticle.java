package net.superkat.explosiveenhancement.particles.normal;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.superkat.explosiveenhancement.registry.ParticleTextureRegistry;

@SideOnly(Side.CLIENT)
public class ShockwaveParticle extends Particle {

    public ShockwaveParticle(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double velX, double velY,
                             double velZ) {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn);
        this.particleMaxAge = Math.max(1, (int) ((9 + Math.floor(velX / 5))
                / net.superkat.explosiveenhancement.config.ExplosiveEnhancementConfig.shockwaveSpeed));
        this.particleScale = (float) velX * 10.0F * (float) net.superkat.explosiveenhancement.config.ExplosiveEnhancementConfig.shockwaveScale;
        this.motionX = 0;
        this.motionY = 0;
        this.motionZ = 0;
        this.particleAlpha = 1.0F;
        this.setParticleTexture(ParticleTextureRegistry.SHOCKWAVE_SPRITES[0]);
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

            if (this.particleAge >= this.particleMaxAge * 0.65) {
                SparkParticle spark = new SparkParticle(this.world, this.posX, this.posY, this.posZ,
                        this.particleScale / 10.0F, this.motionY, this.motionZ, true);
                Minecraft.getMinecraft().effectRenderer.addEffect(spark);
            }

            int frame = (int) (((float) this.particleAge / this.particleMaxAge)
                    * ParticleTextureRegistry.SHOCKWAVE_SPRITES.length);
            if (frame >= ParticleTextureRegistry.SHOCKWAVE_SPRITES.length) {
                frame = ParticleTextureRegistry.SHOCKWAVE_SPRITES.length - 1;
            }
            this.setParticleTexture(ParticleTextureRegistry.SHOCKWAVE_SPRITES[frame]);
        }
    }

    @Override
    public int getFXLayer() {
        return 1;
    }
}
