package com.boydti.island;

import com.intellectualcrafters.plot.config.Configuration;
import com.intellectualcrafters.plot.config.ConfigurationNode;
import com.intellectualcrafters.plot.generator.ClassicPlotWorld;

public class IslandPlotWorld extends ClassicPlotWorld {
    public IslandPlotWorld(String worldname) {
        super(worldname);
    } 

    @Override
    public ConfigurationNode[] getSettingNodes() {
        return new ConfigurationNode[] {
            new ConfigurationNode("island-size", 4, "Island Size", Configuration.INTEGER, true),
            new ConfigurationNode("ocean-size", 1, "Ocean Size", Configuration.INTEGER, true)
        };
    }
}
