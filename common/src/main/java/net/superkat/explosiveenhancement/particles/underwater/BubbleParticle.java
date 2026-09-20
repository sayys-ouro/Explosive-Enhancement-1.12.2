package net.superkat.explosiveenhancement.particles.underwater;

import net.minecraft.block.material.Material;
import net.minecraft.client.particle.Particle;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.superkat.explosiveenhancement.registry.ParticleTextureRegistry;

@SideOnly(Side.CLIENT)
public class BubbleParticle extends Particle {
    private int startingAirTick = 0;
    private int extraTimeBeforePopping;
    private boolean startAirTick = true;

    public BubbleParticle(World worldIn, double x, double y, double z, double velX, double velY, double velZ) {
        super(worldIn, x, y, z);

        this.setSize(0.02F, 0.02F);

        this.particleScale *= this.rand.nextFloat() * 1.5F + 0.2F;

        double theta = this.rand.nextDouble() * 2 * Math.PI;
        double phi = this.rand.nextDouble() * Math.PI;

        this.motionX = Math.sin(phi) * Math.cos(theta) * (this.rand.nextDouble() * .05 + 0.5);
        this.motionY = Math.abs(this.rand.nextDouble() * 0.5 + 0.5);
        this.motionZ = Math.sin(phi) * Math.cos(theta) * (this.rand.nextDouble() * .05 + 0.5);
        this.particleMaxAge = 120 + this.rand.nextInt(41);
        this.extraTimeBeforePopping = 1 + this.rand.nextInt(10);

        this.setParticleTexture(ParticleTextureRegistry.BUBBLE_SPRITE);
    }

    @Override
    public int getFXLayer() {
        return 1;
    }

    @Override
    public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (this.particleMaxAge-- <= 0) { // count up not down !!!! :sob:
            this.setExpired();

            return;
        }

        this.motionY += 0.002D;

        this.move(this.motionX, this.motionY, this.motionZ);

        this.motionY *= 0.82D;

        if (this.particleMaxAge >= this.particleAge * 0.97) {
            this.motionX *= 0.83D;
            this.motionZ *= 0.83D;
        } else {
            this.motionX *= 0.62D;
            this.motionZ *= 0.62D;
        }

        BlockPos pos = new BlockPos(this.posX, this.posY, this.posZ);

        if (this.world.getBlockState(pos).getMaterial() != Material.WATER) {
            this.motionY -= 0.002D;

            if (startAirTick) {
                startingAirTick = this.particleMaxAge;
                this.motionY = 0;
                startAirTick = false;
            }
            if (this.particleMaxAge == startingAirTick - extraTimeBeforePopping) {
                this.setExpired();

                this.world.playSound(this.posX, this.posY, this.posZ, SoundEvents.ENTITY_GENERIC_SPLASH, SoundCategory.AMBIENT, 0.1f, 1f, false);
            }
        }
    }
}
