/*******************************************************************************
 * This file is part of ASkyBlock.
 *
 *     ASkyBlock is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     ASkyBlock is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with ASkyBlock.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/

package com.wasteofplastic.acidisland.schematics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.block.DoubleChest;
import org.bukkit.block.Sign;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.json.simple.JSONValue;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.wasteofplastic.acidisland.nms.NMSAbstraction;
import com.wasteofplastic.org.jnbt.CompoundTag;
import com.wasteofplastic.org.jnbt.ListTag;
import com.wasteofplastic.org.jnbt.StringTag;
import com.wasteofplastic.org.jnbt.Tag;

public class IslandBlock {
    private short typeId;
   // private byte data;
    private int x;
    private int y;
    private int z;
    private List<String> signText;
    private BannerBlock banner;
    private SkullBlock skull;
    private PotBlock pot;
    private EntityType spawnerBlockType;
    // Chest contents
    private final Map<Byte,ItemStack> chestContents = new HashMap<>();
    protected static final Map<String, Material> WEtoM = new HashMap<>();
    protected static final Map<String, EntityType> WEtoME = new HashMap<>();

    static {
        // Establish the World Edit to Material look up
        // V1.8 items
     /*   if (!Bukkit.getServer().getVersion().contains("(MC: 1.7")) {

            WEtoM.put("ARMORSTAND",Material.ARMOR_STAND);
            WEtoM.put("ACACIA_DOOR",Material.ACACIA_DOOR_ITEM);
            WEtoM.put("BIRCH_DOOR",Material.BIRCH_DOOR_ITEM);
            WEtoM.put("BIRCH_STAIRS",Material.BIRCH_WOOD_STAIRS);
            WEtoM.put("DARK_OAK_DOOR",Material.DARK_OAK_DOOR_ITEM);
            WEtoM.put("JUNGLE_DOOR",Material.JUNGLE_DOOR_ITEM);
            WEtoM.put("SLIME",Material.SLIME_BLOCK);
            WEtoM.put("SPRUCE_DOOR",Material.SPRUCE_DOOR_ITEM);
        }*/
        WEtoM.put("BREWING_STAND",Material.BREWING_STAND);
        WEtoM.put("CARROT_ON_A_STICK",Material.CARROT_ON_A_STICK);
        WEtoM.put("CARROT",Material.CARROT);
        WEtoM.put("CAULDRON", Material.CAULDRON);
        WEtoM.put("CHEST_MINECART", Material.CHEST_MINECART);
        WEtoM.put("CLOCK", Material.CLOCK);
        WEtoM.put("COBBLESTONE_WALL",Material.COBBLESTONE_WALL);
        WEtoM.put("COMMAND_BLOCK",Material.COMMAND_BLOCK);
        WEtoM.put("COMMAND_BLOCK_MINECART",Material.COMMAND_BLOCK_MINECART);
        WEtoM.put("COMPARATOR",Material.COMPARATOR);
        WEtoM.put("COOKED_PORKCHOP", Material.COOKED_PORKCHOP);
        WEtoM.put("CLOCK", Material.CLOCK);
        WEtoM.put("CRAFTING_TABLE", Material.CRAFTING_TABLE);
        WEtoM.put("DIAMOND_HORSE_ARMOR",Material.DIAMOND_HORSE_ARMOR);
        WEtoM.put("DIAMOND_SHOVEL",Material.DIAMOND_SHOVEL);
        WEtoM.put("DYE",Material.LIME_DYE);
        WEtoM.put("ENCHANTING_TABLE", Material.ENCHANTING_TABLE); //1.11 rename
        WEtoM.put("END_PORTAL_FRAME",Material.END_PORTAL_FRAME);
        WEtoM.put("END_PORTAL", Material.END_PORTAL); // 1.11 rename 
        WEtoM.put("END_STONE", Material.END_STONE);
        WEtoM.put("EXPERIENCE_BOTTLE",Material.EXPERIENCE_BOTTLE);
        WEtoM.put("FILLED_MAP",Material.MAP);
        WEtoM.put("FIRE_CHARGE",Material.FIRE_CHARGE);
        WEtoM.put("FIREWORKS",Material.FIREWORK_ROCKET);
        WEtoM.put("FLOWER_POT", Material.FLOWER_POT);
        WEtoM.put("GLASS_PANE",Material.GLASS_PANE);
        WEtoM.put("GOLDEN_CHESTPLATE",Material.GOLDEN_CHESTPLATE);
        WEtoM.put("GOLDEN_HORSE_ARMOR",Material.GOLDEN_HORSE_ARMOR);
        WEtoM.put("GOLDEN_LEGGINGS",Material.GOLDEN_LEGGINGS);
        WEtoM.put("GOLDEN_PICKAXE",Material.GOLDEN_PICKAXE);
        WEtoM.put("GOLDEN_RAIL",Material.POWERED_RAIL);
        WEtoM.put("GOLDEN_SHOVEL",Material.GOLDEN_SHOVEL);
        WEtoM.put("GOLDEN_SWORD", Material.GOLDEN_SWORD);
        WEtoM.put("GOLDEN_HELMET", Material.GOLDEN_HELMET);
        WEtoM.put("GOLDEN_HOE", Material.GOLDEN_HOE);
        WEtoM.put("GOLDEN_AXE", Material.GOLDEN_AXE);
        WEtoM.put("GOLDEN_BOOTS", Material.GOLDEN_BOOTS);
        WEtoM.put("GUNPOWDER", Material.GUNPOWDER);
        WEtoM.put("WHITE_TERRACOTTA",Material.WHITE_TERRACOTTA);
		WEtoM.put("ORANGE_TERRACOTTA",Material.ORANGE_TERRACOTTA);
		WEtoM.put("MAGENTA_TERRACOTTA",Material.MAGENTA_TERRACOTTA);
		WEtoM.put("LIGHT_BLUE_TERRACOTTA",Material.LIGHT_BLUE_TERRACOTTA);
		WEtoM.put("YELLOW_TERRACOTTA",Material.YELLOW_TERRACOTTA);
		WEtoM.put("LIME_TERRACOTTA",Material.LIME_TERRACOTTA);
		WEtoM.put("PINK_TERRACOTTA",Material.PINK_TERRACOTTA);
		WEtoM.put("GRAY_TERRACOTTA",Material.GRAY_TERRACOTTA);
		WEtoM.put("LIGHT_GRAY_TERRACOTTA",Material.LIGHT_GRAY_TERRACOTTA);
		WEtoM.put("CYAN_TERRACOTTA",Material.CYAN_TERRACOTTA);
		WEtoM.put("PURPLE_TERRACOTTA",Material.PURPLE_TERRACOTTA);
		WEtoM.put("BLUE_TERRACOTTA",Material.BLUE_TERRACOTTA);
		WEtoM.put("BROWN_TERRACOTTA",Material.BROWN_TERRACOTTA);
		WEtoM.put("GREEN_TERRACOTTA",Material.GREEN_TERRACOTTA);
		WEtoM.put("RED_TERRACOTTA",Material.RED_TERRACOTTA);
		WEtoM.put("BLACK_TERRACOTTA",Material.BLACK_TERRACOTTA);
        WEtoM.put("HEAVY_WEIGHTED_PRESSURE_PLATE",Material.HEAVY_WEIGHTED_PRESSURE_PLATE);
        WEtoM.put("IRON_BARS",Material.IRON_BARS);
        WEtoM.put("IRON_HORSE_ARMOR",Material.IRON_HORSE_ARMOR);
        WEtoM.put("IRON_SHOVEL",Material.IRON_SHOVEL);
        WEtoM.put("LEAD",Material.LEAD);
        WEtoM.put("OAK_LEAVES",Material.OAK_LEAVES);
        WEtoM.put("BIRCH_LEAVES",Material.BIRCH_LEAVES);
        WEtoM.put("SPRUCE_LEAVES",Material.SPRUCE_LEAVES);
        WEtoM.put("ACACIA_LEAVES",Material.ACACIA_LEAVES);
        WEtoM.put("DARK_OAK_LEAVES",Material.DARK_OAK_LEAVES);
        WEtoM.put("JUNGLE_LEAVES",Material.JUNGLE_LEAVES);
        WEtoM.put("LIGHT_WEIGHTED_PRESSURE_PLATE",Material.LIGHT_WEIGHTED_PRESSURE_PLATE);
		WEtoM.put("OAK_LOG",Material.OAK_LOG);
        WEtoM.put("BIRCH_LOG",Material.BIRCH_LOG);
        WEtoM.put("SPRUCE_LOG",Material.SPRUCE_LOG);
        WEtoM.put("ACACIA_LOG",Material.ACACIA_LOG);
        WEtoM.put("DARK_OAK_LOG",Material.DARK_OAK_LOG);
        WEtoM.put("JUNGLE_LOG",Material.JUNGLE_LOG);
        WEtoM.put("MAP",Material.MAP);
        WEtoM.put("MYCELIUM", Material.MYCELIUM);
        WEtoM.put("NETHER_BRICK_FENCE",Material.NETHER_BRICK_FENCE);
        WEtoM.put("NETHER_WART",Material.NETHER_WART);
        WEtoM.put("NETHERBRICK",Material.NETHER_BRICK);
        //WEtoM.put("OAK_STAIRS",Material.OAK_STAIRS);
		WEtoM.put("OAK_STAIRS",Material.OAK_STAIRS);
        WEtoM.put("BIRCH_STAIRS",Material.BIRCH_STAIRS);
        WEtoM.put("SPRUCE_STAIRS",Material.SPRUCE_STAIRS);
        WEtoM.put("ACACIA_STAIRS",Material.ACACIA_STAIRS);
        WEtoM.put("DARK_OAK_STAIRS",Material.DARK_OAK_STAIRS);
        WEtoM.put("JUNGLE_STAIRS",Material.JUNGLE_STAIRS);
        WEtoM.put("PISTON",Material.PISTON);
       // WEtoM.put("PLANKS",Material.PLANKS);
		WEtoM.put("OAK_PLANKS",Material.OAK_PLANKS);
        WEtoM.put("BIRCH_PLANKS",Material.BIRCH_PLANKS);
        WEtoM.put("SPRUCE_PLANKS",Material.SPRUCE_PLANKS);
        WEtoM.put("ACACIA_PLANKS",Material.ACACIA_PLANKS);
        WEtoM.put("DARK_OAK_PLANKS",Material.DARK_OAK_PLANKS);
        WEtoM.put("JUNGLE_PLANKS",Material.JUNGLE_PLANKS);
        WEtoM.put("POTATO", Material.POTATO);
        WEtoM.put("RAIL",Material.RAIL);
        WEtoM.put("RECORD_11",Material.MUSIC_DISC_11);
        WEtoM.put("RECORD_13",Material.MUSIC_DISC_13);
        WEtoM.put("RECORD_BLOCKS",Material.MUSIC_DISC_BLOCKS);
        WEtoM.put("RECORD_CAT",Material.MUSIC_DISC_CAT);
        WEtoM.put("RECORD_CHIRP",Material.MUSIC_DISC_CHIRP);
        WEtoM.put("RECORD_FAR",Material.MUSIC_DISC_FAR);
        WEtoM.put("RECORD_MALL",Material.MUSIC_DISC_MALL);
        WEtoM.put("RECORD_MELLOHI",Material.MUSIC_DISC_MELLOHI);
        WEtoM.put("RECORD_STAL",Material.MUSIC_DISC_STAL);
        WEtoM.put("RECORD_STRAD",Material.MUSIC_DISC_STRAD);
        WEtoM.put("RECORD_WAIT",Material.MUSIC_DISC_WAIT);
        WEtoM.put("RECORD_WARD",Material.MUSIC_DISC_WARD);
        WEtoM.put("RED_FLOWER",Material.POPPY);
        WEtoM.put("REEDS",Material.SUGAR_CANE);
        WEtoM.put("REPEATER",Material.REPEATER);
        WEtoM.put("SKULL", Material.PLAYER_HEAD);
        WEtoM.put("SPAWN_EGG",Material.ZOMBIE_SPAWN_EGG);
        WEtoM.put("STICKY_PISTON",Material.STICKY_PISTON);
        WEtoM.put("STONE_BRICK_STAIRS",Material.STONE_BRICK_STAIRS);
        WEtoM.put("STONE_SHOVEL",Material.STONE_SHOVEL);
        WEtoM.put("STONE_SLAB",Material.STONE_SLAB);
        WEtoM.put("STONE_STAIRS",Material.COBBLESTONE_STAIRS);
        WEtoM.put("TNT_MINECART",Material.TNT_MINECART);
        WEtoM.put("WATERLILY",Material.LILY_PAD);
        WEtoM.put("WHEAT_SEEDS", Material.WHEAT_SEEDS);
        WEtoM.put("WOODEN_AXE",Material.WOODEN_AXE);
        //WOODEN_BUTTON
		WEtoM.put("OAK_BUTTON",Material.OAK_BUTTON);
        WEtoM.put("BIRCH_BUTTON",Material.BIRCH_BUTTON);
        WEtoM.put("SPRUCE_BUTTON",Material.SPRUCE_BUTTON);
        WEtoM.put("ACACIA_BUTTON",Material.ACACIA_BUTTON);
        WEtoM.put("DARK_OAK_BUTTON",Material.DARK_OAK_BUTTON);
        WEtoM.put("JUNGLE_BUTTON",Material.JUNGLE_BUTTON);
        //WOODEN_DOOR
		WEtoM.put("OAK_DOOR",Material.OAK_DOOR);
        WEtoM.put("BIRCH_DOOR",Material.BIRCH_DOOR);
        WEtoM.put("SPRUCE_DOOR",Material.SPRUCE_DOOR);
        WEtoM.put("ACACIA_DOOR",Material.ACACIA_DOOR);
        WEtoM.put("DARK_OAK_DOOR",Material.DARK_OAK_DOOR);
        WEtoM.put("JUNGLE_DOOR",Material.JUNGLE_DOOR);
        WEtoM.put("WOODEN_HOE",Material.WOODEN_HOE);
        WEtoM.put("WOODEN_PICKAXE",Material.WOODEN_PICKAXE);
        //WEtoM.put("WOODEN_PRESSURE_PLATE",Material.WOOD_PLATE);
		WEtoM.put("OAK_PRESSURE_PLATE",Material.OAK_PRESSURE_PLATE);
        WEtoM.put("BIRCH_PRESSURE_PLATE",Material.BIRCH_PRESSURE_PLATE);
        WEtoM.put("SPRUCE_PRESSURE_PLATE",Material.SPRUCE_PRESSURE_PLATE);
        WEtoM.put("ACACIA_PRESSURE_PLATE",Material.ACACIA_PRESSURE_PLATE);
        WEtoM.put("DARK_OAK_PRESSURE_PLATE",Material.DARK_OAK_PRESSURE_PLATE);
        WEtoM.put("JUNGLE_PRESSURE_PLATE",Material.JUNGLE_PRESSURE_PLATE);
        WEtoM.put("WOODEN_SHOVEL",Material.WOODEN_SHOVEL);
        //WEtoM.put("WOODEN_SLAB",Material.WOODEN_SLAB);
		WEtoM.put("OAK_SLAB",Material.OAK_SLAB);
        WEtoM.put("BIRCH_SLAB",Material.BIRCH_SLAB);
        WEtoM.put("SPRUCE_SLAB",Material.SPRUCE_SLAB);
        WEtoM.put("ACACIA_SLAB",Material.ACACIA_SLAB);
        WEtoM.put("DARK_OAK_SLAB",Material.DARK_OAK_SLAB);
        WEtoM.put("JUNGLE_SLAB",Material.JUNGLE_SLAB);
        WEtoM.put("WOODEN_SWORD",Material.WOODEN_SWORD);
        WEtoM.put("MUSHROOM_STEW",Material.MUSHROOM_STEW);
        // Entities
        WEtoME.put("LAVASLIME", EntityType.MAGMA_CUBE);
        WEtoME.put("ENTITYHORSE", EntityType.HORSE);
        WEtoME.put("OZELOT", EntityType.OCELOT);
        WEtoME.put("MUSHROOMCOW", EntityType.MUSHROOM_COW);
        WEtoME.put("MOOSHROOM", EntityType.MUSHROOM_COW); // 1.11 rename
        WEtoME.put("PIGZOMBIE", EntityType.PIG_ZOMBIE);
        WEtoME.put("ZOMBIE_PIGMAN", EntityType.PIG_ZOMBIE); // 1.11 rename
        WEtoME.put("CAVESPIDER", EntityType.CAVE_SPIDER);
        WEtoME.put("XPORB", EntityType.EXPERIENCE_ORB);
        WEtoME.put("XP_ORB", EntityType.EXPERIENCE_ORB); // 1.11 rename
        WEtoME.put("MINECARTRIDEABLE", EntityType.MINECART);
        WEtoME.put("MINECARTHOPPER", EntityType.MINECART_HOPPER);
        WEtoME.put("HOPPER_MINECART", EntityType.MINECART_HOPPER);
        WEtoME.put("MINECARTFURNACE", EntityType.MINECART_FURNACE);
        WEtoME.put("FURNACE_MINECART", EntityType.MINECART_FURNACE);
        WEtoME.put("MINECARTMOBSPAWNER", EntityType.MINECART_MOB_SPAWNER);
        WEtoME.put("SPAWNER_MINECART", EntityType.MINECART_MOB_SPAWNER); // 1.11 rename
        WEtoME.put("MINECARTTNT", EntityType.MINECART_TNT);
        WEtoME.put("TNT_MINECART", EntityType.MINECART_TNT); // 1.11
        WEtoME.put("LEASH_KNOT",EntityType.LEASH_HITCH); // 1.11
        WEtoME.put("MINECARTCHEST", EntityType.MINECART_CHEST);
        WEtoME.put("CHEST_MINECART", EntityType.MINECART_CHEST); //1.11 rename
        WEtoME.put("VILLAGERGOLEM", EntityType.IRON_GOLEM);
        WEtoME.put("ENDERDRAGON", EntityType.ENDER_DRAGON);
        WEtoME.put("PAINTING", EntityType.PAINTING);
        WEtoME.put("ITEMFRAME", EntityType.ITEM_FRAME);
        if (!Bukkit.getServer().getVersion().contains("(MC: 1.7")) {
            WEtoME.put("ENDERCRYSTAL", EntityType.ENDER_CRYSTAL);
            WEtoME.put("ARMORSTAND", EntityType.ARMOR_STAND);
        }
        // 1.10 entities and materials
        if (!Bukkit.getServer().getVersion().contains("(MC: 1.7") && !Bukkit.getServer().getVersion().contains("(MC: 1.8") && !Bukkit.getServer().getVersion().contains("(MC: 1.9")) {
            WEtoME.put("POLARBEAR", EntityType.POLAR_BEAR);
            WEtoM.put("ENDER_CRYSTAL", Material.END_CRYSTAL); // 1.11
        }
    }

    /**
     * @param x
     * @param y
     * @param z
     */
    public IslandBlock(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        signText = null;
        banner = null;
        skull = null;
        pot = null;
        spawnerBlockType = null;
    }
    /**
     * @return the type
     */
    public short getTypeId() {
        return typeId;
    }
    /**
     * @param type the type to set
     */
    public void setTypeId(short type) {
        this.typeId = type;
    }
    /**
     * @return the data
     */
    /*public int getData() {
        return data;
    }*/
    /**
     * @param data the data to set
     */
    /*public void setData(byte data) {
        this.data = data;
    }*/

    /**
     * @return the signText
     */
    public List<String> getSignText() {
        return signText;
    }
    /**
     * @param signText the signText to set
     */
    public void setSignText(List<String> signText) {
        this.signText = signText;
    }

    /**
     * @param i
     * @param b
     */
    public void setBlock(short i) {
        this.typeId = i;
    }

    /**
     * Sets this block up with all the banner data required
     * @param map
     */
    public void setBanner(Map<String, Tag> map) {
        banner = new BannerBlock();
        banner.prep(map);
    }
    /**
     * Sets this block up with all the skull data required
     * @param map
     * @param dataValue
     */
    public void setSkull(Map<String, Tag> map, BlockFace rotation) {
        skull = new SkullBlock();
        skull.prep(map, rotation);
    }
    public void setFlowerPot(Map<String, Tag> map){
        pot = new PotBlock();
        pot.prep(map);
    }

    /**
     * Sets the spawner type if this block is a spawner
     * @param tileData
     */
    public void setSpawnerType(Map<String, Tag> tileData) {
        //Bukkit.getLogger().info("DEBUG: " + tileData.toString());
        String creatureType = "";        
        if (tileData.containsKey("EntityId")) {
            creatureType = ((StringTag) tileData.get("EntityId")).getValue().toUpperCase();
        } else if (tileData.containsKey("SpawnData")) {
            // 1.9 format
            Map<String,Tag> spawnData = ((CompoundTag) tileData.get("SpawnData")).getValue();
            //Bukkit.getLogger().info("DEBUG: " + spawnData.toString());
            if (spawnData.containsKey("id")) {
                creatureType = ((StringTag) spawnData.get("id")).getValue().toUpperCase();
            }
        }
        //Bukkit.getLogger().info("DEBUG: creature type = " + creatureType);
        // The mob type might be prefixed with "Minecraft:"
        if (creatureType.startsWith("MINECRAFT:")) {
            creatureType = creatureType.substring(10);
        }
        if (WEtoME.containsKey(creatureType)) {
            spawnerBlockType = WEtoME.get(creatureType);
        } else {
            try {
                spawnerBlockType = EntityType.valueOf(creatureType);
            } catch (Exception e) {
                Bukkit.getLogger().warning("Spawner setting of " + creatureType + " is unknown for this server. Skipping.");
            }
        }
        //Bukkit.getLogger().info("DEBUG: spawnerblock type = " + spawnerBlockType);
    }

    /**
     * Sets this block's sign data
     * @param tileData
     */
    public void setSign(Map<String, Tag> tileData) {
        signText = new ArrayList<String>();
        List<String> text = new ArrayList<String>();
        for (int i = 1; i < 5; i++) {
            String line = ((StringTag) tileData.get("Text" + String.valueOf(i))).getValue();
            // This value can actually be a string that says null sometimes.
            if (line.equalsIgnoreCase("null")) {
                line = "";
            }
            //System.out.println("DEBUG: line " + i + " = '"+ line + "' of length " + line.length());
            text.add(line);
        }

        JSONParser parser = new JSONParser();
        ContainerFactory containerFactory = new ContainerFactory(){
            public List creatArrayContainer() {
                return new LinkedList();
            }

            public Map createObjectContainer() {
                return new LinkedHashMap();
            }

        };
        // This just removes all the JSON formatting and provides the raw text
        for (int line = 0; line < 4; line++) {
            String lineText = "";
            if (!text.get(line).equals("\"\"") && !text.get(line).isEmpty()) {
                //String lineText = text.get(line).replace("{\"extra\":[\"", "").replace("\"],\"text\":\"\"}", "");
                //Bukkit.getLogger().info("DEBUG: sign text = '" + text.get(line) + "'");
                if (text.get(line).startsWith("{")) {
                    // JSON string
                    try {

                        Map json = (Map)parser.parse(text.get(line), containerFactory);
                        List list = (List) json.get("extra");
                        //System.out.println("DEBUG1:" + JSONValue.toJSONString(list));
                        if (list != null) {
                            Iterator iter = list.iterator();
                            while(iter.hasNext()){
                                Object next = iter.next();
                                String format = JSONValue.toJSONString(next);
                                //System.out.println("DEBUG2:" + format);
                                // This doesn't see right, but appears to be the easiest way to identify this string as JSON...
                                if (format.startsWith("{")) {
                                    // JSON string
                                    Map jsonFormat = (Map)parser.parse(format, containerFactory);
                                    Iterator formatIter = jsonFormat.entrySet().iterator();
                                    while (formatIter.hasNext()) {
                                        Map.Entry entry = (Map.Entry)formatIter.next();
                                        //System.out.println("DEBUG3:" + entry.getKey() + "=>" + entry.getValue());
                                        String key = entry.getKey().toString();
                                        String value = entry.getValue().toString();
                                        if (key.equalsIgnoreCase("color")) {
                                            try {
                                                lineText += ChatColor.valueOf(value.toUpperCase());
                                            } catch (Exception noColor) {
                                                Bukkit.getLogger().warning("Unknown color " + value +" in sign when pasting schematic, skipping...");
                                            }
                                        } else if (key.equalsIgnoreCase("text")) {
                                            lineText += value;
                                        } else {
                                            // Formatting - usually the value is always true, but check just in case
                                            if (key.equalsIgnoreCase("obfuscated") && value.equalsIgnoreCase("true")) {
                                                lineText += ChatColor.MAGIC;
                                            } else if (key.equalsIgnoreCase("underlined") && value.equalsIgnoreCase("true")) {
                                                lineText += ChatColor.UNDERLINE;
                                            } else {
                                                // The rest of the formats
                                                try {
                                                    lineText += ChatColor.valueOf(key.toUpperCase());
                                                } catch (Exception noFormat) {
                                                    // Ignore
                                                    //System.out.println("DEBUG3:" + key + "=>" + value);
                                                    Bukkit.getLogger().warning("Unknown format " + value +" in sign when pasting schematic, skipping...");
                                                }
                                            }
                                        }   
                                    }
                                } else {
                                    // This is unformatted text. It is included in "". A reset is required to clear
                                    // any previous formatting
                                    if (format.length()>1) {
                                        lineText += ChatColor.RESET + format.substring(format.indexOf('"')+1,format.lastIndexOf('"'));
                                    }
                                } 
                            }
                        } else {
                            // No extra tag
                            json = (Map)parser.parse(text.get(line), containerFactory);
                            String value = (String) json.get("text");
                            //System.out.println("DEBUG text only?:" + value);
                            lineText += value;
                        }
                    } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } else {
                    // This is unformatted text (not JSON). It is included in "".
                    if (text.get(line).length() > 1) {
                        try {
                            lineText = text.get(line).substring(text.get(line).indexOf('"')+1,text.get(line).lastIndexOf('"'));
                        } catch (Exception e) {
                            //There may not be those "'s, so just use the raw line
                            lineText = text.get(line);
                        }
                    } else {
                        // just in case it isn't - show the raw line
                        lineText = text.get(line);
                    }
                }
                //Bukkit.getLogger().info("Line " + line + " is " + lineText);
            }
            signText.add(lineText);
        }
    }

    public void setBook(Map<String, Tag> tileData) {
        //Bukkit.getLogger().info("DEBUG: Book data ");
        Bukkit.getLogger().info(tileData.toString());
    }

    @SuppressWarnings("deprecation")
    public void setChest(NMSAbstraction nms, Map<String, Tag> tileData) {
        try {
            ListTag chestItems = (ListTag) tileData.get("Items");
            if (chestItems != null) {
                //int number = 0;
                for (Tag item : chestItems.getValue()) {
                    // Format for chest items is:
                    // id = short value of item id
                    // Damage = short value of item damage
                    // Count = the number of items
                    // Slot = the slot in the chest
                    // inventory

                    if (item instanceof CompoundTag) {
                        try {
                            // Id is a number
                            String itemType = (String) ((CompoundTag) item).getValue().get("id").getValue();
                     //       short itemDamage = (Short) ((CompoundTag) item).getValue().get("Damage").getValue();
                            byte itemAmount = (Byte) ((CompoundTag) item).getValue().get("Count").getValue();
                            byte itemSlot = (Byte) ((CompoundTag) item).getValue().get("Slot").getValue();
                            ItemStack chestItem = new ItemStack(Material.matchMaterial(itemType), itemAmount);
                            chestContents.put(itemSlot, chestItem);
                        } catch (ClassCastException ex) {
                            // Id is a material
                            String itemType = (String) ((CompoundTag) item).getValue().get("id").getValue();
                            try {
                                // Get the material
                                if (itemType.startsWith("minecraft:")) {
                                    String material = itemType.substring(10).toUpperCase();
                                    // Special case for non-standard material names
                                    Material itemMaterial;

                                    //Bukkit.getLogger().info("DEBUG: " + material);

                                    if (WEtoM.containsKey(material)) {
                                        //Bukkit.getLogger().info("DEBUG: Found in hashmap");
                                        itemMaterial = WEtoM.get(material);
                                    } else {
                                        //Bukkit.getLogger().info("DEBUG: Not in hashmap");
                                        itemMaterial = Material.valueOf(material);
                                    }
                                    short itemDamage = (Short) ((CompoundTag) item).getValue().get("Damage").getValue();
                                    byte itemAmount = (Byte) ((CompoundTag) item).getValue().get("Count").getValue();
                                    byte itemSlot = (Byte) ((CompoundTag) item).getValue().get("Slot").getValue();
                                    ItemStack chestItem = new ItemStack(itemMaterial, itemAmount, itemDamage);
                                    if (itemMaterial.equals(Material.WRITTEN_BOOK)) {
                                        chestItem = nms.setBook(item);
                                    }
                                    // Check for potions
                                    if (itemMaterial.toString().contains("POTION")) {
                                        chestItem = nms.setPotion(itemMaterial, item, chestItem);
                                    }
                                    chestContents.put(itemSlot, chestItem);
                                }
                            } catch (Exception exx) {
                                // Bukkit.getLogger().info(item.toString());
                                // Bukkit.getLogger().info(((CompoundTag)item).getValue().get("id").getName());
                                Bukkit.getLogger().severe(
                                        "Could not parse item [" + itemType.substring(10).toUpperCase() + "] in schematic - skipping!");
                                // Bukkit.getLogger().severe(item.toString());
                                exx.printStackTrace();
                            }

                        }

                        // Bukkit.getLogger().info("Set chest inventory slot "
                        // + itemSlot + " to " +
                        // chestItem.toString());
                    }
                }
                //Bukkit.getLogger().info("Added " + number + " items to chest");
            }
        } catch (Exception e) {
            Bukkit.getLogger().severe("Could not parse schematic file item, skipping!");
            // e.printStackTrace();
        }
    }


    /**
     * Paste this block at blockLoc
     * @param nms
     * @param blockLoc
     */
    //@SuppressWarnings("deprecation")
    @SuppressWarnings("deprecation")
    public void paste(NMSAbstraction nms, Location blockLoc, boolean usePhysics, Biome biome) {
        // Only paste air if it is below the sea level and in the overworld
        Block block = new Location(blockLoc.getWorld(), x, y, z).add(blockLoc).getBlock();
        block.setBiome(biome);
        nms.setBlockSuperFast(block, typeId, usePhysics);
        if (signText != null) {
            if (block.getType() != typeId) {
                block.setType(typeId);
            }
            // Sign
            Sign sign = (Sign) block.getState();
            int index = 0;
            for (String line : signText) {
                sign.setLine(index++, line);
            }
            sign.update(true, false);
        } else if (banner != null) {
            banner.set(block);
        } else if (skull != null){
            skull.set(block);
        } else if (pot != null){
            pot.set(nms, block);
        } else if (spawnerBlockType != null) {
            if (block.getType() != typeId) {
                block.setType(typeId);
            }
            CreatureSpawner cs = (CreatureSpawner)block.getState();
            cs.setSpawnedType(spawnerBlockType);
            //Bukkit.getLogger().info("DEBUG: setting spawner");
            cs.update(true, false);
        } else if (!chestContents.isEmpty()) {
            if (block.getType() != typeId) {
                block.setType(typeId);
            }
            //Bukkit.getLogger().info("DEBUG: inventory holder "+ block.getType());
            // Check if this is a double chest
            
            InventoryHolder chestBlock = (InventoryHolder) block.getState();
            //InventoryHolder iH = chestBlock.getInventory().getHolder();
            if (chestBlock instanceof DoubleChest) {
                //Bukkit.getLogger().info("DEBUG: double chest");
                DoubleChest doubleChest = (DoubleChest) chestBlock;
                for (ItemStack chestItem: chestContents.values()) {
                    doubleChest.getInventory().addItem(chestItem);
                }
            } else {
                // Single chest
                for (Entry<Byte, ItemStack> en : chestContents.entrySet()) {
                    //Bukkit.getLogger().info("DEBUG: " + en.getKey() + ","  + en.getValue());
                    chestBlock.getInventory().setItem(en.getKey(), en.getValue());
                }
            }
        }
    }

    /**
     * @return Vector for where this block is in the schematic
     */
    public Vector getVector() {
        return new Vector(x,y,z);
    }
}
