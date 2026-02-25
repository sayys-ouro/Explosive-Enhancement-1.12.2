package net.superkat.explosiveenhancement.particles.normal;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.superkat.explosiveenhancement.registry.ParticleTextureRegistry;

@SideOnly(Side.CLIENT)
public class FireballParticle extends Particle {
    private final boolean important;

    public FireballParticle(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double velX, double velY,
                            double velZ) {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn);
        this.particleMaxAge = Math.max(1, (int) ((9 + Math.floor(velX / 5))
                / net.superkat.explosiveenhancement.config.ExplosiveEnhancementConfig.fireballSpeed));
        this.particleScale = (float) velX * 10.0f;
        this.important = velY == 1; // Used to emulate the 'isImportant' logic from 1.21.1 if needed
        this.motionX = 0;
        this.motionY = 0;
        this.motionZ = 0;
        this.particleAlpha = velZ == 1.0 ? 0.0F : 1.0F;
        this.setParticleTexture(ParticleTextureRegistry.FIREBALL_SPRITES[0]);
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
                // Spawn Sparks
                if (net.superkat.explosiveenhancement.config.ExplosiveEnhancementConfig.showSparks) {
                    SparkParticle spark = new SparkParticle(this.world, this.posX, this.posY, this.posZ,
                            this.particleScale, this.motionY, this.motionZ, false);
                    Minecraft.getMinecraft().effectRenderer.addEffect(spark);
                }
            }

            int frame = (int) (((float) this.particleAge / this.particleMaxAge)
                    * ParticleTextureRegistry.FIREBALL_SPRITES.length);
            if (frame >= ParticleTextureRegistry.FIREBALL_SPRITES.length) {
                frame = ParticleTextureRegistry.FIREBALL_SPRITES.length - 1;
            }
            this.setParticleTexture(ParticleTextureRegistry.FIREBALL_SPRITES[frame]);
        }
    }

    @Override
    public int getFXLayer() {
        return 1;
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
