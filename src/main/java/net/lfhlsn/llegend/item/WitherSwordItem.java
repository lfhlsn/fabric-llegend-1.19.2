package net.lfhlsn.llegend.item;

import net.lfhlsn.llegend.entity.IPlayerSwordCoolDown;
import net.lfhlsn.llegend.item.skill.SwordWithSkill_1;
import net.lfhlsn.llegend.item.skill.SwordWithSkill_2;
import net.lfhlsn.llegend.item.skill.SwordWithSkill_3;
import net.minecraft.entity.*;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.entity.projectile.WitherSkullEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.Random;

public class WitherSwordItem extends SwordItem implements SwordWithSkill_1, SwordWithSkill_2, SwordWithSkill_3 {
    public final Random random=new Random();
    public WitherSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damage(1, attacker, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER,300,2));
        if(random.nextInt(100)==0){
            World world=attacker.getWorld();
            if(!world.isClient){
                WitherSkeletonEntity witherSkeleton=new WitherSkeletonEntity(EntityType.WITHER_SKELETON,world);
                witherSkeleton.setPos(target.getX()+random.nextDouble(-1,1),target.getY(),target.getZ()+random.nextDouble(-1,1));
                witherSkeleton.setTarget(target);
                world.spawnEntity(witherSkeleton);
            }
        }
        return true;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(random.nextInt(6000)==0&&entity instanceof LivingEntity){
            ((LivingEntity)entity).addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER,100,1));
        }
    }

    @Override
    public void skill_1(World world, ServerPlayerEntity player, ItemStack itemStack) {
        if(((IPlayerSwordCoolDown)player).getWitherSwordCoolDown_1()==0){
            if (!world.isClient){
                itemStack.damage(20,player,e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
                AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(world, player.getX(), player.getY(), player.getZ());
                areaEffectCloudEntity.addEffect(new StatusEffectInstance(StatusEffects.WITHER,600,2));
                areaEffectCloudEntity.setRadius(2.5f);
                areaEffectCloudEntity.setRadiusOnUse(-0.5f);
                areaEffectCloudEntity.setWaitTime(10);
                areaEffectCloudEntity.setDuration(600);
                world.spawnEntity(areaEffectCloudEntity);
                ((IPlayerSwordCoolDown)player).setWitherSwordCoolDown_1(((IPlayerSwordCoolDown)player).witherSwordMaxCoolDown_1);
            }
        }
        else player.sendMessage(Text.translatable("event.llegend.sword_skill.warning_1").append(String.valueOf((((IPlayerSwordCoolDown)player).getWitherSwordCoolDown_1())/20)).append(Text.translatable("event.llegend.sword_skill.warning_2")).formatted(Formatting.RED));
    }

    @Override
    public void skill_2(World world, ServerPlayerEntity player, ItemStack itemStack) {
        if(((IPlayerSwordCoolDown)player).getWitherSwordCoolDown_2()==0){
            if (!world.isClient){
                itemStack.damage(80,player,e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER,1500,2));
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE,1500,2));
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE,1500,0));
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST,1500,14));
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION,60,14));
                ((IPlayerSwordCoolDown)player).setWitherSwordCoolDown_2(((IPlayerSwordCoolDown)player).witherSwordMaxCoolDown_2);
            }
        }
        else player.sendMessage(Text.translatable("event.llegend.sword_skill.warning_1").append(String.valueOf((((IPlayerSwordCoolDown)player).getWitherSwordCoolDown_2())/20)).append(Text.translatable("event.llegend.sword_skill.warning_2")).formatted(Formatting.RED));
    }

    @Override
    public void skill_3(World world, ServerPlayerEntity player, ItemStack itemStack) {
        if(((IPlayerSwordCoolDown)player).getWitherSwordCoolDown_3()==0){
            if (!world.isClient){
                itemStack.damage(250,player,e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
                WitherSkullEntity[] witherSkulls1 = new WitherSkullEntity[16];
                WitherSkullEntity[] witherSkulls2 = new WitherSkullEntity[16];
                WitherSkullEntity[] witherSkulls3 = new WitherSkullEntity[16];
                int i=0;
                for (WitherSkullEntity witherSkull: witherSkulls1) {
                    witherSkull=new WitherSkullEntity(world,player,Math.cos(Math.PI*i/8),-0.3,Math.sin(Math.PI*i/8));
                    witherSkull.setPos(player.getX(),player.getY()+4,player.getZ());
                    witherSkull.setCharged(true);
                    world.spawnEntity(witherSkull);
                    i++;
                }
                for (WitherSkullEntity witherSkull: witherSkulls2) {
                    witherSkull=new WitherSkullEntity(world,player,Math.cos(Math.PI*i/8),-0.6,Math.sin(Math.PI*i/8));
                    witherSkull.setPos(player.getX(),player.getY()+4,player.getZ());
                    witherSkull.setCharged(true);
                    world.spawnEntity(witherSkull);
                    i++;
                }
                for (WitherSkullEntity witherSkull: witherSkulls3) {
                    witherSkull=new WitherSkullEntity(world,player,Math.cos(Math.PI*i/8),-0.9,Math.sin(Math.PI*i/8));
                    witherSkull.setPos(player.getX(),player.getY()+4,player.getZ());
                    witherSkull.setCharged(true);
                    world.spawnEntity(witherSkull);
                    i++;
                }
                ((IPlayerSwordCoolDown)player).setWitherSwordCoolDown_3(((IPlayerSwordCoolDown)player).witherSwordMaxCoolDown_3);
            }
        }
        else player.sendMessage(Text.translatable("event.llegend.sword_skill.warning_1").append(String.valueOf((((IPlayerSwordCoolDown)player).getWitherSwordCoolDown_3())/20)).append(Text.translatable("event.llegend.sword_skill.warning_2")).formatted(Formatting.RED));
    }
}
