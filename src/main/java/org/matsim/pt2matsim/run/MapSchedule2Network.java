package org.matsim.pt2matsim.run;

import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.config.ConfigWriter;
import org.matsim.core.network.NetworkUtils;
import org.matsim.core.utils.collections.CollectionUtils;
import org.matsim.pt2matsim.config.PublicTransitMappingConfigGroup;
import org.matsim.pt2matsim.run.CreateDefaultPTMapperConfig;
import org.matsim.pt2matsim.run.PublicTransitMapper;

public class MapSchedule2Network {

    public static void main(String[] args) {
        // Create a mapping config:
        CreateDefaultPTMapperConfig.main(new String[]{ "example/MMR/output_networks_7/config_7.xml"});
        // Open the mapping config and set the parameters to the required values
        // (usually done manually by opening the config with a simple editor)
        Config config = ConfigUtils.loadConfig(
                "example/MMR/output_networks_7/config_7.xml",
                PublicTransitMappingConfigGroup.createDefaultConfig());
        PublicTransitMappingConfigGroup ptmConfig = ConfigUtils.addOrGetModule(config, PublicTransitMappingConfigGroup.class);

        ptmConfig.setInputNetworkFile("example/MMR/output_networks_7/MMR_Highways_network_FINAL.xml");
        ptmConfig.setOutputNetworkFile("example/MMR/output_networks_7/MMR_Highways_network_MAPPED_FINAL.xml");
        ptmConfig.setOutputStreetNetworkFile("example/MMR/output_networks_7/MMR_Street_network_MAPPED.xml"); // without PT

        ptmConfig.setInputScheduleFile("example/MMR/output_networks_7/MMR_mvv_bus_2019_schedule.xml");
        ptmConfig.setOutputScheduleFile("example/MMR/output_networks_7/MMR_mvv_bus_2019_schedule_MAPPED_FINAL.xml");

        ptmConfig.setScheduleFreespeedModes(CollectionUtils.stringToSet("rail, light_rail"));
        // Save the mapping config
        // (usually done manually)
        new ConfigWriter(config).write("example/MMR/output_networks_7/config_7.xml");
        PublicTransitMapper.run("example/MMR/output_networks_7/config_7.xml");


    }
}
