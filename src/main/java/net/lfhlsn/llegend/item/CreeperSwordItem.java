package net.lfhlsn.llegend.item;

import net.lfhlsn.llegend.entity.IPlayerSwordCoolDown;
import net.lfhlsn.llegend.item.skill.SwordWithSkill_1;
import net.lfhlsn.llegend.item.skill.SwordWithSkill_2;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

import java.util.Random;


public class CreeperSwordItem extends SwordItem implements SwordWithSkill_1, SwordWithSkill_2 {
    public CreeperSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damage(1, attacker, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        World world=attacker.getWorld();
        Random random=new Random();
        if(!world.isClient&&random.nextInt(10)==0){
            Explosion.DestructionType destructionType = world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING) ? Explosion.DestructionType.DESTROY : Explosion.DestructionType.NONE;
            world.createExplosion(target,target.getX(),target.getY(),target.getZ(),2.0f,destructionType);
        }
        return true;
    }

    @Override
    public void skill_1(World world, ServerPlayerEntity player, ItemStack itemStack) {
        if(((IPlayerSwordCoolDown)player).getCreeperSwordCoolDown_1()==0){
            if (!world.isClient){
                itemStack.damage(20,player,e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
                CreeperEntity creeperEntity=new CreeperEntity(EntityType.CREEPER,world);
                creeperEntity.setFuseSpeed(1);
                creeperEntity.setPos(player.getX(),player.getY(),player.getZ());
                world.spawnEntity(creeperEntity);
                ((IPlayerSwordCoolDown)player).setCreeperSwordCoolDown_1(((IPlayerSwordCoolDown)player).creeperSwordMaxCoolDown_1);
            }
        }
        else player.sendMessage(Text.translatable("event.llegend.sword_skill.warning_1").append(String.valueOf((((IPlayerSwordCoolDown)player).getCreeperSwordCoolDown_1())/20)).append(Text.translatable("event.llegend.sword_skill.warning_2")).formatted(Formatting.RED));
    }

    @Override
    public void skill_2(World world, ServerPlayerEntity player, ItemStack itemStack) {
        if(((IPlayerSwordCoolDown)player).getCreeperSwordCoolDown_2()==0){
            if (!world.isClient){
                ((IPlayerSwordCoolDown)player).setCreeperSwordCoolDown_2(((IPlayerSwordCoolDown)player).creeperSwordMaxCoolDown_2);
                itemStack.damage(100,player,e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
                Explosion.DestructionType destructionType = world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING) ? Explosion.DestructionType.DESTROY : Explosion.DestructionType.NONE;
                double x=player.getX();
                double y=player.getY();
                double z=player.getZ();
                for (int i = 0; i < 4; i++) {
                    world.createExplosion(player,x+10*Math.cos(Math.PI*i/2),y,z+10*Math.sin(Math.PI*i/2),8.0f,true,destructionType);
                }
            }
        }
        else player.sendMessage(Text.translatable("event.llegend.sword_skill.warning_1").append(String.valueOf((((IPlayerSwordCoolDown)player).getCreeperSwordCoolDown_2())/20)).append(Text.translatable("event.llegend.sword_skill.warning_2")).formatted(Formatting.RED));
    }
}
