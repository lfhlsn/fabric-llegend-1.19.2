package net.lfhlsn.llegend.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.DragonFireballEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ModDragonFireballEntity extends DragonFireballEntity {
    public ModDragonFireballEntity(World world, LivingEntity owner) {
        super(world, owner, 0, 0, 0);
    }

    @Override
    public void tick(){
        Entity entity = this.getOwner();
        if (this.world.isClient || (entity == null || !entity.isRemoved()) && this.world.isChunkLoaded(this.getBlockPos())) {
            super.tick();
            if (this.isBurning()) {
                this.setOnFireFor(1);
            }

            HitResult hitResult = ProjectileUtil.getCollision(this, this::canHit);
            if (hitResult.getType() != HitResult.Type.MISS) {
                this.onCollision(hitResult);
            }

            this.checkBlockCollision();
            Vec3d velocity = this.getVelocity();
            double x = this.getX() + velocity.x;
            double y = this.getY() + velocity.y;
            double z = this.getZ() + velocity.z;
            ProjectileUtil.setRotationFromVelocity(this, 0.2F);
            float drag = this.getDrag();
            if (this.isTouchingWater()) {
                for(int i = 0; i < 4; ++i) {
                    this.world.addParticle(ParticleTypes.BUBBLE, x - velocity.x * 0.25, y - velocity.y * 0.25, z - velocity.z * 0.25, velocity.x, velocity.y, velocity.z);
                }

                drag = 0.8F;
            }

            this.setVelocity(velocity.multiply(1.5*drag));
            this.world.addParticle(this.getParticleType(), x, y + 0.5, z, 0.0, 0.0, 0.0);
            this.setPosition(x, y, z);
        } else {
            this.discard();
        }
    }
}
