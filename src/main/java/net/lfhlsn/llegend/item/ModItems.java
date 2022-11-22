package net.lfhlsn.llegend.item;

import net.lfhlsn.llegend.ModInitializer;
import net.lfhlsn.llegend.item.material.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class ModItems {
    public static final Item FROZEN_SWORD = new FrozenSwordItem(FrozenSwordToolMaterial.FROZEN_SWORD_TOOL_MATERIAL,3,-2.4F,new Item.Settings().group(ItemGroup.COMBAT).rarity(Rarity.UNCOMMON));
    public static final Item UNKNOWN_SWORD = new UnknownSwordItem(UnknownSwordToolMaterial.UNKNOWN_SWORD_TOOL_MATERIAL,3,-2.4F,new Item.Settings().group(ItemGroup.COMBAT).rarity(Rarity.RARE));
    public static final Item DRAGON_SWORD = new DragonSwordItem(DragonSwordToolMaterial.DRAGON_SWORD_MATERIAL,3,-2.4F,new Item.Settings().group(ItemGroup.COMBAT).rarity(Rarity.EPIC));
    public static final Item ANCIENT_IRON_SWORD = new AncientIronSwordItem(AncientIronSwordToolMaterial.ANCIENT_IRON_SWORD_TOOL_MATERIAL,3,-2.4F,new Item.Settings().group(ItemGroup.COMBAT).rarity(Rarity.UNCOMMON));
    public static final Item ANCIENT_BRONZE_SWORD = new AncientBronzeSwordItem(AncientBronzeSwordToolMaterial.ANCIENT_BRONZE_SWORD_TOOL_MATERIAL,3,-2.4F,new Item.Settings().group(ItemGroup.COMBAT).rarity(Rarity.UNCOMMON));
    public static final Item END_SWORD = new EndSwordItem(EndSwordToolMaterial.END_SWORD_TOOL_MATERIAL,3,-2.4F,new Item.Settings().group(ItemGroup.COMBAT).rarity(Rarity.RARE));
    public static final Item CREEPER_SWORD = new CreeperSwordItem(CreeperSwordToolMaterial.CREEPER_SWORD_TOOL_MATERIAL,3,-2.4F,new Item.Settings().group(ItemGroup.COMBAT).rarity(Rarity.RARE));
    public static final Item WITHER_SWORD = new WitherSwordItem(WitherSwordToolMaterial.WITHER_SWORD_TOOL_MATERIAL,3,-2.4F,new Item.Settings().group(ItemGroup.COMBAT).rarity(Rarity.EPIC));
    public static void register(){
        Registry.register(Registry.ITEM, new Identifier(ModInitializer.MOD_ID,"frozen_sword"),FROZEN_SWORD);
        Registry.register(Registry.ITEM, new Identifier(ModInitializer.MOD_ID,"unknown_sword"),UNKNOWN_SWORD);
        Registry.register(Registry.ITEM, new Identifier(ModInitializer.MOD_ID,"dragon_sword"),DRAGON_SWORD);
        Registry.register(Registry.ITEM, new Identifier(ModInitializer.MOD_ID,"ancient_iron_sword"),ANCIENT_IRON_SWORD);
        Registry.register(Registry.ITEM, new Identifier(ModInitializer.MOD_ID,"ancient_bronze_sword"),ANCIENT_BRONZE_SWORD);
        Registry.register(Registry.ITEM, new Identifier(ModInitializer.MOD_ID,"end_sword"),END_SWORD);
        Registry.register(Registry.ITEM, new Identifier(ModInitializer.MOD_ID,"creeper_sword"),CREEPER_SWORD);
        Registry.register(Registry.ITEM, new Identifier(ModInitializer.MOD_ID,"wither_sword"),WITHER_SWORD);
    }
}
