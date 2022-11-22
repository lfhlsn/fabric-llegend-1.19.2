package net.lfhlsn.llegend.item;

import net.lfhlsn.llegend.entity.IPlayerSwordCoolDown;
import net.lfhlsn.llegend.item.skill.SwordWithSkill_1;
import net.lfhlsn.llegend.item.skill.SwordWithSkill_2;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.*;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ClickType;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UnknownSwordItem extends SwordItem implements SwordWithSkill_1, SwordWithSkill_2 {
    public final Random random=new Random();
    public UnknownSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damage(1, attacker, (e) -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        if(random.nextInt(4)==0)
        {
            int duration =random.nextInt(10);
            int amplifier=random.nextInt(5);
            int index =random.nextInt(17);
            StatusEffect[] effects=new StatusEffect[]{StatusEffects.RESISTANCE,StatusEffects.SLOWNESS,StatusEffects.WEAKNESS,StatusEffects.NAUSEA,StatusEffects.MINING_FATIGUE,StatusEffects.HASTE,StatusEffects.HUNGER,StatusEffects.STRENGTH,StatusEffects.UNLUCK,StatusEffects.REGENERATION,StatusEffects.POISON,StatusEffects.BLINDNESS,StatusEffects.DARKNESS,StatusEffects.LUCK,StatusEffects.WITHER,StatusEffects.JUMP_BOOST,StatusEffects.LEVITATION};
            target.addStatusEffect(new StatusEffectInstance(effects[index], duration * 20+100, amplifier));
        }
        return true;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(random.nextInt(300)==0)
        {
            stack.damage(random.nextInt(4),(LivingEntity) entity,(e) -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        }
    }

    @Override
    public boolean onClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
        if(clickType == ClickType.LEFT) {
            List<ItemStack> empty = new ArrayList<>();
            for (ItemStack temp : player.getInventory().main) {
                if (temp.equals(ItemStack.EMPTY)) {
                    empty.add(temp);
                }
            }
            if (empty.size()<1) return false;
            Slot newSlot;
            do {
                newSlot = new Slot(player.getInventory(), random.nextInt(36), -1, -1);
            }
            while (newSlot.hasStack());
            slot.setStack(ItemStack.EMPTY);
            newSlot.setStack(stack);
            return true;
        }
        else return false;
    }

    @Override
    public void skill_1(World world, ServerPlayerEntity player, ItemStack itemStack) {
        if (!world.isClient){
            itemStack.damage(200,player,e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
            int index= random.nextInt(12);
            Item[] items =new Item[]{Items.IRON_INGOT,Items.IRON_INGOT,Items.IRON_INGOT,Items.IRON_INGOT,Items.GOLD_INGOT,Items.GOLD_INGOT,Items.GOLD_INGOT,Items.DIAMOND,Items.DIAMOND,Items.EMERALD,Items.EMERALD,Items.NETHERITE_SCRAP};
            int amount= Math.max(random.nextInt(-7,8)+(itemStack.getItem().getMaxDamage()-itemStack.getDamage())/ random.nextInt(10,20),1);
            ItemStack itemStack1 =new ItemStack(items[index],amount);
            List<ItemStack> empty = new ArrayList<>();
            for (ItemStack temp : player.getInventory().main) {
                if (temp.equals(ItemStack.EMPTY)) {
                    empty.add(temp);
                }
            }
            player.sendMessage(Text.translatable("event.llegend.sword_skill.info").append(String.valueOf(amount)+' ').append(Text.translatable(items[index].getTranslationKey())));
            if (empty.size()<1){
                player.dropItem(itemStack1, true, false);
            }
            else {
                Slot newSlot;
                do {
                    newSlot = new Slot(player.getInventory(), random.nextInt(36), -1, -1);
                }
                while (newSlot.hasStack());
                newSlot.setStack(itemStack1);
            }
        }
    }

    @Override
    public void skill_2(World world, ServerPlayerEntity player, ItemStack itemStack) {
        if(((IPlayerSwordCoolDown)player).getUnknownSwordCoolDown_2()==0)
        {
            if(!world.isClient){
                itemStack.damage(30,player,e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
                int duration =random.nextInt(10);
                int amplifier=random.nextInt(5);
                int index =random.nextInt(17);
                StatusEffect[] effects=new StatusEffect[]{StatusEffects.RESISTANCE,StatusEffects.SLOWNESS,StatusEffects.WEAKNESS,StatusEffects.NAUSEA,StatusEffects.MINING_FATIGUE,StatusEffects.HASTE,StatusEffects.HUNGER,StatusEffects.STRENGTH,StatusEffects.UNLUCK,StatusEffects.REGENERATION,StatusEffects.POISON,StatusEffects.BLINDNESS,StatusEffects.DARKNESS,StatusEffects.LUCK,StatusEffects.WITHER,StatusEffects.JUMP_BOOST,StatusEffects.LEVITATION};
                player.addStatusEffect(new StatusEffectInstance(effects[index], duration * 20+100, amplifier));
                ((IPlayerSwordCoolDown)player).setUnknownSwordCoolDown_2(((IPlayerSwordCoolDown)player).unknownSwordMaxCoolDown_2);
            }
        }
        else player.sendMessage(Text.translatable("event.llegend.sword_skill.warning_1").append(String.valueOf((((IPlayerSwordCoolDown)player).getUnknownSwordCoolDown_2())/20)).append(Text.translatable("event.llegend.sword_skill.warning_2")).formatted(Formatting.RED));
    }
}
