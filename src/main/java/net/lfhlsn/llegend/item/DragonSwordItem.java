package net.lfhlsn.llegend.item;

import net.lfhlsn.llegend.entity.IPlayerSwordCoolDown;
import net.lfhlsn.llegend.entity.ModDragonFireballEntity;
import net.lfhlsn.llegend.item.skill.SwordWithSkill_1;
import net.lfhlsn.llegend.item.skill.SwordWithSkill_2;
import net.lfhlsn.llegend.item.skill.SwordWithSkill_3;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Random;


public class DragonSwordItem extends SwordItem implements SwordWithSkill_1, SwordWithSkill_2, SwordWithSkill_3 {
    public DragonSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damage(1, attacker, (e) -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        if(target.isAlive())
        {
            double d = (attacker.getBoundingBox().minX + attacker.getBoundingBox().maxX) / 2.0;
            double e = (attacker.getBoundingBox().minZ + attacker.getBoundingBox().maxZ) / 2.0;
            double f = target.getX() - d;
            double g = target.getZ() - e;
            double h = Math.max(f * f + g * g, 0.1);
            target.addVelocity(f / h * 4.0, 1, g / h * 4.0);
        }
        return true;
    }

    @Override
    public void skill_1(World world, ServerPlayerEntity player, ItemStack itemStack){
        if(((IPlayerSwordCoolDown)player).getDragonSwordCoolDown_1()==0){
            world.playSound(player,new BlockPos(player.getPos()),SoundEvents.ENTITY_ENDER_DRAGON_GROWL, SoundCategory.PLAYERS,5.0F,player.getPitch());
            world.playSound(player,new BlockPos(player.getPos()),SoundEvents.ENTITY_ENDER_DRAGON_SHOOT, SoundCategory.PLAYERS,5.0F,player.getPitch());
            if (!world.isClient)
            {
                itemStack.damage(50,player,e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
                ModDragonFireballEntity fireball=new ModDragonFireballEntity(world,player);
                fireball.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, 3.0F, 1.0F);
                world.spawnEntity(fireball);
                ((IPlayerSwordCoolDown)player).setDragonSwordCoolDown_1(((IPlayerSwordCoolDown)player).dragonSwordMaxCoolDown_1);
            }
        }
        else player.sendMessage(Text.translatable("event.llegend.sword_skill.warning_1").append(String.valueOf((((IPlayerSwordCoolDown)player).getDragonSwordCoolDown_1())/20)).append(Text.translatable("event.llegend.sword_skill.warning_2")).formatted(Formatting.RED));
    }

    @Override
    public void skill_2(World world, ServerPlayerEntity player, ItemStack itemStack) {
        if(((IPlayerSwordCoolDown)player).getDragonSwordCoolDown_2()==0){
            if (!world.isClient){
                itemStack.damage(100,player,e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH,1600,2));
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST,1600,4));
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION,1600,1));
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE,1600,0));
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE,1600,0));
                ((IPlayerSwordCoolDown)player).setDragonSwordCoolDown_2(((IPlayerSwordCoolDown)player).dragonSwordMaxCoolDown_2);
            }
        }
        else player.sendMessage(Text.translatable("event.llegend.sword_skill.warning_1").append(String.valueOf((((IPlayerSwordCoolDown)player).getDragonSwordCoolDown_2())/20)).append(Text.translatable("event.llegend.sword_skill.warning_2")).formatted(Formatting.RED));
    }

    @Override
    public void skill_3(World world, ServerPlayerEntity player, ItemStack itemStack) {
        if(((IPlayerSwordCoolDown)player).getDragonSwordCoolDown_3()==0)
        {
            Random random=new Random();
            Vec3d vec3d =player.getPos();
            double x= vec3d.x;
            double y= vec3d.y;
            double z= vec3d.z;
            if(world.isClient){
                for (int i = 0; i < 36; i++) {
                    world.addParticle(ParticleTypes.PORTAL, x+Math.cos(Math.PI*i/18),y,z+Math.sin(Math.PI*i/18),Math.cos(Math.PI*i/18),0.2,Math.sin(Math.PI*i/18));
                    world.addParticle(ParticleTypes.PORTAL, x+2*Math.cos(Math.PI*i/18),y,z+2*Math.sin(Math.PI*i/18),Math.cos(Math.PI*i/18),0.2,Math.sin(Math.PI*i/18));
                    world.addParticle(ParticleTypes.PORTAL, x+3*Math.cos(Math.PI*i/18),y,z+3*Math.sin(Math.PI*i/18),Math.cos(Math.PI*i/18),0.2,Math.sin(Math.PI*i/18));
                }
                for (int i = 0; i < 10; i++) {
                    world.addParticle(ParticleTypes.PORTAL, x,y+2+(double)i/5,z,0,0,0);
                }
            }
            else {
                itemStack.damage(300,player,e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
                for (int i = 0; i < 8; i++) {
                    EndermanEntity entity=new EndermanEntity(EntityType.ENDERMAN,world);
                    entity.setPos(x+3*Math.cos(Math.PI*i/4),y,z+3*Math.sin(Math.PI*i/4));
                    if(random.nextInt(10)==0) entity.setCarriedBlock(Blocks.DRAGON_HEAD.getDefaultState());
                    else if(random.nextInt(100)==0) entity.setCarriedBlock(Blocks.DRAGON_EGG.getDefaultState());
                    entity.setTarget(player);
                    world.spawnEntity(entity);
                }
                for (int i = 0; i < 16; i++) {
                    ModDragonFireballEntity fireball=new ModDragonFireballEntity(world,player);
                    fireball.setPos(x,y+4,z);
                    fireball.setVelocity(2*Math.cos(Math.PI*i/8),-1,2*Math.sin(Math.PI*i/8));
                    world.spawnEntity(fireball);
                }
                world.playSound(player,new BlockPos(player.getPos()),SoundEvents.ENTITY_ENDER_DRAGON_SHOOT, SoundCategory.PLAYERS,5.0F,player.getPitch());
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH,3000,4));
                ((IPlayerSwordCoolDown)player).setDragonSwordCoolDown_3(((IPlayerSwordCoolDown)player).dragonSwordMaxCoolDown_3);
            }
        }
        else player.sendMessage(Text.translatable("event.llegend.sword_skill.warning_1").append(String.valueOf((((IPlayerSwordCoolDown)player).getDragonSwordCoolDown_3())/20)).append(Text.translatable("event.llegend.sword_skill.warning_2")).formatted(Formatting.RED));
    }
}
