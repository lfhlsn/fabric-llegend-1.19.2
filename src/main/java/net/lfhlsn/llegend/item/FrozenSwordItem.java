package net.lfhlsn.llegend.item;

import net.lfhlsn.llegend.entity.IPlayerSwordCoolDown;
import net.lfhlsn.llegend.item.skill.SwordWithSkill_1;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

public class FrozenSwordItem extends SwordItem implements SwordWithSkill_1 {

    public FrozenSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damage(1, attacker, (e) -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        if(target.isAlive()) target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 1));
        return true;
    }

    @Override
    public void skill_1(World world, ServerPlayerEntity player, ItemStack itemStack) {
        if(((IPlayerSwordCoolDown)player).getFrozenSwordCoolDown_1()==0){
            itemStack.damage(30,player,e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS,2400,4));
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE,1800,3));
            player.getItemCooldownManager().set(this, 6000);
            ((IPlayerSwordCoolDown) player).setFrozenSwordCoolDown_1(IPlayerSwordCoolDown.frozenSwordMaxCoolDown_1);
        }
        else player.sendMessage(Text.translatable("event.llegend.sword_skill.warning_1").append(String.valueOf((((IPlayerSwordCoolDown)player).getFrozenSwordCoolDown_1())/20)).append(Text.translatable("event.llegend.sword_skill.warning_2")).formatted(Formatting.RED));
    }
}
