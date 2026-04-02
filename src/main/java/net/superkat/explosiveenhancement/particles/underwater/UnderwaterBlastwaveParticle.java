package net.superkat.explosiveenhancement.particles.underwater;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.superkat.explosiveenhancement.registry.ParticleTextureRegistry;

@SideOnly(Side.CLIENT)
public class UnderwaterBlastwaveParticle extends Particle {
    public UnderwaterBlastwaveParticle(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double velX,
                                       double velY, double velZ) {
        super(worldIn, xCoordIn, yCoordIn + 0.5D, zCoordIn);
        this.motionX = 0;
        this.motionY = 0;
        this.motionZ = 0;
        this.particleScale = (float) velX * (float) net.superkat.explosiveenhancement.config.ExplosiveEnhancementConfig.underwaterBlastWaveScale;
        this.particleMaxAge = Math.max(1, (int) ((15 + (Math.floor(velX / 5)))
                / net.superkat.explosiveenhancement.config.ExplosiveEnhancementConfig.underwaterBlastWaveSpeed));
        this.particleAlpha = 1.0F;

        this.setParticleTexture(ParticleTextureRegistry.UNDERWATER_BLASTWAVE_SPRITES[0]);
    }

    @Override
    public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (this.particleAge++ >= this.particleMaxAge) {
            this.setExpired();
        } else {
            int frame = (int) (((float) this.particleAge / this.particleMaxAge)
                    * ParticleTextureRegistry.UNDERWATER_BLASTWAVE_SPRITES.length);
            if (frame >= ParticleTextureRegistry.UNDERWATER_BLASTWAVE_SPRITES.length) {
                frame = ParticleTextureRegistry.UNDERWATER_BLASTWAVE_SPRITES.length - 1;
            }
            this.setParticleTexture(ParticleTextureRegistry.UNDERWATER_BLASTWAVE_SPRITES[frame]);
        }
    }

    @Override
    public int getFXLayer() {
        return 1;
    }

    @Override
    public void renderParticle(BufferBuilder buffer, Entity entityIn, float partialTicks, float rotationX,
                               float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
        float f4 = this.particleScale;
        float f = (float) (this.prevPosX + (this.posX - this.prevPosX) * (double) partialTicks - interpPosX);
        float f1 = (float) (this.prevPosY + (this.posY - this.prevPosY) * (double) partialTicks - interpPosY);
        float f2 = (float) (this.prevPosZ + (this.posZ - this.prevPosZ) * (double) partialTicks - interpPosZ);
        float u1 = this.particleTexture.getMinU();
        float u2 = this.particleTexture.getMaxU();
        float v1 = this.particleTexture.getMinV();
        float v2 = this.particleTexture.getMaxV();
        int i = this.getBrightnessForRender(partialTicks);
        int j = i >> 16 & 65535;
        int k = i & 65535;

        buffer.pos((double) f - f4, (double) f1, (double) f2 - f4).tex((double) u2, (double) v2)
                .color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k)
                .endVertex();
        buffer.pos((double) f - f4, (double) f1, (double) f2 + f4).tex((double) u2, (double) v1)
                .color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k)
                .endVertex();
        buffer.pos((double) f + f4, (double) f1, (double) f2 + f4).tex((double) u1, (double) v1)
                .color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k)
                .endVertex();
        buffer.pos((double) f + f4, (double) f1, (double) f2 - f4).tex((double) u1, (double) v2)
                .color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k)
                .endVertex();
    }
}
