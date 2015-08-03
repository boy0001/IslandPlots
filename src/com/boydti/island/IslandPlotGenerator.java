package com.boydti.island;

import java.util.List;

import org.bukkit.World;

import com.intellectualcrafters.plot.object.PlotManager;
import com.intellectualcrafters.plot.object.PlotWorld;
import com.intellectualcrafters.plot.object.PseudoRandom;
import com.intellectualcrafters.plot.object.RegionWrapper;
import com.intellectualcrafters.plot.object.SetupObject;
import com.plotsquared.bukkit.generator.BukkitPlotGenerator;
import com.plotsquared.bukkit.generator.BukkitPlotPopulator;

public class IslandPlotGenerator extends BukkitPlotGenerator {

    public IslandPlotGenerator(String world) {
        super(world);
    }

    @Override
    public void generateChunk(World arg0, RegionWrapper arg1, PseudoRandom arg2, int arg3, int arg4, BiomeGrid arg5) {
    }

    @Override
    public PlotWorld getNewPlotWorld(String name) {
        return new IslandPlotWorld(name);
    }
    
    @Override
    public void processSetup(SetupObject object) {
        object.setupManager = new IslandSetupManager();
        object.type = 1;
        object.terrain = 3;
    }

    private IslandPlotManager man = new IslandPlotManager();
    
    @Override
    public PlotManager getPlotManager() {
        return man;
    }

    @Override
    public List<BukkitPlotPopulator> getPopulators(String arg0) {
        return null;
    }

    @Override
    public void init(PlotWorld arg0) {
    }
    
}
