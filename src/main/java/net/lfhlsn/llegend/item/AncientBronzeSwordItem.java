package net.lfhlsn.llegend.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

import java.util.Random;

public class AncientBronzeSwordItem extends SwordItem {
    public final Random random=new Random();
    public AncientBronzeSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if(random.nextInt(15)==0) stack.damage(40, attacker, (e) -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        else stack.damage(random.nextInt(1,5), attacker, (e) -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        return true;
    }
}
