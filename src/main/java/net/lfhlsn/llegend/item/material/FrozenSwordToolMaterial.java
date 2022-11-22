package net.lfhlsn.llegend.item.material;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class FrozenSwordToolMaterial implements ToolMaterial {
    public static final ToolMaterial FROZEN_SWORD_TOOL_MATERIAL =new FrozenSwordToolMaterial();

    @Override
    public int getDurability() {
        return 450;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 6.0F;
    }

    @Override
    public float getAttackDamage() {
        return 3.0F;
    }

    @Override
    public int getMiningLevel() {
        return 2;
    }

    @Override
    public int getEnchantability() {
        return 12;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.BLUE_ICE);
    }
}
