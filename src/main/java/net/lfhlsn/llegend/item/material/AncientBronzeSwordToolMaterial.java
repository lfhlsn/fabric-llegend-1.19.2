package net.lfhlsn.llegend.item.material;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class AncientBronzeSwordToolMaterial implements ToolMaterial {
    public static final ToolMaterial ANCIENT_BRONZE_SWORD_TOOL_MATERIAL=new AncientBronzeSwordToolMaterial();
    @Override
    public int getDurability() {
        return 100;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 6.0F;
    }

    @Override
    public float getAttackDamage() {
        return 1.0F;
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
        return null;
    }
}
