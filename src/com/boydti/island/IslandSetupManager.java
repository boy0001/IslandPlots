package com.boydti.island;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import com.intellectualcrafters.plot.PS;
import com.intellectualcrafters.plot.config.ConfigurationNode;
import com.intellectualcrafters.plot.object.SetupObject;
import com.plotsquared.bukkit.util.BukkitSetupUtils;

public class IslandSetupManager extends BukkitSetupUtils {
    
    @Override
    public String setupWorld(SetupObject object) {
        
        String world = object.world;
        String path = "worlds." + world;
        
        PS.get().config.set(path + "." + "generator.type", object.type);
        PS.get().config.set(path + "." + "generator.terrain", object.terrain);
        PS.get().config.set(path + "." + "generator.plugin", "IslandPlots");
        
        // FIXME parse object
        
        // FIXEM save settings
        
        ConfigurationNode[] settings = object.step;
        int plot_size = (int) settings[0].getValue() * 32;
        int road_size = (int) settings[1].getValue() * 32;
        
        PS.get().config.set(path + "." + "plot.height", 0);
        PS.get().config.set(path + "." + "plot.size", plot_size);
        PS.get().config.set(path + "." + "plot.filling", Arrays.asList("0"));
        PS.get().config.set(path + "." + "plot.floor", Arrays.asList("0"));
        PS.get().config.set(path + "." + "wall.block", "0");
        PS.get().config.set(path + "." + "wall.block_claimed", "0");
        PS.get().config.set(path + "." + "wall.filling", "0");
        PS.get().config.set(path + "." + "wall.height", 0);
        PS.get().config.set(path + "." + "road.width", road_size);
        PS.get().config.set(path + "." + "road.height", 0);
        PS.get().config.set(path + "." + "road.block", "0");
        PS.get().config.set(path + "." + "plot.bedrock", false);
        
        try {
            PS.get().config.save(PS.get().configFile);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        Main.islandConfig.set(path + "." + "ocean" , "com.github.hoqhuuep.islandcraft.core.ConstantBiomeDistribution DEEP_OCEAN");
        Main.islandConfig.set(path + "." + "island-distribution" , "com.boydti.island.PlotDistribution " + plot_size + " " + road_size);
        
        String[] gen_types = {
            "com.github.hoqhuuep.islandcraft.core.IslandGeneratorAlpha BIRCH_FOREST BIRCH_FOREST_M BIRCH_FOREST_HILLS BIRCH_FOREST_HILLS_M ~ ~ OCEAN BEACH RIVER",
            "com.github.hoqhuuep.islandcraft.core.IslandGeneratorAlpha COLD_TAIGA COLD_TAIGA_M COLD_TAIGA_HILLS ~ ~ ~ OCEAN COLD_BEACH FORZEN_RIVER",
            "com.github.hoqhuuep.islandcraft.core.IslandGeneratorAlpha DESERT DESERT_M DESERT_HILLS ~ ~ ~ OCEAN BEACH RIVER",
            "com.github.hoqhuuep.islandcraft.core.IslandGeneratorAlpha EXTREME_HILLS EXTREME_HILLS_M EXTREME_HILLS_PLUS EXTREME_HILLS_PLUS_M EXTREME_HILLS_EDGE ~ OCEAN STONE_BEACH RIVER",
            "com.github.hoqhuuep.islandcraft.core.IslandGeneratorAlpha FOREST ~ FOREST_HILLS ~ FLOWER_FOREST ~ OCEAN BEACH RIVER",
            "com.github.hoqhuuep.islandcraft.core.IslandGeneratorAlpha ICE_PLAINS ~ ICE_MOUNTAINS ~ ICE_PLAINS_SPIKES ~ OCEAN FROZEN_OCEAN FORZEN_RIVER",
            "com.github.hoqhuuep.islandcraft.core.IslandGeneratorAlpha JUNGLE JUNGLE_M JUNGLE_HILLS ~ JUNGLE_EDGE JUNGLE_EDGE_M OCEAN BEACH RIVER",
            "com.github.hoqhuuep.islandcraft.core.IslandGeneratorAlpha MEGA_TAIGA MEGA_SPRUCE_TAIGA MEGA_TAIGA_HILLS MEGA_SPRUCE_TAIGA_HILLS ~ ~ OCEAN BEACH RIVER",
            "com.github.hoqhuuep.islandcraft.core.IslandGeneratorAlpha MESA MESA_BRYCE MESA_PLATEAU MESA_PLATEAU_M MESA_PLATEAU_F MESA_PLATEAU_F_M OCEAN MESA RIVER",
            "com.github.hoqhuuep.islandcraft.core.IslandGeneratorAlpha MUSHROOM_ISLAND ~ ~ ~ ~ ~ OCEAN MUSHROOM_ISLAND_SHORE RIVER",
            "com.github.hoqhuuep.islandcraft.core.IslandGeneratorAlpha PLAINS ~ SUNFLOWER_PLAINS ~ ~ ~ OCEAN BEACH RIVER",
            "com.github.hoqhuuep.islandcraft.core.IslandGeneratorAlpha ROOFED_FOREST ROOFED_FOREST_M ~ ~ ~ ~ OCEAN BEACH RIVER",
            "com.github.hoqhuuep.islandcraft.core.IslandGeneratorAlpha SAVANNA SAVANNA_M SAVANNA_PLATEAU SAVANNA_PLATEAU_M ~ ~ OCEAN BEACH RIVER",
            "com.github.hoqhuuep.islandcraft.core.IslandGeneratorAlpha SWAMPLAND SWAMPLAND_M ~ ~ ~ ~ OCEAN BEACH RIVER",
            "com.github.hoqhuuep.islandcraft.core.IslandGeneratorAlpha TAIGA TAIGA_M TAIGA_HILLS ~ ~ ~ OCEAN BEACH RIVER"
        };
        
        Main.islandConfig.set(path + "." + "island-generators" , gen_types);
        
        PS.log("&aSaving IslandPlots configurtion!!!!!!!!!");
        
        try {
            Main.islandConfig.save(new File(Main.islandCraftPlugin.getDataFolder() + File.separator + "config.yml"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        Main.islandCraftPlugin.reloadConfig();
        
        if ((Bukkit.getPluginManager().getPlugin("Multiverse-Core") != null) && Bukkit.getPluginManager().getPlugin("Multiverse-Core").isEnabled()) {
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "mv create " + world + " normal");
        } else {
            if ((Bukkit.getPluginManager().getPlugin("MultiWorld") != null) && Bukkit.getPluginManager().getPlugin("MultiWorld").isEnabled()) {
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "mw create " + world);
            } else {
                Bukkit.createWorld(new WorldCreator(object.world).environment(World.Environment.NORMAL));
            }
        }
        
        return object.world;
    }
}
