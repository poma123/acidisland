package com.wasteofplastic.acidisland.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.bukkit.block.Biome;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.sk89q.worldedit.entity.metadata.EntityType;
import com.wasteofplastic.acidisland.ASkyBlock;

public class UpdateOnePointThirteen {

	private static HashMap<String, String> table;

	static {
		table = new HashMap<String, String>();

		// nether biome
		table.put("HELL", "NETHER");
		table.put("MUSHROOM_ISLAND", "MUSHROOM_FIELDS");
		table.put("SWAMPLAND", "SWAMP");
		table.put("COLD_BEACH", "SNOWY_BEACH");
		table.put("MESA", "BADLANDS");
		table.put("MUTATED_FOREST", "FLOWER_FOREST");
		table.put("ROOFED_FOREST", "DARK_FOREST");

		// materials
		table.put("STONE:1", "GRANITE");
		table.put("STONE:2", "POLISHED_GRANITE");
		table.put("STONE:3", "DIORITE");
		table.put("STONE:4", "POLISHED_DIORITE");
		table.put("STONE:5", "ANDESITE");
		table.put("STONE:6", "POLISHED_ANDESITE");
		table.put("DIRT:1", "DIRT");
		table.put("DIRT:2", "COARSE_DIRT");
		table.put("DIRT:3", "PODZOL");
		table.put("WOOD:1", "SPRUCE_PLANKS");
		table.put("WOOD:2", "BIRCH_PLANKS");
		table.put("WOOD:3", "JUNGLE_PLANKS");
		table.put("WOOD:4", "ACACIA_PLANKS");
		table.put("WOOD:5", "DARK_OAK_PLANKS");
		table.put("WOOD", "OAK_PLANKS");
		table.put("SAPLING:1", "SPRUCE_SAPLING");
		table.put("SAPLING:2", "BIRCH_SAPLING");
		table.put("SAPLING:3", "JUNGLE_SAPLING");
		table.put("SAPLING:4", "ACACIA_SAPLING");
		table.put("SAPLING:5", "DARK_OAK_SAPLING");
		table.put("SAPLING", "OAK_SAPLING");
		table.put("STATIONARY_WATER", "WATER");
		table.put("STATIONARY_LAVA", "LAVA");
		table.put("SAND:1", "RED_SAND");
		table.put("LOG:1", "SPRUCE_LOG");
		table.put("LOG:2", "BIRCH_LOG");
		table.put("LOG:3", "JUNGLE_LOG");
		table.put("LOG", "OAK_LOG");
		table.put("LEAVES:1", "SPRUCE_LEAVES");
		table.put("LEAVES:2", "BIRCH_LEAVES");
		table.put("LEAVES:3", "JUNGLE_LEAVES");
		table.put("LEAVES", "OAK_LEAVES");
		table.put("SPONGE:1", "WET_SPONGE");
		table.put("SANDSTONE:1", "CHISELED_SANDSTONE");
		table.put("SANDSTONE:2", "CUT_SANDSTONE");
		table.put("NOTE_BLOCK", "NOTE_BLOCK");
		table.put("BED_BLOCK", "RED_BED");
		table.put("POWERED_RAIL", "POWERED_RAIL");
		table.put("DETECTOR_RAIL", "DETECTOR_RAIL");
		table.put("PISTON_STICKY_BASE", "STICKY_PISTON");
		table.put("WEB", "COBWEB");
		table.put("LONG_GRASS", "TALL_GRASS");
		table.put("LONG_GRASS:2", "FERN");
		table.put("PISTON_BASE", "PISTON");
		table.put("PISTON_EXTENSION", "PISTON_HEAD");
		table.put("WOOL", "WHITE_WOOL");
		table.put("WOOL:1", "ORANGE_WOOL");
		table.put("WOOL:2", "MAGENTA_WOOL");
		table.put("WOOL:3", "LIGHT_BLUE_WOOL");
		table.put("WOOL:4", "YELLOW_WOOL");
		table.put("WOOL:5", "LIME_WOOL");
		table.put("WOOL:6", "PINK_WOOL");
		table.put("WOOL:7", "GRAY_WOOL");
		table.put("WOOL:8", "LIGHT_GRAY_WOOL");
		table.put("WOOL:9", "CYAN_WOOL");
		table.put("WOOL:10", "PURPLE_WOOL");
		table.put("WOOL:11", "BLUE_WOOL");
		table.put("WOOL:12", "BROWN_WOOL");
		table.put("WOOL:13", "GREEN_WOOL");
		table.put("WOOL:14", "RED_WOOL");
		table.put("WOOL:15", "BLACK_WOOL");
		table.put("PISTON_MOVING_PIECE", "MOVING_PISTON");
		table.put("YELLOW_FLOWER", "DANDELION_YELLOW");
		table.put("RED_ROSE", "POPPY");
		table.put("RED_ROSE:1", "BLUE_ORCHID");
		table.put("RED_ROSE:2", "ALLIUM");
		table.put("RED_ROSE:3", "AZURE_BLUET");
		table.put("RED_ROSE:4", "RED_TULIP");
		table.put("RED_ROSE:5", "ORANGE_TULIP");
		table.put("RED_ROSE:6", "WHITE_TULIP");
		table.put("RED_ROSE:7", "PINK_TULIP");
		table.put("RED_ROSE:8", "OXEYE_DAISY");
		table.put("DOUBLE_STEP", "STONE_SLAB");
		table.put("DOUBLE_STEP:1", "SANDSTONE_SLAB");
		table.put("DOUBLE_STEP:2", "OAK_SLAB");
		table.put("DOUBLE_STEP:3", "COBBLESTONE_SLAB");
		table.put("DOUBLE_STEP:4", "BRICK_SLAB");
		table.put("DOUBLE_STEP:5", "STONE_BRICK_SLAB");
		table.put("DOUBLE_STEP:6", "NETHER_BRICK_SLAB");
		table.put("DOUBLE_STEP:7", "SMOOTH_QUARTZ");
		table.put("DOUBLE_STEP:8", "SMOOTH_STONE");
		table.put("STEP", "STONE_SLAB");
		table.put("STEP:1", "SANDSTONE_SLAB");
		table.put("STEP:2", "OAK_SLAB");
		table.put("STEP:3", "COBBLESTONE_SLAB");
		table.put("STEP:4", "BRICK_SLAB");
		table.put("STEP:5", "STONE_BRICK_SLAB");
		table.put("STEP:6", "NETHER_BRICK_SLAB");
		table.put("STEP:7", "QUARTZ_SLAB");
		table.put("MOB_SPAWNER", "SPAWNER");
		table.put("WOOD_STAIRS", "OAK_STAIRS");
		table.put("WOOD_STAIRS:1", "SPRUCE_STAIRS");
		table.put("WOOD_STAIRS:2", "BIRCH_STAIRS");
		table.put("WOOD_STAIRS:3", "JUNGLE_STAIRS");
		table.put("WOOD_STAIRS:4", "ACACIA_STAIRS");
		table.put("WOOD_STAIRS:5", "DARK_OAK_STAIRS");
		table.put("WORKBENCH", "CRAFTING_TABLE");
		table.put("CROPS", "WHEAT");
		table.put("SOIL", "FARMLAND");
		table.put("BURNING_FURNACE", "FURNACE");
		table.put("SIGN_POST", "SIGN");
		table.put("WOODEN_DOOR", "OAK_DOOR");
		table.put("WOODEN_DOOR:1", "SPRUCE_DOOR");
		table.put("WOODEN_DOOR:2", "BIRCH_DOOR");
		table.put("WOODEN_DOOR:3", "JUNGLE_DOOR");
		table.put("WOODEN_DOOR:4", "ACACIA_DOOR");
		table.put("WOODEN_DOOR:5", "DARK_OAK_DOOR");
		table.put("RAILS", "RAIL");
		table.put("STONE_PLATE", "STONE_PRESSURE_PLATE");
		table.put("IRON_DOOR_BLOCK", "IRON_DOOR");
		table.put("WOOD_PLATE", "OAK_PRESSURE_PLATE");
		table.put("GLOWING_REDSTONE_ORE", "REDSTONE_ORE");
		table.put("REDSTONE_TORCH_OFF", "REDSTONE_TORCH");
		table.put("REDSTONE_TORCH_ON", "REDSTONE_TORCH");
		table.put("SUGAR_CANE_BLOCK", "SUGAR_CANE");
		table.put("FENCE", "OAK_FENCE");
		table.put("PORTAL", "NETHER_PORTAL");
		table.put("CAKE_BLOCK", "CAKE");
		table.put("DIODE_BLOCK_OFF", "REPEATER");
		table.put("DIODE_BLOCK_ON", "REPEATER");
		table.put("WHITE_STAINED_GLASS", "WHITE_STAINED_GLASS");
		table.put("ORANGE_STAINED_GLASS:1", "ORANGE_STAINED_GLASS");
		table.put("MAGENTA_STAINED_GLASS:2", "MAGENTA_STAINED_GLASS");
		table.put("LIGHT_BLUE_STAINED_GLASS:3", "LIGHT_BLUE_STAINED_GLASS");
		table.put("YELLOW_STAINED_GLASS:4", "YELLOW_STAINED_GLASS");
		table.put("LIME_STAINED_GLASS:5", "LIME_STAINED_GLASS");
		table.put("PINK_STAINED_GLASS:6", "PINK_STAINED_GLASS");
		table.put("GRAY_STAINED_GLASS:7", "GRAY_STAINED_GLASS");
		table.put("LIGHT_GRAY_STAINED_GLASS:8", "LIGHT_GRAY_STAINED_GLASS");
		table.put("CYAN_STAINED_GLASS:9", "CYAN_STAINED_GLASS");
		table.put("PURPLE_STAINED_GLASS:10", "PURPLE_STAINED_GLASS");
		table.put("BLUE_STAINED_GLASS:11", "BLUE_STAINED_GLASS");
		table.put("BROWN_STAINED_GLASS:12", "BROWN_STAINED_GLASS");
		table.put("GREEN_STAINED_GLASS:13", "GREEN_STAINED_GLASS");
		table.put("RED_STAINED_GLASS:14", "RED_STAINED_GLASS");
		table.put("BLACK_STAINED_GLASS:15", "BLACK_STAINED_GLASS");
		table.put("TRAP_DOOR", "OAK_TRAPDOOR");
		table.put("TRAP_DOOR:1", "SPRUCE_TRAPDOOR");
		table.put("TRAP_DOOR:2", "BIRCH_TRAPDOOR");
		table.put("TRAP_DOOR:3", "JUNGLE_TRAPDOOR");
		table.put("TRAP_DOOR:4", "ACACIA_TRAPDOOR");
		table.put("TRAP_DOOR:5", "DARK_OAK_TRAPDOOR");
		table.put("MONSTER_EGGS", "INFESTED_STONE");
		table.put("MONSTER_EGGS:1", "INFESTED_COBBLESTONE");
		table.put("MONSTER_EGGS:2", "INFESTED_STONE_BRICKS");
		table.put("MONSTER_EGGS:3", "INFESTED_MOSSY_STONE_BRICKS");
		table.put("MONSTER_EGGS:4", "INFESTED_CRACKED_STONE_BRICKS");
		table.put("SMOOTH_BRICK:5", "INFESTED_CHISELED_STONE_BRICKS");
		table.put("HUGE_MUSHROOM_1", "RED_MUSHROOM");
		table.put("HUGE_MUSHROOM_2", "RED_MUSHROOM");
		table.put("IRON_FENCE", "IRON_BARS");
		table.put("THIN_GLASS", "GLASS_PANE");
		table.put("MELON_BLOCK", "MELON");
		table.put("FENCE_GATE", "OAK_FENCE_GATE");
		table.put("FENCE_GATE:1", "SPRUCE_FENCE_GATE");
		table.put("FENCE_GATE:2", "BIRCH_FENCE_GATE");
		table.put("FENCE_GATE:3", "JUNGLE_FENCE_GATE");
		table.put("FENCE_GATE:4", "ACACIA_FENCE_GATE");
		table.put("FENCE_GATE:5", "DARK_OAK_FENCE_GATE");
		table.put("SMOOTH_STAIRS", "STONE_BRICK_STAIRS");
		table.put("MYCEL", "MYCELIUM");
		table.put("WATER_LILY", "LILY_PAD");
		table.put("NETHER_FENCE", "NETHER_BRICK_FENCE");
		table.put("NETHER_WARTS", "NETHER_WART");
		table.put("ENCHANTMENT_TABLE", "ENCHANTING_TABLE");
		table.put("ENDER_PORTAL", "END_PORTAL");
		table.put("ENDER_PORTAL_FRAME", "END_PORTAL_FRAME");
		table.put("ENDER_STONE", "END_STONE");
		table.put("REDSTONE_LAMP_OFF", "REDSTONE_LAMP");
		table.put("REDSTONE_LAMP_ON", "REDSTONE_LAMP");
		table.put("WOOD_DOUBLE_STEP", "OAK_SLAB");
		table.put("WOODEN_DOUBLE_STEP:1", "SPRUCE_SLAB");
		table.put("WOODEN_DOUBLE_STEP:2", "BIRCH_SLAB");
		table.put("WOODEN_DOUBLE_STEP:3", "JUNGLE_SLAB");
		table.put("WOODEN_DOUBLE_STEP:4", "ACACIA_SLAB");
		table.put("WOODEN_DOUBLE_STEP:5", "DARK_OAK_SLAB");
		table.put("WOOD_STEP", "OAK_SLAB");
		table.put("WOODEN_STEP:1", "SPRUCE_SLAB");
		table.put("WOODEN_STEP:2", "BIRCH_SLAB");
		table.put("WOODEN_STEP:3", "JUNGLE_SLAB");
		table.put("WOODEN_STEP:4", "ACACIA_SLAB");
		table.put("WOODEN_STEP:5", "DARK_OAK_SLAB");
		table.put("SPRUCE_WOOD_STAIRS", "SPRUCE_STAIRS");
		table.put("BIRCH_WOOD_STAIRS", "BIRCH_STAIRS");
		table.put("JUNGLE_WOOD_STAIRS", "JUNGLE_STAIRS");
		table.put("COMMAND", "COMMAND_BLOCK");
		table.put("COBBLE_WALL", "COBBLESTONE_WALL");
		table.put("WOOD_BUTTON", "OAK_BUTTON");
		table.put("SKULL", "PLAYER_HEAD");
		table.put("GOLD_PLATE", "HEAVY_WEIGHTED_PRESSURE_PLATE");
		table.put("IRON_PLATE", "LIGHT_WEIGHTED_PRESSURE_PLATE");
		table.put("REDSTONE_COMPARATOR_OFF", "COMPARATOR");
		table.put("REDSTONE_COMPARATOR_ON", "COMPARATOR");
		table.put("QUARTZ_ORE", "NETHER_QUARTZ_ORE");
		table.put("STAINED_CLAY", "WHITE_TERRACOTTA");
		table.put("STAINED_CLAY:1", "ORANGE_TERRACOTTA");
		table.put("STAINED_CLAY:2", "MAGENTA_TERRACOTTA");
		table.put("STAINED_CLAY:3", "LIGHT_BLUE_TERRACOTTA");
		table.put("STAINED_CLAY:4", "YELLOW_TERRACOTTA");
		table.put("STAINED_CLAY:5", "LIME_TERRACOTTA");
		table.put("STAINED_CLAY:6", "PINK_TERRACOTTA");
		table.put("STAINED_CLAY:7", "GRAY_TERRACOTTA");
		table.put("STAINED_CLAY:8", "LIGHT_GRAY_TERRACOTTA");
		table.put("STAINED_CLAY:9", "CYAN_TERRACOTTA");
		table.put("STAINED_CLAY:10", "PURPLE_TERRACOTTA");
		table.put("STAINED_CLAY:11", "BLUE_TERRACOTTA");
		table.put("STAINED_CLAY:12", "BROWN_TERRACOTTA");
		table.put("STAINED_CLAY:13", "GREEN_TERRACOTTA");
		table.put("STAINED_CLAY:14", "RED_TERRACOTTA");
		table.put("STAINED_CLAY:15", "BLACK_TERRACOTTA");
		table.put("STAINED_GLASS_PANE", "WHITE_STAINED_GLASS_PANE");
		table.put("STAINED_GLASS_PANE:1", "ORANGE_STAINED_GLASS_PANE");
		table.put("STAINED_GLASS_PANE:2", "MAGENTA_STAINED_GLASS_PANE");
		table.put("STAINED_GLASS_PANE:3", "LIGHT_BLUE_STAINED_GLASS_PANE");
		table.put("STAINED_GLASS_PANE:4", "YELLOW_STAINED_GLASS_PANE");
		table.put("STAINED_GLASS_PANE:5", "LIME_STAINED_GLASS_PANE");
		table.put("STAINED_GLASS_PANE:6", "PINK_STAINED_GLASS_PANE");
		table.put("STAINED_GLASS_PANE:7", "GRAY_STAINED_GLASS_PANE");
		table.put("STAINED_GLASS_PANE:8", "LIGHT_GRAY_STAINED_GLASS_PANE");
		table.put("STAINED_GLASS_PANE:9", "CYAN_STAINED_GLASS_PANE");
		table.put("STAINED_GLASS_PANE:10", "PURPLE_STAINED_GLASS_PANE");
		table.put("STAINED_GLASS_PANE:11", "BLUE_STAINED_GLASS_PANE");
		table.put("STAINED_GLASS_PANE:12", "BROWN_STAINED_GLASS_PANE");
		table.put("STAINED_GLASS_PANE:13", "GREEN_STAINED_GLASS_PANE");
		table.put("STAINED_GLASS_PANE:14", "RED_STAINED_GLASS_PANE");
		table.put("STAINED_GLASS_PANE:15", "BLACK_STAINED_GLASS_PANE");
		table.put("LEAVES_2", "ACACIA_LEAVES");
		table.put("LEAVES_2:1", "DARK_OAK_LEAVES");
		table.put("LOG_2", "ACACIA_LOG");
		table.put("LOG_2:1", "DARK_OAK_LOG");
		table.put("ACACIA_STAIRS", "ACACIA_STAIRS");
		table.put("DARK_OAK_STAIRS", "DARK_OAK_STAIRS");
		table.put("CARPET", "WHITE_CARPET");
		table.put("CARPET:1", "ORANGE_CARPET");
		table.put("CARPET:2", "MAGENTA_CARPET");
		table.put("CARPET:3", "LIGHT_BLUE_CARPET");
		table.put("CARPET:4", "YELLOW_CARPET");
		table.put("CARPET:5", "LIME_CARPET");
		table.put("CARPET:6", "PINK_CARPET");
		table.put("CARPET:7", "GRAY_CARPET");
		table.put("CARPET:8", "LIGHT_GRAY_CARPET");
		table.put("CARPET:9", "CYAN_CARPET");
		table.put("CARPET:10", "PURPLE_CARPET");
		table.put("CARPET:11", "BLUE_CARPET");
		table.put("CARPET:12", "BROWN_CARPET");
		table.put("CARPET:13", "GREEN_CARPET");
		table.put("CARPET:14", "RED_CARPET");
		table.put("CARPET:15", "BLACK_CARPET");
		table.put("HARD_CLAY", "TERRACOTTA");
		table.put("DOUBLE_PLANT", "SUNFLOWER");
		table.put("STANDING_BANNER", "WHITE_BANNER");
		table.put("STANDING_BANNER:1", "ORANGE_BANNER");
		table.put("STANDING_BANNER:2", "MAGENTA_BANNER");
		table.put("STANDING_BANNER:3", "LIGHT_BLUE_BANNER");
		table.put("STANDING_BANNER:4", "YELLOW_BANNER");
		table.put("STANDING_BANNER:5", "LIME_BANNER");
		table.put("STANDING_BANNER:6", "PINK_BANNER");
		table.put("STANDING_BANNER:7", "GRAY_BANNER");
		table.put("STANDING_BANNER:8", "LIGHT_GRAY_BANNER");
		table.put("STANDING_BANNER:9", "CYAN_BANNER");
		table.put("STANDING_BANNER:10", "PURPLE_BANNER");
		table.put("STANDING_BANNER:11", "BLUE_BANNER");
		table.put("STANDING_BANNER:12", "BROWN_BANNER");
		table.put("STANDING_BANNER:13", "GREEN_BANNER");
		table.put("STANDING_BANNER:14", "RED_BANNER");
		table.put("STANDING_BANNER:15", "BLACK_BANNER");
		table.put("WALL_BANNER", "WHITE_WALL_BANNER");
		table.put("WALL_BANNER:1", "ORANGE_WALL_BANNER");
		table.put("WALL_BANNER:2", "MAGENTA_WALL_BANNER");
		table.put("WALL_BANNER:3", "LIGHT_BLUE_WALL_BANNER");
		table.put("WALL_BANNER:4", "YELLOW_WALL_BANNER");
		table.put("WALL_BANNER:5", "LIME_WALL_BANNER");
		table.put("WALL_BANNER:6", "PINK_WALL_BANNER");
		table.put("WALL_BANNER:7", "GRAY_WALL_BANNER");
		table.put("WALL_BANNER:8", "LIGHT_GRAY_WALL_BANNER");
		table.put("WALL_BANNER:9", "CYAN_WALL_BANNER");
		table.put("WALL_BANNER:10", "PURPLE_WALL_BANNER");
		table.put("WALL_BANNER:11", "BLUE_WALL_BANNER");
		table.put("WALL_BANNER:12", "BROWN_WALL_BANNER");
		table.put("WALL_BANNER:13", "GREEN_WALL_BANNER");
		table.put("WALL_BANNER:14", "RED_WALL_BANNER");
		table.put("WALL_BANNER:15", "BLACK_WALL_BANNER");
		table.put("DAYLIGHT_DETECTOR_INVERTED", "DAYLIGHT_DETECTOR");
		table.put("DOUBLE_STONE_SLAB2", "STONE_SLAB");
		table.put("STONE_SLAB2", "STONE_SLAB");
		table.put("PURPUR_DOUBLE_SLAB", "PURPUR_SLAB");
		table.put("END_BRICKS", "END_STONE_BRICKS");
		table.put("BEETROOT_BLOCK", "BEETROOTS");
		table.put("COMMAND_REPEATING", "REPEATING_COMMAND_BLOCK");
		table.put("COMMAND_CHAIN", "CHAIN_COMMAND_BLOCK");
		table.put("MAGMA", "MAGMA_BLOCK");
		table.put("NETHER_WART_BLOCK", "NETHER_WART_BLOCK");
		table.put("RED_NETHER_BRICK", "RED_NETHER_BRICKS");
		table.put("SILVER_SHULKER_BOX", "LIGHT_GRAY_SHULKER_BOX");
		table.put("SILVER_GLAZED_TERRACOTTA", "LIGHT_GRAY_GLAZED_TERRACOTTA");
		table.put("CONCRETE", "WHITE_CONCRETE");
		table.put("CONCRETE:1", "ORANGE_CONCRETE");
		table.put("CONCRETE:2", "MAGENTA_CONCRETE");
		table.put("CONCRETE:3", "LIGHT_BLUE_CONCRETE");
		table.put("CONCRETE:4", "YELLOW_CONCRETE");
		table.put("CONCRETE:5", "LIME_CONCRETE");
		table.put("CONCRETE:6", "PINK_CONCRETE");
		table.put("CONCRETE:7", "GRAY_CONCRETE");
		table.put("CONCRETE:8", "LIGHT_GRAY_CONCRETE");
		table.put("CONCRETE:9", "CYAN_CONCRETE");
		table.put("CONCRETE:10", "PURPLE_CONCRETE");
		table.put("CONCRETE:11", "BLUE_CONCRETE");
		table.put("CONCRETE:12", "BROWN_CONCRETE");
		table.put("CONCRETE:13", "GREEN_CONCRETE");
		table.put("CONCRETE:14", "RED_CONCRETE");
		table.put("CONCRETE:15", "BLACK_CONCRETE");
		table.put("CONCRETE_POWDER", "WHITE_CONCRETE_POWDER");
		table.put("CONCRETE_POWDER:1", "ORANGE_CONCRETE_POWDER");
		table.put("CONCRETE_POWDER:2", "MAGENTA_CONCRETE_POWDER");
		table.put("CONCRETE_POWDER:3", "LIGHT_BLUE_CONCRETE_POWDER");
		table.put("CONCRETE_POWDER:4", "YELLOW_CONCRETE_POWDER");
		table.put("CONCRETE_POWDER:5", "LIME_CONCRETE_POWDER");
		table.put("CONCRETE_POWDER:6", "PINK_CONCRETE_POWDER");
		table.put("CONCRETE_POWDER:7", "GRAY_CONCRETE_POWDER");
		table.put("CONCRETE_POWDER:8", "LIGHT_GRAY_CONCRETE_POWDER");
		table.put("CONCRETE_POWDER:9", "CYAN_CONCRETE_POWDER");
		table.put("CONCRETE_POWDER:10", "PURPLE_CONCRETE_POWDER");
		table.put("CONCRETE_POWDER:11", "BLUE_CONCRETE_POWDER");
		table.put("CONCRETE_POWDER:12", "BROWN_CONCRETE_POWDER");
		table.put("CONCRETE_POWDER:13", "GREEN_CONCRETE_POWDER");
		table.put("CONCRETE_POWDER:14", "RED_CONCRETE_POWDER");
		table.put("CONCRETE_POWDER:15", "BLACK_CONCRETE_POWDER");
		table.put("IRON_SPADE", "IRON_SHOVEL");
		table.put("WOOD_SWORD", "WOODEN_SWORD");
		table.put("WOOD_SPADE", "WOODEN_SHOVEL");
		table.put("WOOD_PICKAXE", "WOODEN_PICKAXE");
		table.put("WOOD_AXE", "WOODEN_AXE");
		table.put("STONE_SPADE", "STONE_SHOVEL");
		table.put("DIAMOND_SPADE", "DIAMOND_SHOVEL");
		table.put("MUSHROOM_SOUP", "MUSHROOM_STEW");
		table.put("GOLD_SWORD", "GOLDEN_SWORD");
		table.put("GOLD_SPADE", "GOLDEN_SHOVEL");
		table.put("GOLD_PICKAXE", "GOLDEN_PICKAXE");
		table.put("GOLD_AXE", "GOLDEN_AXE");
		table.put("SULPHUR", "GUNPOWDER");
		table.put("WOOD_HOE", "WOODEN_HOE");
		table.put("GOLD_HOE", "GOLDEN_HOE");
		table.put("SEEDS", "WHEAT_SEEDS");
		table.put("GOLD_HELMET", "GOLDEN_HELMET");
		table.put("GOLD_CHESTPLATE", "GOLDEN_CHESTPLATE");
		table.put("GOLD_LEGGINGS", "GOLDEN_LEGGINGS");
		table.put("GOLD_BOOTS", "GOLDEN_BOOTS");
		table.put("PORK", "PORKCHOP");
		table.put("GRILLED_PORK", "COOKED_PORKCHOP");
		table.put("WOOD_DOOR", "OAK_DOOR");
		table.put("SNOW_BALL", "SNOWBALL");
		table.put("BOAT", "OAK_BOAT");
		table.put("CLAY_BRICK", "BRICK");
		table.put("STORAGE_MINECART", "CHEST_MINECART");
		table.put("POWERED_MINECART", "FURNACE_MINECART");
		table.put("WATCH", "CLOCK");
		table.put("RAW_FISH", "COD");
		table.put("COOKED_FISH", "COOKED_COD");
		table.put("INK_SACK", "INK_SAC");
		table.put("BED", "RED_BED");
		table.put("DIODE", "REPEATER");
		table.put("RAW_BEEF", "BEEF");
		table.put("RAW_CHICKEN", "CHICKEN");
		table.put("NETHER_STALK", "NETHER_WART");
		table.put("BREWING_STAND_ITEM", "BREWING_STAND");
		table.put("CAULDRON_ITEM", "CAULDRON");
		table.put("EYE_OF_ENDER", "ENDER_EYE");
		table.put("SPECKLED_MELON", "MELON_SLICE");
		table.put("MONSTER_EGG:BAT", "BAT_SPAWN_EGG");
		table.put("MONSTER_EGG:BLAZE", "BLAZE_SPAWN_EGG");
		table.put("MONSTER_EGG:CAVE_SPIDER", "CAVE_SPIDER_SPAWN_EGG");
		table.put("MONSTER_EGG:CHICKEN", "CHICKEN_SPAWN_EGG");
		table.put("MONSTER_EGG:COD", "COD_SPAWN_EGG");
		table.put("MONSTER_EGG:COW", "COW_SPAWN_EGG");
		table.put("MONSTER_EGG:CREEPER", "CREEPER_SPAWN_EGG");
		table.put("MONSTER_EGG:DOLPHIN", "DOLPHIN_SPAWN_EGG");
		table.put("MONSTER_EGG:DONKEY", "DONKEY_SPAWN_EGG");
		table.put("MONSTER_EGG:DROWNED", "DROWNED_SPAWN_EGG");
		table.put("MONSTER_EGG:ELDER_GUARDIAN", "GUARDIAN_SPAWN_EGG");
		table.put("MONSTER_EGG:ENDERMAN", "ENDERMAN_SPAWN_EGG");
		table.put("MONSTER_EGG:ENDERMITE", "ENDERMITE_SPAWN_EGG");
		table.put("MONSTER_EGG:EVOKER", "EVOKER_SPAWN_EGG");
		table.put("MONSTER_EGG:GHAST", "GHAST_SPAWN_EGG");
		table.put("MONSTER_EGG:GUARDIAN", "GUARDIAN_SPAWN_EGG");
		table.put("MONSTER_EGG:HORSE", "HORSE_SPAWN_EGG");
		table.put("MONSTER_EGG:HUSK", "HUSK_SPAWN_EGG");
		table.put("MONSTER_EGG:LLAMA", "LLAMA_SPAWN_EGG");
		table.put("MONSTER_EGG:MAGMA_CUBE", "MAGMA_CUBE_SPAWN_EGG");
		table.put("MONSTER_EGG:MOOSHROOM", "MOOSHROOM_SPAWN_EGG");
		table.put("MONSTER_EGG:MULE", "MULE_SPAWN_EGG");
		table.put("MONSTER_EGG:OCELOT", "OCELOT_SPAWN_EGG");
		table.put("MONSTER_EGG:PARROT", "PARROT_SPAWN_EGG");
		table.put("MONSTER_EGG:PHANTOM", "PHANTOM_SPAWN_EGG");
		table.put("MONSTER_EGG:PIG", "PIG_SPAWN_EGG");
		table.put("MONSTER_EGG:POLAR_BEAR", "POLAR_BEAR_SPAWN_EGG");
		table.put("MONSTER_EGG:PUFFERFISH", "PUFFERFISH_SPAWN_EGG");
		table.put("MONSTER_EGG:RABBIT", "RABBIT_SPAWN_EGG");
		table.put("MONSTER_EGG:SALMON", "SALMON_SPAWN_EGG");
		table.put("MONSTER_EGG:SHEEP", "SHEEP_SPAWN_EGG");
		table.put("MONSTER_EGG:SHULKER", "SHULKER_SPAWN_EGG");
		table.put("MONSTER_EGG:SILVERFISH", "SILVERFISH_SPAWN_EGG");
		table.put("MONSTER_EGG:SKELETON_HORSE", "SKELETON_HORSE_SPAWN_EGG");
		table.put("MONSTER_EGG:SKELETON", "SKELETON_SPAWN_EGG");
		table.put("MONSTER_EGG:SLIME", "SLIME_SPAWN_EGG");
		table.put("MONSTER_EGG:SPIDER", "SPIDER_SPAWN_EGG");
		table.put("MONSTER_EGG:SQUID", "SQUID_SPAWN_EGG");
		table.put("MONSTER_EGG:STRAY", "STRAY_SPAWN_EGG");
		table.put("MONSTER_EGG:TROPICAL_FISH", "TROPICAL_FISH_SPAWN_EGG");
		table.put("MONSTER_EGG:TURTLE", "TURTLE_SPAWN_EGG");
		table.put("MONSTER_EGG:VEX", "VEX_SPAWN_EGG");
		table.put("MONSTER_EGG:VILLAGER", "VILLAGER_SPAWN_EGG");
		table.put("MONSTER_EGG:VINDICATOR", "VINDICATOR_SPAWN_EGG");
		table.put("MONSTER_EGG:WITCH", "WITCH_SPAWN_EGG");
		table.put("MONSTER_EGG:WOLF", "WOLF_SPAWN_EGG");
		table.put("MONSTER_EGG:ZOMBIE_HORSE", "ZOMBIE_HORSE_SPAWN_EGG");
		table.put("MONSTER_EGG:ZOMBIE_PIGMAN", "ZOMBIE_PIGMAN_SPAWN_EGG");
		table.put("MONSTER_EGG:ZOMBIE", "ZOMBIE_SPAWN_EGG");
		table.put("MONSTER_EGG:ZOMBIE_VILLAGER", "ZOMBIE_VILLAGER_SPAWN_EGG");
		table.put("EXP_BOTTLE", "EXPERIENCE_BOTTLE");
		table.put("FIREBALL", "FIRE_CHARGE");
		table.put("BOOK_AND_QUILL", "WRITABLE_BOOK");
		table.put("FLOWER_POT_ITEM", "FLOWER_POT");
		table.put("CARROT_ITEM", "CARROT");
		table.put("POTATO_ITEM", "POTATO");
		table.put("EMPTY_MAP", "MAP");
		table.put("SKULL_ITEM", "PLAYER_HEAD");
		table.put("CARROT_STICK", "CARROT_ON_A_STICK");
		table.put("FIREWORK", "FIREWORK_ROCKET");
		table.put("FIREWORK_CHARGE", "FIREWORK_STAR");
		table.put("REDSTONE_COMPARATOR", "COMPARATOR");
		table.put("NETHER_BRICK_ITEM", "NETHER_BRICK");
		table.put("EXPLOSIVE_MINECART", "TNT_MINECART");
		table.put("IRON_BARDING", "IRON_HORSE_ARMOR");
		table.put("GOLD_BARDING", "GOLDEN_HORSE_ARMOR");
		table.put("DIAMOND_BARDING", "DIAMOND_HORSE_ARMOR");
		table.put("LEASH", "LEAD");
		table.put("COMMAND_MINECART", "COMMAND_BLOCK_MINECART");
		table.put("BANNER", "WHITE_BANNER");
		table.put("BANNER:1", "ORANGE_BANNER");
		table.put("BANNER:2", "MAGENTA_BANNER");
		table.put("BANNER:3", "LIGHT_BLUE_BANNER");
		table.put("BANNER:4", "YELLOW_BANNER");
		table.put("BANNER:5", "LIME_BANNER");
		table.put("BANNER:6", "PINK_BANNER");
		table.put("BANNER:7", "GRAY_BANNER");
		table.put("BANNER:8", "LIGHT_GRAY_BANNER");
		table.put("BANNER:9", "CYAN_BANNER");
		table.put("BANNER:10", "PURPLE_BANNER");
		table.put("BANNER:11", "BLUE_BANNER");
		table.put("BANNER:12", "BROWN_BANNER");
		table.put("BANNER:13", "GREEN_BANNER");
		table.put("BANNER:14", "RED_BANNER");
		table.put("BANNER:15", "BLACK_BANNER");
		table.put("SPRUCE_DOOR_ITEM", "SPRUCE_DOOR");
		table.put("BIRCH_DOOR_ITEM", "BIRCH_DOOR");
		table.put("JUNGLE_DOOR_ITEM", "JUNGLE_DOOR");
		table.put("ACACIA_DOOR_ITEM", "ACACIA_DOOR");
		table.put("DARK_OAK_DOOR_ITEM", "DARK_OAK_DOOR");
		table.put("CHORUS_FRUIT_POPPED", "POPPED_CHORUS_FRUIT");
		table.put("DRAGONS_BREATH", "DRAGON_BREATH");
		table.put("BOAT_SPRUCE", "SPRUCE_BOAT");
		table.put("BOAT_BIRCH", "BIRCH_BOAT");
		table.put("BOAT_JUNGLE", "JUNGLE_BOAT");
		table.put("BOAT_ACACIA", "ACACIA_BOAT");
		table.put("BOAT_DARK_OAK", "DARK_OAK_BOAT");
		table.put("TOTEM", "TOTEM_OF_UNDYING");
		table.put("GOLD_RECORD", "MUSIC_DISC_13");
		table.put("GREEN_RECORD", "MUSIC_DISC_CAT");
		table.put("RECORD_3", "MUSIC_DISC_BLOCKS");
		table.put("RECORD_4", "MUSIC_DISC_CHIRP");
		table.put("RECORD_5", "MUSIC_DISC_FAR");
		table.put("RECORD_6", "MUSIC_DISC_MALL");
		table.put("RECORD_7", "MUSIC_DISC_MELLOHI");
		table.put("RECORD_8", "MUSIC_DISC_STAL");
		table.put("RECORD_9", "MUSIC_DISC_STRAD");
		table.put("RECORD_10", "MUSIC_DISC_WARD");
		table.put("RECORD_11", "MUSIC_DISC_11");
		table.put("RECORD_12", "MUSIC_DISC_WAIT");

	}

	/**
	 * @author poma123
	 * 
	 * @param plugin
	 * 
	 *            Update old configuration files for 1.13 release
	 */
	public static boolean updateOnePointThirteen(final ASkyBlock plugin) {

		plugin.getLogger().info("Starting updating the configuration files for 1.13...");
		for (File file : new File(plugin.getDataFolder().getAbsolutePath()).listFiles()) {
			// update config.yml for 1.13

			FileConfiguration ymlfile = YamlConfiguration.loadConfiguration(file);
			if (file.getName().equals("config.yml")) {
				String content = null;
				try {

					content = FileUtils.readFileToString(file, "UTF-8");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// Update chestItems

				String[] chestItems = ymlfile.getString("island.chestItems").split(" ");
				String oldItems = String.join(" ", chestItems);

				for (int i = 0; i < chestItems.length; i++) {

					String[] items = chestItems[i].split(":");

					if (items.length > 2) {
						if (table.containsKey(items[0] + ":" + items[1])) {

							chestItems[i] = table.get(items[0] + ":" + items[1]) + ":" + items[2];
						}
					} else if (items.length == 2) {
						if (table.containsKey(items[0])) {

							chestItems[i] = table.get(items[0]) + ":" + items[1];
						}
					}
				}

				String chestItemsOutput = String.join(" ", chestItems);
				content = content.replace("chestItems: '" + oldItems + "'", "chestItems: '" + chestItemsOutput + "'");

				// Update biomes and icons

				String biomeName = null;

				for (String s : ymlfile.getConfigurationSection("biomes").getKeys(false)) {
					try {
						Biome.valueOf(s);
					} catch (Exception e) {
						if (table.containsKey(s)) {
							biomeName = table.get(s);
							content = content.replace(s + ":", biomeName + ":");
						} else {
							plugin.getLogger().warning(
									"[AcidIsland 1.13 Updater] Can't update the biome: " + s + ". Skipping...");
						}
					}

					String icon = ymlfile.get("biomes." + s + ".icon").toString();
					if (table.containsKey(icon)) {
						String output = table.get(icon);
						content = content.replace("    icon: " + icon + System.lineSeparator(), "    icon: " + output + System.lineSeparator());
					}

				}

				// Update schematic biomes and icons
				for (String s : ymlfile.getConfigurationSection("schematicsection.schematics").getKeys(false)) {
					if (ymlfile.get("schematicsection.schematics." + s + ".biome") != null) {
						String biome = ymlfile.get("schematicsection.schematics." + s + ".biome").toString();
						try {
							Biome.valueOf(biome);
						} catch (Exception e) {
							if (table.containsKey(biome)) {
								biomeName = table.get(biome);
								content = content.replace(biome + ":", biomeName + ":");
							} else {
								plugin.getLogger().warning(
										"[AcidIsland 1.13 Updater] Can't update the biome: " + biome + ". Skipping...");
							}
						}
					}

					String icon = ymlfile.get("schematicsection.schematics." + s + ".icon").toString();
					if (table.containsKey(icon)) {
						String output = table.get(icon);
						content = content.replace("    icon: " + icon, "    icon: " + output);
					}

				}

				try {
					FileUtils.writeStringToFile(file, content, "UTF-8");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				plugin.reloadConfig();
				plugin.getLogger().info("config.yml succesfully updated for 1.13!");
			}

			// update challenges.yml for 1.13
			if (file.getName().equals("challenges.yml")) {
				String content = null;
				try {

					content = FileUtils.readFileToString(file, "UTF-8");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// Update levelUnlock section rewards
				for (String s : ymlfile.getConfigurationSection("challenges.levelUnlock").getKeys(false)) {
					String reward = ymlfile.getString("challenges.levelUnlock." + s + ".itemReward");
					if (reward != null) {
						String[] space = reward.split(" ");
						for (int i = 0; i < space.length; i++) {
							String[] splitted = space[i].split(":");
							if (splitted.length == 2) {
								if (table.containsKey(splitted[0])) {
									// content = content.replace(" itemReward: " + splitted[0] + ":" + splitted[1],
									// " itemReward: " + table.get(splitted[0]) + ":" + splitted[1]);
									space[i] = table.get(splitted[0]);
								}
							}
							if (splitted.length > 2) {
								if (table.containsKey(splitted[0] + ":" + splitted[1])) {
									// content = content.replace(" itemReward: " + splitted[0] + ":" + splitted[1] +
									// ":" + splitted[2], " itemReward: " + table.get(splitted[0] + ":" +
									// splitted[1]) + ":" + splitted[2]);
									space[i] = table.get(splitted[0] + ":" + splitted[1]) + ":" + splitted[2];
								}
							}
							String output = String.join(" ", space);
							content = content.replace("      itemReward: '" + reward + "'",
									"      itemReward: '" + output + "'");
						}
						

					}
				}

				// Update challenges itemrewards, icons, required items
				for (String s : ymlfile.getConfigurationSection("challenges.challengeList").getKeys(false)) {
					String icon = ymlfile.get("challenges.challengeList." + s + ".icon").toString();
					if (table.containsKey(icon)) {
						String output = table.get(icon);
						content = content.replace("      icon: " + icon + System.lineSeparator(), "      icon: " + output + System.lineSeparator());
					}
					String itemReward = ymlfile.getString("challenges.challengeList." + s + ".itemReward");
					if (itemReward != null) {
						String[] space = itemReward.split(" ");
						for (int i = 0; i < space.length; i++) {
							String[] splitted = space[i].split(":");
							if (splitted.length == 2) {
								if (table.containsKey(splitted[0])) {
									// content = content.replace(" itemReward: " + splitted[0] + ":" + splitted[1],
									// " itemReward: " + table.get(splitted[0]) + ":" + splitted[1]);
									space[i] = table.get(splitted[0]);
								}
							}
							if (splitted.length > 2) {
								if (table.containsKey(splitted[0] + ":" + splitted[1])) {
									// content = content.replace(" itemReward: " + splitted[0] + ":" + splitted[1] +
									// ":" + splitted[2], " itemReward: " + table.get(splitted[0] + ":" +
									// splitted[1]) + ":" + splitted[2]);
									space[i] = table.get(splitted[0] + ":" + splitted[1]) + ":" + splitted[2];
								}
							}

						}
						String output = String.join(" ", space);
						content = content.replace("      itemReward: '" + itemReward + "'",
								"      itemReward: '" + output + "'");
					}
					String repeatitemReward = ymlfile.getString("challenges.challengeList." + s + ".repeatItemReward");
					if (repeatitemReward != null) {
						String[] space = repeatitemReward.split(" ");
						for (int i = 0; i < space.length; i++) {
							String[] splitted = space[i].split(":");
							if (splitted.length == 2) {
								if (table.containsKey(splitted[0])) {
									// content = content.replace(" itemReward: " + splitted[0] + ":" + splitted[1],
									// " itemReward: " + table.get(splitted[0]) + ":" + splitted[1]);
									space[i] = table.get(splitted[0]);
								}
								
							}
							if (splitted.length > 2) {
								if (table.containsKey(splitted[0] + ":" + splitted[1])) {
									// content = content.replace(" itemReward: " + splitted[0] + ":" + splitted[1] +
									// ":" + splitted[2], " itemReward: " + table.get(splitted[0] + ":" +
									// splitted[1]) + ":" + splitted[2]);
									space[i] = table.get(splitted[0] + ":" + splitted[1]) + ":" + splitted[2];
								}
							}

						}
						String output = String.join(" ", space);
						content = content.replace("      repeatItemReward: '" + repeatitemReward + "'",
								"      repeatItemReward: '" + output + "'");
					}
					String requiredItems = ymlfile.getString("challenges.challengeList." + s + ".requiredItems");
					if (requiredItems != null) {
						String[] space = requiredItems.split(" ");
						for (int i = 0; i < space.length; i++) {
							String[] splitted = space[i].split(":");
							if (splitted.length == 2) {
								if (table.containsKey(splitted[0])) {
									// content = content.replace(" itemReward: " + splitted[0] + ":" + splitted[1],
									// " itemReward: " + table.get(splitted[0]) + ":" + splitted[1]);
									space[i] = table.get(splitted[0]);
								}
							}
							if (splitted.length > 2) {
								if (table.containsKey(splitted[0] + ":" + splitted[1])) {
									// content = content.replace(" itemReward: " + splitted[0] + ":" + splitted[1] +
									// ":" + splitted[2], " itemReward: " + table.get(splitted[0] + ":" +
									// splitted[1]) + ":" + splitted[2]);
									space[i] = table.get(splitted[0] + ":" + splitted[1]) + ":" + splitted[2];
								}
							}

						}
						String output = String.join(" ", space);
						content = content.replace("      requiredItems: '" + requiredItems + "'",
								"      requiredItems: '" + output + "'");
					}
					

				}
				try {
					FileUtils.writeStringToFile(file, content, "UTF-8");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				plugin.getLogger().info("challenges.yml succesfully updated for 1.13!");
			}
			// update blockvalues.yml for 1.13
			if (file.getName().equals("blockvalues.yml")) {
				String content = null;
				try {

					content = FileUtils.readFileToString(file, "UTF-8");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				for (String s : ymlfile.getConfigurationSection("blocks").getKeys(false)) {

					String[] splitted = s.split(": ");
					int value = ymlfile.getInt("blocks." + s);
					if (splitted[0].equals("BANNER")) {
						content = content.replace("  BANNER: " + value,
								"  WHITE_BANNER: " + value + System.lineSeparator() + "  ORANGE_BANNER: " + value
										+ System.lineSeparator() + "  MAGENTA_BANNER: " + value + System.lineSeparator()
										+ "  LIGHT_BLUE_BANNER: " + value + System.lineSeparator() + "  YELLOW_BANNER: "
										+ value + System.lineSeparator() + "  LIME_BANNER: " + value
										+ System.lineSeparator() + "  PINK_BANNER: " + value + System.lineSeparator()
										+ "  GRAY_BANNER: " + value + System.lineSeparator() + "  LIGHT_GRAY_BANNER: "
										+ value + System.lineSeparator() + "  CYAN_BANNER: " + value
										+ System.lineSeparator() + "  PURPLE_BANNER: " + value + System.lineSeparator()
										+ "  BLUE_BANNER: " + value + System.lineSeparator() + "  BROWN_BANNER: "
										+ value + System.lineSeparator() + "  GREEN_BANNER: " + value
										+ System.lineSeparator() + "  RED_BANNER: " + value + System.lineSeparator()
										+ "  BLACK_BANNER: " + value + System.lineSeparator());
						continue;
					} else if (splitted[0].equals("BED_BLOCK")) {
						content = content.replace("  BED_BLOCK: " + value,
								"  WHITE_BED: " + value + System.lineSeparator() + "  ORANGE_BED: " + value
										+ System.lineSeparator() + "  MAGENTA_BED: " + value + System.lineSeparator()
										+ "  LIGHT_BLUE_BED: " + value + System.lineSeparator() + "  YELLOW_BED: "
										+ value + System.lineSeparator() + "  LIME_BED: " + value
										+ System.lineSeparator() + "  PINK_BED: " + value + System.lineSeparator()
										+ "  GRAY_BED: " + value + System.lineSeparator() + "  LIGHT_GRAY_BED: " + value
										+ System.lineSeparator() + "  CYAN_BED: " + value + System.lineSeparator()
										+ "  PURPLE_BED: " + value + System.lineSeparator() + "  BLUE_BED: " + value
										+ System.lineSeparator() + "  BROWN_BED: " + value + System.lineSeparator()
										+ "  GREEN_BED: " + value + System.lineSeparator() + "  RED_BED: " + value
										+ System.lineSeparator() + "  BLACK_BED: " + value + System.lineSeparator());
						continue;

					} else if (splitted[0].equals("BOAT")) {
						content = content.replace("  BOAT: " + value,
								"  BIRCH_BOAT: " + value + System.lineSeparator() + "  OAK_BOAT: " + value
										+ System.lineSeparator() + "  SPRUCE_BOAT: " + value + System.lineSeparator()
										+ "  DARK_OAK_BOAT: " + value + System.lineSeparator() + "  JUNGLE_BOAT: "
										+ value + System.lineSeparator() + "  ACACIA_BOAT: " + value
										+ System.lineSeparator());
						continue;
					} else if (splitted[0].equals("CARPET")) {
						content = content.replace("  CARPET: " + value,
								"  WHITE_CARPET: " + value + System.lineSeparator() + "  ORANGE_CARPET: " + value
										+ System.lineSeparator() + "  MAGENTA_CARPET: " + value + System.lineSeparator()
										+ "  LIGHT_BLUE_CARPET: " + value + System.lineSeparator() + "  YELLOW_CARPET: "
										+ value + System.lineSeparator() + "  LIME_CARPET: " + value
										+ System.lineSeparator() + "  PINK_CARPET: " + value + System.lineSeparator()
										+ "  GRAY_CARPET: " + value + System.lineSeparator() + "  LIGHT_GRAY_CARPET: "
										+ value + System.lineSeparator() + "  CYAN_CARPET: " + value
										+ System.lineSeparator() + "  PURPLE_CARPET: " + value + System.lineSeparator()
										+ "  BLUE_CARPET: " + value + System.lineSeparator() + "  BROWN_CARPET: "
										+ value + System.lineSeparator() + "  GREEN_CARPET: " + value
										+ System.lineSeparator() + "  RED_CARPET: " + value + System.lineSeparator()
										+ "  BLACK_CARPET: " + value + System.lineSeparator());

					} else if (splitted[0].equals("CONCRETE")) {
						content = content.replace("  CONCRETE: " + value, "  WHITE_CONCRETE: " + value
								+ System.lineSeparator() + "  ORANGE_CONCRETE: " + value + System.lineSeparator()
								+ "  MAGENTA_CONCRETE: " + value + System.lineSeparator() + "  LIGHT_BLUE_CONCRETE: "
								+ value + System.lineSeparator() + "  YELLOW_CONCRETE: " + value
								+ System.lineSeparator() + "  LIME_CONCRETE: " + value + System.lineSeparator()
								+ "  PINK_CONCRETE: " + value + System.lineSeparator() + "  GRAY_CONCRETE: " + value
								+ System.lineSeparator() + "  LIGHT_GRAY_CONCRETE: " + value + System.lineSeparator()
								+ "  CYAN_CONCRETE: " + value + System.lineSeparator() + "  PURPLE_CONCRETE: " + value
								+ System.lineSeparator() + "  BLUE_CONCRETE: " + value + System.lineSeparator()
								+ "  BROWN_CONCRETE: " + value + System.lineSeparator() + "  GREEN_CONCRETE: " + value
								+ System.lineSeparator() + "  RED_CONCRETE: " + value + System.lineSeparator()
								+ "  BLACK_CONCRETE: " + value + System.lineSeparator());
						continue;

					} else if (splitted[0].equals("CONCRETE_POWDER")) {
						content = content.replace("  CONCRETE_POWDER: " + value,
								"  WHITE_CONCRETE_POWDER: " + value + System.lineSeparator()
										+ "  ORANGE_CONCRETE_POWDER: " + value + System.lineSeparator()
										+ "  MAGENTA_CONCRETE_POWDER: " + value + System.lineSeparator()
										+ "  LIGHT_BLUE_CONCRETE_POWDER: " + value + System.lineSeparator()
										+ "  YELLOW_CONCRETE_POWDER: " + value + System.lineSeparator()
										+ "  LIME_CONCRETE_POWDER: " + value + System.lineSeparator()
										+ "  PINK_CONCRETE_POWDER: " + value + System.lineSeparator()
										+ "  GRAY_CONCRETE_POWDER: " + value + System.lineSeparator()
										+ "  LIGHT_GRAY_CONCRETE_POWDER: " + value + System.lineSeparator()
										+ "  CYAN_CONCRETE_POWDER: " + value + System.lineSeparator()
										+ "  PURPLE_CONCRETE_POWDER: " + value + System.lineSeparator()
										+ "  BLUE_CONCRETE_POWDER: " + value + System.lineSeparator()
										+ "  BROWN_CONCRETE_POWDER: " + value + System.lineSeparator()
										+ "  GREEN_CONCRETE_POWDER: " + value + System.lineSeparator()
										+ "  RED_CONCRETE_POWDER: " + value + System.lineSeparator()
										+ "  BLACK_CONCRETE_POWDER: " + value + System.lineSeparator());
						continue;

					} else if (splitted[0].equals("HARDENED_CLAY")) {
						content = content.replace("  HARDENED_CLAY: " + value, "  WHITE_TERRACOTTA: " + value
								+ System.lineSeparator() + "  ORANGE_TERRACOTTA: " + value + System.lineSeparator()
								+ "  MAGENTA_TERRACOTTA: " + value + System.lineSeparator()
								+ "  LIGHT_BLUE_TERRACOTTA: " + value + System.lineSeparator() + "  YELLOW_TERRACOTTA: "
								+ value + System.lineSeparator() + "  LIME_TERRACOTTA: " + value
								+ System.lineSeparator() + "  PINK_TERRACOTTA: " + value + System.lineSeparator()
								+ "  GRAY_TERRACOTTA: " + value + System.lineSeparator() + "  LIGHT_GRAY_TERRACOTTA: "
								+ value + System.lineSeparator() + "  CYAN_TERRACOTTA: " + value
								+ System.lineSeparator() + "  PURPLE_TERRACOTTA: " + value + System.lineSeparator()
								+ "  BLUE_TERRACOTTA: " + value + System.lineSeparator() + "  BROWN_TERRACOTTA: "
								+ value + System.lineSeparator() + "  GREEN_TERRACOTTA: " + value
								+ System.lineSeparator() + "  RED_TERRACOTTA: " + value + System.lineSeparator()
								+ "  BLACK_TERRACOTTA: " + value + System.lineSeparator());
						continue;

					} else if (splitted[0].equals("FENCE")) {
						content = content.replace("  FENCE: " + value,
								"  BIRCH_FENCE: " + value + System.lineSeparator() + "  OAK_FENCE: " + value
										+ System.lineSeparator() + "  SPRUCE_FENCE: " + value + System.lineSeparator()
										+ "  DARK_OAK_FENCE: " + value + System.lineSeparator() + "  JUNGLE_FENCE: "
										+ value + System.lineSeparator() + "  ACACIA_FENCE: " + value
										+ System.lineSeparator());
						continue;

					} else if (splitted[0].equals("FENCE_GATE")) {
						content = content.replace("  FENCE_GATE: " + value, "  BIRCH_FENCE_GATE: " + value
								+ System.lineSeparator() + "  OAK_FENCE_GATE: " + value + System.lineSeparator()
								+ "  SPRUCE_FENCE_GATE: " + value + System.lineSeparator() + "  DARK_OAK_FENCE_GATE: "
								+ value + System.lineSeparator() + "  JUNGLE_FENCE_GATE: " + value
								+ System.lineSeparator() + "  ACACIA_FENCE_GATE: " + value + System.lineSeparator());
						continue;

					} else if (splitted[0].equals("LEAVES")) {
						content = content.replace("  LEAVES: " + value,
								"  BIRCH_LEAVES: " + value + System.lineSeparator() + "  OAK_LEAVES: " + value
										+ System.lineSeparator() + "  SPRUCE_LEAVES: " + value + System.lineSeparator()
										+ "  DARK_OAK_LEAVES: " + value + System.lineSeparator() + "  JUNGLE_LEAVES: "
										+ value + System.lineSeparator());
						continue;

					} else if (splitted[0].equals("LEAVES_2")) {
						content = content.replace("  LEAVES_2: " + value,
								"  ACACIA_LEAVES: " + value + System.lineSeparator());
						continue;

					} else if (splitted[0].equals("LOG")) {
						content = content.replace("  LOG: " + value,
								"  BIRCH_LOG: " + value + System.lineSeparator() + "  OAK_LOG: " + value
										+ System.lineSeparator() + "  SPRUCE_LOG: " + value + System.lineSeparator()
										+ "  DARK_OAK_LOG: " + value + System.lineSeparator() + "  JUNGLE_LOG: " + value
										+ System.lineSeparator());
						continue;

					} else if (splitted[0].equals("LOG_2")) {
						content = content.replace("  LOG_2: " + value,
								"  ACACIA_LOG: " + value + System.lineSeparator());
						continue;

					} else if (splitted[0].equals("SKULL")) {
						content = content.replace("  SKULL: " + value,
								"  SKELETON_SKULL: " + value + System.lineSeparator() + "  WITHER_SKELETON_SKULL: "
										+ value + System.lineSeparator() + "  ZOMBIE_HEAD: " + value
										+ System.lineSeparator() + "  CREEPER_HEAD: " + value + System.lineSeparator()
										+ "  PLAYER_HEAD: " + value + System.lineSeparator() + "  DRAGON_HEAD: " + value
										+ System.lineSeparator());
						continue;

					} else if (splitted[0].equals("STAINED_GLASS")) {
						content = content.replace("  STAINED_GLASS: " + value, "  WHITE_STAINED_GLASS: " + value
								+ System.lineSeparator() + "  ORANGE_STAINED_GLASS: " + value + System.lineSeparator()
								+ "  MAGENTA_STAINED_GLASS: " + value + System.lineSeparator()
								+ "  LIGHT_BLUE_STAINED_GLASS: " + value + System.lineSeparator()
								+ "  YELLOW_STAINED_GLASS: " + value + System.lineSeparator() + "  LIME_STAINED_GLASS: "
								+ value + System.lineSeparator() + "  PINK_STAINED_GLASS: " + value
								+ System.lineSeparator() + "  GRAY_STAINED_GLASS: " + value + System.lineSeparator()
								+ "  LIGHT_GRAY_STAINED_GLASS: " + value + System.lineSeparator()
								+ "  CYAN_STAINED_GLASS: " + value + System.lineSeparator() + "  PURPLE_STAINED_GLASS: "
								+ value + System.lineSeparator() + "  BLUE_STAINED_GLASS: " + value
								+ System.lineSeparator() + "  BROWN_STAINED_GLASS: " + value + System.lineSeparator()
								+ "  GREEN_STAINED_GLASS: " + value + System.lineSeparator() + "  RED_STAINED_GLASS: "
								+ value + System.lineSeparator() + "  BLACK_STAINED_GLASS: " + value
								+ System.lineSeparator());
						continue;
					} else if (splitted[0].equals("STAINED_GLASS_PANE")) {
						content = content.replace("  STAINED_GLASS_PANE: " + value,
								"  WHITE_STAINED_GLASS_PANE: " + value + System.lineSeparator()
										+ "  ORANGE_STAINED_GLASS_PANE: " + value + System.lineSeparator()
										+ "  MAGENTA_STAINED_GLASS_PANE: " + value + System.lineSeparator()
										+ "  LIGHT_BLUE_STAINED_GLASS_PANE: " + value + System.lineSeparator()
										+ "  YELLOW_STAINED_GLASS_PANE: " + value + System.lineSeparator()
										+ "  LIME_STAINED_GLASS_PANE: " + value + System.lineSeparator()
										+ "  PINK_STAINED_GLASS_PANE: " + value + System.lineSeparator()
										+ "  GRAY_STAINED_GLASS_PANE: " + value + System.lineSeparator()
										+ "  LIGHT_GRAY_STAINED_GLASS_PANE: " + value + System.lineSeparator()
										+ "  CYAN_STAINED_GLASS_PANE: " + value + System.lineSeparator()
										+ "  PURPLE_STAINED_GLASS_PANE: " + value + System.lineSeparator()
										+ "  BLUE_STAINED_GLASS_PANE: " + value + System.lineSeparator()
										+ "  BROWN_STAINED_GLASS_PANE: " + value + System.lineSeparator()
										+ "  GREEN_STAINED_GLASS_PANE: " + value + System.lineSeparator()
										+ "  RED_STAINED_GLASS_PANE: " + value + System.lineSeparator()
										+ "  BLACK_STAINED_GLASS_PANE: " + value + System.lineSeparator());
						continue;
					} else if (splitted[0].equals("TRAP_DOOR")) {
						content = content.replace("  TRAP_DOOR: " + value, "  BIRCH_TRAPDOOR: " + value
								+ System.lineSeparator() + "  OAK_TRAPDOOR: " + value + System.lineSeparator()
								+ "  SPRUCE_TRAPDOOR: " + value + System.lineSeparator() + "  DARK_OAK_TRAPDOOR: "
								+ value + System.lineSeparator() + "  JUNGLE_TRAPDOOR: " + value
								+ System.lineSeparator() + "  ACACIA_TRAPDOOR: " + value + System.lineSeparator());
						continue;
					} else if (splitted[0].equals("WOOD_BUTTON")) {
						content = content.replace("  WOOD_BUTTON: " + value,
								"  BIRCH_BUTTON: " + value + System.lineSeparator() + "  OAK_BUTTON: " + value
										+ System.lineSeparator() + "  SPRUCE_BUTTON: " + value + System.lineSeparator()
										+ "  DARK_OAK_BUTTON: " + value + System.lineSeparator() + "  JUNGLE_BUTTON: "
										+ value + System.lineSeparator() + "  ACACIA_BUTTON: " + value
										+ System.lineSeparator());
						continue;
					} else if (splitted[0].equals("WOOD_DOOR")) {
						content = content.replace("  WOOD_DOOR: " + value,
								"  BIRCH_DOOR: " + value + System.lineSeparator() + "  OAK_DOOR: " + value
										+ System.lineSeparator() + "  SPRUCE_DOOR: " + value + System.lineSeparator()
										+ "  DARK_OAK_DOOR: " + value + System.lineSeparator() + "  JUNGLE_DOOR: "
										+ value + System.lineSeparator() + "  ACACIA_DOOR: " + value
										+ System.lineSeparator());
						continue;
					} else if (splitted[0].equals("WOOD_DOUBLE_STEP")) {
						content = content.replace("  WOOD_DOUBLE_STEP: " + value,
								"  BIRCH_SLAB: " + value + System.lineSeparator() + "  OAK_SLAB: " + value
										+ System.lineSeparator() + "  SPRUCE_SLAB: " + value + System.lineSeparator()
										+ "  DARK_OAK_SLAB: " + value + System.lineSeparator() + "  JUNGLE_SLAB: "
										+ value + System.lineSeparator() + "  ACACIA_SLAB: " + value
										+ System.lineSeparator());
						continue;
					} else if (splitted[0].equals("WOOD_PLATE")) {
						content = content.replace("  WOOD_PLATE: " + value,
								"  BIRCH_PRESSURE_PLATE: " + value + System.lineSeparator() + "  OAK_PRESSURE_PLATE: "
										+ value + System.lineSeparator() + "  SPRUCE_PRESSURE_PLATE: " + value
										+ System.lineSeparator() + "  DARK_OAK_PRESSURE_PLATE: " + value
										+ System.lineSeparator() + "  JUNGLE_PRESSURE_PLATE: " + value
										+ System.lineSeparator() + "  ACACIA_PRESSURE_PLATE: " + value
										+ System.lineSeparator());
						continue;
					} else if (splitted[0].equals("WOOD_STAIRS")) {
						content = content.replace("  WOOD_STAIRS: " + value,
								"  BIRCH_STAIRS: " + value + System.lineSeparator() + "  OAK_STAIRS: " + value
										+ System.lineSeparator() + "  SPRUCE_STAIRS: " + value + System.lineSeparator()
										+ "  DARK_OAK_STAIRS: " + value + System.lineSeparator() + "  JUNGLE_STAIRS: "
										+ value + System.lineSeparator() + "  ACACIA_STAIRS: " + value
										+ System.lineSeparator());
						continue;
					} else if (splitted[0].equals("WOOD_STEP")) {
						content = content.replace("  WOOD_STEP: " + value,
								"  BIRCH_SLAB: " + value + System.lineSeparator() + "  OAK_SLAB: " + value
										+ System.lineSeparator() + "  SPRUCE_SLAB: " + value + System.lineSeparator()
										+ "  DARK_OAK_SLAB: " + value + System.lineSeparator() + "  JUNGLE_SLAB: "
										+ value + System.lineSeparator() + "  ACACIA_SLAB: " + value
										+ System.lineSeparator());
						continue;
					} else if (splitted[0].equals("WOOL")) {
						content = content.replace("  WOOL: " + value, "  WHITE_WOOL: " + value + System.lineSeparator()
								+ "  ORANGE_WOOL: " + value + System.lineSeparator() + "  MAGENTA_WOOL: " + value
								+ System.lineSeparator() + "  LIGHT_BLUE_WOOL: " + value + System.lineSeparator()
								+ "  YELLOW_WOOL: " + value + System.lineSeparator() + "  LIME_WOOL: " + value
								+ System.lineSeparator() + "  PINK_WOOL: " + value + System.lineSeparator()
								+ "  GRAY_WOOL: " + value + System.lineSeparator() + "  LIGHT_GRAY_WOOL: " + value
								+ System.lineSeparator() + "  CYAN_WOOL: " + value + System.lineSeparator()
								+ "  PURPLE_WOOL: " + value + System.lineSeparator() + "  BLUE_WOOL: " + value
								+ System.lineSeparator() + "  BROWN_WOOL: " + value + System.lineSeparator()
								+ "  GREEN_WOOL: " + value + System.lineSeparator() + "  RED_WOOL: " + value
								+ System.lineSeparator() + "  BLACK_WOOL: " + value + System.lineSeparator());
						continue;
					} else if (table.containsKey(splitted[0])) {
						String newName = table.get(splitted[0]);
						content = content.replace(splitted[0] + ": ", newName + ": ");
					}

				}

				try {
					FileUtils.writeStringToFile(file, content, "UTF-8");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				if (!content.contains("#TUBE_CORAL")) {
					try {

						FileWriter wr = null;
						wr = new FileWriter(file, true);
						BufferedWriter bw = new BufferedWriter(wr);
						bw.newLine();
						bw.write("  #TUBE_CORAL: 10");
						bw.newLine();
						bw.write("  #BRAIN_CORAL: 10");
						bw.newLine();
						bw.write("  #BUBBLE_CORAL: 10");
						bw.newLine();
						bw.write("  #FIRE_CORAL: 10");
						bw.newLine();
						bw.write("  #HORN_CORAL: 10");
						bw.newLine();
						bw.write("  #TUBE_CORAL_FAN: 10");
						bw.newLine();
						bw.write("  #BRAIN_CORAL_FAN: 10");
						bw.newLine();
						bw.write("  #BUBBLE_CORAL_FAN: 10");
						bw.newLine();
						bw.write("  #FIRE_CORAL_FAN: 10");
						bw.newLine();
						bw.write("  #HORN_CORAL_FAN: 10");
						bw.newLine();
						bw.write("  #TUBE_CORAL_BLOCK: 10");
						bw.newLine();
						bw.write("  #BRAIN_CORAL_BLOCK: 10");
						bw.newLine();
						bw.write("  #BUBBLE_CORAL_BLOCK: 10");
						bw.newLine();
						bw.write("  #FIRE_CORAL_BLOCK: 10");
						bw.newLine();
						bw.write("  #HORN_CORAL_BLOCK: 10");
						bw.newLine();
						bw.write("  #DEAD_TUBE_CORAL_BLOCK: 1");
						bw.newLine();
						bw.write("  #DEAD_BRAIN_CORAL_BLOCK: 1");
						bw.newLine();
						bw.write("  #DEAD_BUBBLE_CORAL_BLOCK: 1");
						bw.newLine();
						bw.write("  #DEAD_FIRE_CORAL_BLOCK: 1");
						bw.newLine();
						bw.write("  #DEAD_HORN_CORAL_BLOCK: 1");
						bw.newLine();
						bw.flush();
						bw.close();
					} catch (Exception e) {
						e.printStackTrace();
						return false;
					}

					plugin.getLogger().info("blockvalues.yml succesfully updated for 1.13!");
				}
			}
			// update controlpanel.yml for 1.13
			if (file.getName().equals("controlpanel.yml")) {
				String content = null;
				try {

					content = FileUtils.readFileToString(file, "UTF-8");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				for (String s : ymlfile.getConfigurationSection("default.buttons").getKeys(false)) {
					String icon = ymlfile.get("default.buttons." + s + ".material").toString();
					if (table.containsKey(icon)) {
						String output = table.get(icon);
						content = content.replace("      material: " + icon + System.lineSeparator(), "      material: " + output + System.lineSeparator());
					}
				}
				
				
				try {
					FileUtils.writeStringToFile(file, content, "UTF-8");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				plugin.getLogger().info("controlpanel.yml succesfully updated for 1.13!");
			}

		}

		if (plugin.getConfig().get("onePointThirteen") == null) {

			try {

				FileWriter wr = null;
				wr = new FileWriter(plugin.getDataFolder().getAbsolutePath() + File.separator + "config.yml", true);
				BufferedWriter bw = new BufferedWriter(wr);
				bw.newLine();
				bw.write(
						"#DO NOT DELETE! This value is needed for the configuration files updating (for version 1.13).");
				bw.newLine();
				bw.write("onePointThirteen: true");
				bw.newLine();
				bw.flush();
				bw.close();
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			plugin.reloadConfig();
		}
		return true;
	}

}
