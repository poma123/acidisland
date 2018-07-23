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

package com.wasteofplastic.acidisland;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.ChatColor;
import org.bukkit.ChunkSnapshot;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import com.wasteofplastic.acidisland.util.Util;

/**
 * A class that calculates finds a safe spot asynchronously and then teleports the player there. 
 * @author tastybento
 * 
 */
public class SafeSpotTeleport {

    //private NMSAbstraction nms;
    //private ASkyBlock plugin;
    /**
     * Teleport to a safe place and if it fails, show a failure message
     * @param plugin
     * @param player
     * @param l
     * @param failureMessage
     */
    public SafeSpotTeleport(final ASkyBlock plugin, final Entity player, final Location l, final String failureMessage) {
        new SafeSpotTeleport(plugin, player, l, 1, failureMessage, false);
    }

    /**
     * Teleport to a safe place and set home
     * @param plugin
     * @param player
     * @param l
     * @param number
     */
    public SafeSpotTeleport(final ASkyBlock plugin, final Entity player, final Location l, final int number) {
        new SafeSpotTeleport(plugin, player, l, number, "", true);
    }

    /**
     * Teleport to a safe spot on an island
     * @param plugin
     * @param player
     * @param l
     */
    public SafeSpotTeleport(final ASkyBlock plugin, final Entity player, final Location l) {
        new SafeSpotTeleport(plugin, player, l, 1, "", false);
    } 
    /**
     * Teleport to a safe spot on an island

     * @param plugin
     * @param entity that is being teleported
     * @param islandLoc - the location of the island
     * @param homeNumber - the home number to set if setHome is true
     * @param failureMessage - the message to show the player if this cannot be done
     * @param setHome - if true, the resulting teleport location should become the player's home
     */
    public SafeSpotTeleport(final ASkyBlock plugin, final Entity entity, final Location islandLoc, final int homeNumber, final String failureMessage, final boolean setHome) {
        //this.plugin = plugin;
        //plugin.getLogger().info("DEBUG: running safe spot");
        // Get island
        Island island = plugin.getGrid().getIslandAt(islandLoc);
        if (island != null) {
            final World world = islandLoc.getWorld();
            // Get the chunks
            List<ChunkSnapshot> chunkSnapshot = new ArrayList<ChunkSnapshot>();
            // Add the center chunk
            chunkSnapshot.add(island.getCenter().toVector().toLocation(world).getChunk().getChunkSnapshot());
            // Add immediately adjacent chunks
            for (int x = islandLoc.getChunk().getX()-1; x <= islandLoc.getChunk().getX()+1; x++) {
                for (int z = islandLoc.getChunk().getZ()-1; z <= islandLoc.getChunk().getZ()+1; z++) {
                    if (x != islandLoc.getChunk().getX() || z != islandLoc.getChunk().getZ()) {
                        chunkSnapshot.add(world.getChunkAt(x, z).getChunkSnapshot());
                    }
                }
            }
            // Add the rest of the island protected area
            for (int x = island.getMinProtectedX() /16; x <= (island.getMinProtectedX() + island.getProtectionSize() - 1)/16; x++) {
                for (int z = island.getMinProtectedZ() /16; z <= (island.getMinProtectedZ() + island.getProtectionSize() - 1)/16; z++) {
                    // This includes the center spots again, so is not as efficient...
                    chunkSnapshot.add(world.getChunkAt(x, z).getChunkSnapshot());
                }  
            }
            //plugin.getLogger().info("DEBUG: size of chunk ss = " + chunkSnapshot.size());
            final List<ChunkSnapshot> finalChunk = chunkSnapshot;
            int maxHeight = world.getMaxHeight() - 2;
            if (world.getEnvironment().equals(Environment.NETHER)) {
                // We need to ignore the roof
                maxHeight -= 20;
            }
            final int worldHeight = maxHeight;
            //plugin.getLogger().info("DEBUG:world height = " + worldHeight);

            // Convert any spawn locations 
            ChunkSnapshot spawnChunk = null;
            Location spawnLoc = null;
            if (island.getOwner() != null) {
                HashMap<Integer,Location> teleport = plugin.getPlayers().getHomeLocations(island.getOwner());
                for (Entry<Integer, Location> loc : teleport.entrySet()) {
                    if (loc.getKey() < 0 && loc.getValue().getWorld().equals(islandLoc.getWorld())) {
                        spawnChunk = loc.getValue().getChunk().getChunkSnapshot();
                        spawnLoc = loc.getValue();
                        break;
                    }
                }
            }
            final ChunkSnapshot spawnChunkFinal = spawnChunk;
            final Location spawnLocFinal = spawnLoc;
            
            plugin.getServer().getScheduler().runTaskAsynchronously(plugin, new Runnable() {

                @SuppressWarnings("deprecation")
                @Override
                public void run() {
                    // Find a safe spot, defined as a solid block, with 2 air spaces above it
                    //long time = System.nanoTime();
                    int x = 0;
                    int y = 0;
                    int z = 0;
                    ChunkSnapshot safeChunk = null;
                    ChunkSnapshot portalChunk = null;
                    boolean safeSpotFound = false;
                    Vector safeSpotInChunk = null;
                    Vector portalPart = null;
                    double distance = 0D;
                    double safeDistance = 0D;
                    for (ChunkSnapshot chunk: finalChunk) {
                        for (x = 0; x< 16; x++) {
                            for (z = 0; z < 16; z++) {
                                // Work down from the entry point up
                                for (y = Math.min(chunk.getHighestBlockYAt(x, z), worldHeight); y >= 0; y--) {
                                    // Check for portal - only if this is not a safe home search
                                    if (!setHome && chunk.getBlockType(x, y, z) == Material.NETHER_PORTAL) {
                                        if (portalPart == null || (distance > islandLoc.toVector().distanceSquared(new Vector(x,y,z)))) {
                                            // First one found or a closer one, save the chunk the position and the distance
                                            portalChunk = chunk;
                                            portalPart = new Vector(x,y,z);
                                            distance = portalPart.distanceSquared(islandLoc.toVector());
                                        }
                                    }
                                    // Check for safe spot, but only if it is closer than one we have found already
                                    if (!safeSpotFound || (safeDistance > islandLoc.toVector().distanceSquared(new Vector(x,y,z)))) {
                                        // No safe spot yet, or closer distance
                                        if (checkBlock(chunk,x,y,z, worldHeight)) {
                                            safeChunk = chunk;
                                            safeSpotFound = true;
                                            safeSpotInChunk = new Vector(x,y,z);
                                            safeDistance = islandLoc.toVector().distanceSquared(safeSpotInChunk);
                                        }
                                    }
                                }
                            } //end z
                        } // end x
                        //if (safeSpotFound) {
                        //System.out.print("DEBUG: safe spot found " + safeSpotInChunk.toString());
                        //break search;
                        //}
                    }
                    // End search
                    // Check if the portal is safe (it should be)
                    if (portalPart != null) {
                        //System.out.print("DEBUG: Portal found");
                        // There is a portal available, but is it safe?
                        // Get the lowest portal spot
                        x = portalPart.getBlockX();
                        y = portalPart.getBlockY();
                        z = portalPart.getBlockZ();
                        while (portalChunk.getBlockType(x,y,z) == Material.NETHER_PORTAL) {
                            y--;
                        }
                        //System.out.print("DEBUG: Portal teleport loc = " + (16 * portalChunk.getX() + x) + "," + (y) + "," + (16 * portalChunk.getZ() + z));
                        // Now check if this is a safe location
                        if (checkBlock(portalChunk,x,y,z, worldHeight)) {
                            // Yes, so use this instead of the highest location
                            //System.out.print("DEBUG: Portal is safe");
                            safeSpotFound = true;
                            safeSpotInChunk = new Vector(x,y,z);
                            safeChunk = portalChunk;
                        } else {
                            // Not safe, so ignore this portal
                            portalPart = null;
                        }
                    }
                    if (portalPart == null && spawnChunkFinal != null) {
                        // If no portal or it's not safe, try the spawn point of the schematic
                        x = spawnLocFinal.getBlockX() - spawnChunkFinal.getX() * 16;
                        y = spawnLocFinal.getBlockY();
                        z = spawnLocFinal.getBlockZ() - spawnChunkFinal.getZ() * 16;
                        if (checkBlock(spawnChunkFinal, x, y, z, worldHeight)) {
                         // Return to main thread and teleport the player
                            plugin.getServer().getScheduler().runTask(plugin, new Runnable() {

                                @Override
                                public void run() {
                                    if (setHome) {
                                        plugin.getPlayers().setHomeLocation(entity.getUniqueId(), spawnLocFinal, homeNumber);
                                    }
                                    Vector velocity = entity.getVelocity();
                                    entity.teleport(spawnLocFinal);
                                    entity.setVelocity(velocity);
                                }});
                            return;  
                        }
                    }
                    //System.out.print("Seconds = " + ((System.nanoTime() - time) * 0.000000001));
                    if (safeChunk != null && safeSpotFound) {
                        //final Vector spot = new Vector((16 *currentChunk.getX()) + x + 0.5D, y +1, (16 * currentChunk.getZ()) + z + 0.5D)
                        final Vector spot = new Vector((16 *safeChunk.getX()) + 0.5D, 1, (16 * safeChunk.getZ()) + 0.5D).add(safeSpotInChunk);
                        // Return to main thread and teleport the player
                        plugin.getServer().getScheduler().runTask(plugin, new Runnable() {

                            @Override
                            public void run() {
                                Location destination = spot.toLocation(islandLoc.getWorld());
                                //plugin.getLogger().info("DEBUG: safe spot found = " + destination);
                                if (setHome && entity instanceof Player) {
                                    plugin.getPlayers().setHomeLocation(entity.getUniqueId(), destination, homeNumber);
                                }
                                Vector velocity = entity.getVelocity();
                                entity.teleport(destination);
                                entity.setVelocity(velocity);
                            }});
                    } else {
                        // We did not find a spot
                        plugin.getServer().getScheduler().runTask(plugin, new Runnable() {

                            @Override
                            public void run() {
                                //plugin.getLogger().info("DEBUG: safe spot not found");
                                if (entity instanceof Player) {
                                    if (!failureMessage.isEmpty()) {
                                        Util.sendMessage(((Player)entity), failureMessage);
                                    } else {
                                        Util.sendMessage(((Player)entity), ChatColor.RED + plugin.myLocale(((Player)entity).getUniqueId()).warpserrorNotSafe);
                                    }
                                }
                            }});
                    }
                }

                /**
                 * Returns true if the location is a safe one.
                 * @param chunk
                 * @param x
                 * @param y
                 * @param z
                 * @param worldHeight 
                 * @return
                 */
                @SuppressWarnings("deprecation")
                private boolean checkBlock(ChunkSnapshot chunk, int x, int y, int z, int worldHeight) {
                    //plugin.getLogger().info("DEBUG: chunk = " + chunk.getX() + "," + chunk.getZ());
                    //plugin.getLogger().info("DEBUG: x,y,z = " + x + "," + y +"," + z);
                    Material type = chunk.getBlockType(x, y, z);
                    //plugin.getLogger().info("DEBUG:block type = " + type);
                    if (type != Material.AIR) { // AIR
                        Material space1 = chunk.getBlockType(x, Math.min(y + 1, worldHeight), z);
                        Material space2 = chunk.getBlockType(x, Math.min(y + 2, worldHeight), z);
                        if ((space1 == Material.AIR && space2 == Material.AIR) || (space1 == Material.NETHER_PORTAL || space2 == Material.NETHER_PORTAL)) {
                            // Now there is a chance that this is a safe spot
                            // Check for safe ground
                            Material mat = Material.matchMaterial(type.toString());
                            if (!mat.toString().contains("FENCE") 
                                    && !mat.toString().contains("DOOR")
                                    && !mat.toString().contains("GATE")
                                    && !mat.toString().contains("PLATE")) {
                                switch (mat) {
                                // Unsafe
                                case ANVIL:
                                case BARRIER:
                                case OAK_BOAT:
                                case ACACIA_BOAT:
                                case BIRCH_BOAT:
                                case DARK_OAK_BOAT:
                                case JUNGLE_BOAT:
                                case SPRUCE_BOAT:
                                case CACTUS:
                                case SUNFLOWER:
                                case END_PORTAL:
                                case FIRE:
                                case FLOWER_POT:
                                case LADDER:
                                case LEVER:
                                case TALL_GRASS:
                                case PISTON_HEAD:
                                case MOVING_PISTON:
                                case NETHER_PORTAL:
                                case SIGN:
               //                 case HEAD:
                //                case BANNER:
                                case LAVA:
                                case WATER:
                                case STONE_BUTTON:
                                case TORCH:
                                case TRIPWIRE:
                                case COBWEB:
                                case OAK_BUTTON:
                                case ACACIA_BUTTON:
                                case BIRCH_BUTTON:
                                case DARK_OAK_BUTTON:
                                case JUNGLE_BUTTON:
                                case SPRUCE_BUTTON:
                                    //System.out.println("Block is dangerous " + mat.toString());
                                    break;
                                default:
                                    // Safe
                                    //System.out.println("Block is safe " + mat.toString());
                                    return true;
                                }
                            }
                        }
                    }
                    return false;
                }});
        }
    }

    /**
     * Checks what version the server is running and picks the appropriate NMS handler, or fallback
     * @return NMSAbstraction class
     * @throws ClassNotFoundException
     * @throws IllegalArgumentException
     * @throws SecurityException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    /*
    private NMSAbstraction checkVersion() throws ClassNotFoundException, IllegalArgumentException,
    SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException,
    NoSuchMethodException {
	String serverPackageName = plugin.getServer().getClass().getPackage().getName();
	String pluginPackageName = plugin.getClass().getPackage().getName();
	String version = serverPackageName.substring(serverPackageName.lastIndexOf('.') + 1);
	Class<?> clazz;
	try {
	    //plugin.getLogger().info("DEBUG: Trying " + pluginPackageName + ".nms." + version + ".NMSHandler");
	    clazz = Class.forName(pluginPackageName + ".nms." + version + ".NMSHandler");
	} catch (Exception e) {
	    plugin.getLogger().info("No NMS Handler found, falling back to Bukkit API.");
	    clazz = Class.forName(pluginPackageName + ".nms.fallback.NMSHandler");
	}
	//plugin.getLogger().info("DEBUG: " + serverPackageName);
	//plugin.getLogger().info("DEBUG: " + pluginPackageName);
	// Check if we have a NMSAbstraction implementing class at that location.
	if (NMSAbstraction.class.isAssignableFrom(clazz)) {
	    return (NMSAbstraction) clazz.getConstructor().newInstance();
	} else {
	    throw new IllegalStateException("Class " + clazz.getName() + " does not implement NMSAbstraction");
	}
    }*/
}