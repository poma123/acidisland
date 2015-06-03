package com.wasteofplastic.acidisland.panels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.inventory.Inventory;

import com.wasteofplastic.acidisland.ASkyBlock;
import com.wasteofplastic.acidisland.Island;
import com.wasteofplastic.acidisland.Settings;
import com.wasteofplastic.acidisland.util.Util;
import com.wasteofplastic.acidisland.util.VaultHelper;

public class BiomesPanel implements Listener {
    private static ASkyBlock plugin = ASkyBlock.getPlugin();
    private static HashMap<UUID, List<BiomeItem>> biomeItems = new HashMap<UUID, List<BiomeItem>>();

    /**
     * Returns a customized panel of available Biomes for the player
     * 
     * @param player
     * @return custom Inventory object
     */
    public static Inventory getBiomePanel(Player player) {
	// Go through the available biomes and check permission

	int slot = 0;
	List<BiomeItem> items = new ArrayList<BiomeItem>();
	for (String biomeName : plugin.getConfig().getConfigurationSection("biomes").getKeys(false)) {
	    // Check the biome is actually real
	    try {
		Biome biome = Biome.valueOf(biomeName);
		// Check permission
		String permission = plugin.getConfig().getString("biomes." + biomeName + ".permission", "");
		if (permission.isEmpty() || VaultHelper.permission.has(player, permission)) {
		    // Build inventory item
		    // Get icon
		    String icon = plugin.getConfig().getString("biomes." + biomeName + ".icon", "SAPLING");
		    Material material = null;
		    try {
			material = Material.valueOf(icon);
		    } catch (Exception e) {
			plugin.getLogger().warning("Error parsing biome icon value " + icon + ". Using default SAPLING.");
			material = Material.SAPLING;
		    }
		    // Get cost
		    double cost = plugin.getConfig().getDouble("biomes." + biomeName + ".cost", Settings.biomeCost);
		    // Get friendly name
		    String name = plugin.getConfig().getString("biomes." + biomeName + ".friendlyname", Util.prettifyText(biomeName));
		    // Get description
		    String description = plugin.getConfig().getString("biomes." + biomeName + ".description", "");
		    // Get confirmation or not
		    boolean confirm = plugin.getConfig().getBoolean("biomes." + biomeName + ".confirm", false);
		    // Add item to list
		    // plugin.getLogger().info("DEBUG: " + description + name +
		    // confirm);
		    BiomeItem item = new BiomeItem(material, slot++, cost, description, name, confirm, biome);
		    // Add to item list
		    items.add(item);
		}
	    } catch (Exception e) {
		plugin.getLogger().severe("Could not recognize " + biomeName + " as valid Biome! Skipping...");
	    }
	}
	// Now create the inventory panel
	if (items.size() > 0) {
	    // Save the items for later retrieval when the player clicks on them
	    biomeItems.put(player.getUniqueId(), items);
	    // Make sure size is a multiple of 9
	    int size = items.size() + 8;
	    size -= (size % 9);
	    Inventory newPanel = Bukkit.createInventory(null, size, plugin.myLocale().biomePanelTitle);
	    // Fill the inventory and return
	    for (BiomeItem i : items) {
		newPanel.addItem(i.getItem());
	    }
	    return newPanel;
	} else {
	    player.sendMessage(ChatColor.RED + plugin.myLocale().errorCommandNotReady);
	    plugin.getLogger().warning("There are no biomes in config.yml so /island biomes will not work!");
	}
	return null;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
	Player player = (Player) event.getWhoClicked(); // The player that
	// clicked the item
	UUID playerUUID = player.getUniqueId();
	Inventory inventory = event.getInventory(); // The inventory that was
	// clicked in
	int slot = event.getRawSlot();
	// Check this is the right panel
	if (!inventory.getName().equals(plugin.myLocale().biomePanelTitle)) {
	    return;
	}
	if (slot == -999) {
	    player.closeInventory();
	    event.setCancelled(true);
	    return;
	}
	// Get the list of items for this player
	List<BiomeItem> thisPanel = biomeItems.get(player.getUniqueId());
	if (thisPanel == null) {
	    player.closeInventory();
	    event.setCancelled(true);
	    return;
	}
	if (slot >= 0 && slot < thisPanel.size()) {
	    event.setCancelled(true);
	    // plugin.getLogger().info("DEBUG: slot is " + slot);
	    // Do something
	    Biome biome = thisPanel.get(slot).getBiome();
	    if (biome != null) {
		event.setCancelled(true);
		if (Settings.useEconomy) {
		    // Check cost
		    double cost = thisPanel.get(slot).getPrice();
		    if (cost > 0D) {
			if (!VaultHelper.econ.has(player, cost)) {
			    player.sendMessage(ChatColor.RED + plugin.myLocale().minishopYouCannotAfford.replace("[description]", VaultHelper.econ.format(cost)));
			    return;
			} else {
			    VaultHelper.econ.withdrawPlayer(player, Settings.worldName, cost);
			    player.sendMessage(ChatColor.GREEN + plugin.myLocale().biomeYouBought.replace("[cost]", VaultHelper.econ.format(cost)));
			}
		    }
		}
	    }
	    player.closeInventory(); // Closes the inventory
	    // Actually set the biome
	    if (plugin.getPlayers().inTeam(playerUUID) && plugin.getPlayers().getTeamIslandLocation(playerUUID) != null) {
		setIslandBiome(plugin.getPlayers().getTeamIslandLocation(playerUUID), biome);
	    } else {
		setIslandBiome(plugin.getPlayers().getIslandLocation(player.getUniqueId()), biome);
	    }
	    player.sendMessage(ChatColor.GREEN + plugin.myLocale().biomeSet.replace("[biome]", thisPanel.get(slot).getName()));
	}
	return;
    }


    /**
     * Sets all blocks in an island to a specified biome type
     * 
     * @param islandLoc
     * @param biomeType
     */
    public static boolean setIslandBiome(final Location islandLoc, final Biome biomeType) {
	final Island island = plugin.getGrid().getIslandAt(islandLoc);
	if (island != null) {
	    island.getCenter().getBlock().setBiome(biomeType);
	    return true;
	} else {
	   return false; 
	}
    }
    
    /**
     * Ensures that any block when loaded will match the biome of the center column of the island
     * if it exists. Does not apply to spawn.
     * @param e
     */
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = false)
    public void onChunkLoad(ChunkLoadEvent e) {
	// Only affects overworld
	if (!e.getWorld().equals(ASkyBlock.getIslandWorld())) {
	    return;
	}
	Island island = plugin.getGrid().getIslandAt(e.getChunk().getX()*16, e.getChunk().getZ()*16);
	if (island != null && !island.isSpawn()) {
	    Biome biome = island.getCenter().getBlock().getBiome();
	    for (int x = 0; x< 16; x++) {
		for (int z = 0; z< 16; z++) {
		    // Set biome
		    e.getChunk().getBlock(x, 0, z).setBiome(biome);
		    // Check y down for snow etc.
		    switch (biome) {
		    case MESA:
		    case MESA_BRYCE:
		    case DESERT:
		    case JUNGLE:
		    case SAVANNA:
		    case SAVANNA_MOUNTAINS:
		    case SAVANNA_PLATEAU:
		    case SAVANNA_PLATEAU_MOUNTAINS:
		    case SWAMPLAND:
			boolean topBlockFound = false;
			for (int y = e.getWorld().getMaxHeight(); y >= Settings.sea_level; y--) {
			    Block b = e.getChunk().getBlock(x, y, z);
			    if (!b.getType().equals(Material.AIR)) {
				topBlockFound = true;
			    }
			    if (topBlockFound) {
				if (b.getType() == Material.ICE || b.getType() == Material.SNOW || b.getType() == Material.SNOW_BLOCK) {
				    b.setType(Material.AIR);
				} else {
				    // Finished with the removals once we hit non-offending blocks
				    break;
				}
			    }
			}
			break;
		    case HELL:
			topBlockFound = false;
			for (int y = e.getWorld().getMaxHeight(); y >= Settings.sea_level; y--) {
			    Block b = e.getChunk().getBlock(x, y, z);
			    if (!b.getType().equals(Material.AIR)) {
				topBlockFound = true;
			    }
			    if (topBlockFound) {
				if (b.getType() == Material.ICE || b.getType() == Material.SNOW || b.getType() == Material.SNOW_BLOCK
					|| b.getType() == Material.WATER || b.getType() == Material.STATIONARY_WATER) {
				    b.setType(Material.AIR);
				} else {
				    // Finished with the removals once we hit non-offending blocks
				    break;
				}
			    }
			}
			break;

		    default:
		    }
		}
	    }
	}
    }

}