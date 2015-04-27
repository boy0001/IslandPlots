package com.boydti.island;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.hoqhuuep.islandcraft.api.IslandCraft;
import com.github.hoqhuuep.islandcraft.bukkit.IslandCraftPlugin;

public class Main extends JavaPlugin {
    
    public static IslandCraft islandCraft;
    public static FileConfiguration islandConfig;
    public static IslandCraftPlugin islandCraftPlugin;
    
    @Override
    public void onEnable() {
        Main.islandCraftPlugin = getPlugin(IslandCraftPlugin.class);
        Main.islandCraft = islandCraftPlugin.getIslandCraft();
        Main.islandConfig = islandCraftPlugin.getConfig();
    }
    
    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        return new IslandPlotGenerator();
    }
}
