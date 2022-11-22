package net.lfhlsn.llegend.util;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.lfhlsn.llegend.item.ModItems;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantWithLevelsLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.entity.EntityEquipmentPredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.util.Identifier;

public class ModLootTableModifiers {
    private static final Identifier DESERT_PYRAMID_CHEST_ID =new Identifier("minecraft","chests/pyramid");
    private static final Identifier ANCIENT_CITY_CHEST_ID =new Identifier("minecraft","chests/ancient_city");
    private static final Identifier CREEPER_ID =new Identifier("minecraft","entities/creeper");
    private static final Identifier ENDER_DRAGON_ID =new Identifier("minecraft","entities/ender_dragon");
    private static final Identifier WITHER_ID =new Identifier("minecraft","entities/wither");
    private static final Identifier END_CITY_TREASURE =new Identifier("minecraft","chests/end_city_treasure");

    public static void modifyLootTables(){
        LootTableEvents.MODIFY.register(((resourceManager, lootManager, id, tableBuilder, source) -> {
            if(DESERT_PYRAMID_CHEST_ID.equals(id)) {
                LootPool.Builder poolBuilder =LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0f))
                        .conditionally(RandomChanceLootCondition.builder(0.6f))
                        .with(ItemEntry.builder(ModItems.ANCIENT_BRONZE_SWORD))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,1.0f)).build())
                        .apply(EnchantWithLevelsLootFunction.builder(UniformLootNumberProvider.create(10.0f,19.0f)).conditionally(RandomChanceLootCondition.builder(0.2f)).build());
                tableBuilder.pool(poolBuilder.build());
            }

            if(ANCIENT_CITY_CHEST_ID.equals(id)) {
                LootPool.Builder poolBuilder =LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0f))
                        .conditionally(RandomChanceLootCondition.builder(0.2f))
                        .with(ItemEntry.builder(ModItems.ANCIENT_IRON_SWORD))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,1.0f)).build())
                        .apply(EnchantWithLevelsLootFunction.builder(UniformLootNumberProvider.create(20.0f,29.0f)).conditionally(RandomChanceLootCondition.builder(0.7f)).build());
                tableBuilder.pool(poolBuilder.build());
            }

            if(ENDER_DRAGON_ID.equals(id)){
                LootPool.Builder poolBuilder =LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0f))
                        .conditionally(RandomChanceLootCondition.builder(0.3f))
                        .conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.KILLER,
                                new EntityPredicate.Builder().equipment(EntityEquipmentPredicate.Builder.create()
                                        .mainhand(ItemPredicate.Builder.create().items(ModItems.END_SWORD).build()).build()).build()
                        ))
                        .with(ItemEntry.builder(ModItems.DRAGON_SWORD))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,1.0f)).build());
                tableBuilder.pool(poolBuilder.build());
            }

            if(WITHER_ID.equals(id)){
                LootPool.Builder poolBuilder =LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0f))
                        .conditionally(RandomChanceLootCondition.builder(0.3f))
                        .conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.KILLER,
                                new EntityPredicate.Builder().equipment(EntityEquipmentPredicate.Builder.create()
                                        .mainhand(ItemPredicate.Builder.create().items(ModItems.ANCIENT_IRON_SWORD).build()).build()).build()))
                        .with(ItemEntry.builder(ModItems.WITHER_SWORD))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,1.0f)).build());
                tableBuilder.pool(poolBuilder.build());
            }

            if(CREEPER_ID.equals(id)){
                LootPool.Builder poolBuilder =LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0f))
                        .conditionally(RandomChanceLootCondition.builder(0.7f))
                        .conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.KILLER,
                                new EntityPredicate.Builder().equipment(EntityEquipmentPredicate.Builder.create()
                                        .mainhand(ItemPredicate.Builder.create().items(ModItems.ANCIENT_BRONZE_SWORD).build()).build()).build()))
                        .with(ItemEntry.builder(ModItems.CREEPER_SWORD))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,1.0f)).build());
                tableBuilder.pool(poolBuilder.build());
            }

            if(END_CITY_TREASURE.equals(id)){
                LootPool.Builder poolBuilder =LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(0.2f))
                        .conditionally(RandomChanceLootCondition.builder(1.0f))
                        .with(ItemEntry.builder(ModItems.END_SWORD))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,1.0f)).build())
                        .apply(EnchantWithLevelsLootFunction.builder(UniformLootNumberProvider.create(20.0f,39.0f)).conditionally(RandomChanceLootCondition.builder(0.9f)).build());
                tableBuilder.pool(poolBuilder.build());
            }
        }));
    }
}
