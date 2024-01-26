package org.matsim.pt2matsim.run;

import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.config.ConfigWriter;
import org.matsim.core.utils.collections.CollectionUtils;
import org.matsim.pt2matsim.config.PublicTransitMappingConfigGroup;

public class MapSchedule2Network_2 {

    public static void main(String[] args) {
        // Create a mapping config:
        CreateDefaultPTMapperConfig.main(new String[]{ "example/MMR/output_networks_3/config_3.xml"});
        // Open the mapping config and set the parameters to the required values
        // (usually done manually by opening the config with a simple editor)
        Config config = ConfigUtils.loadConfig(
                "example/MMR/output_networks_3/config_3.xml",
                PublicTransitMappingConfigGroup.createDefaultConfig());
        PublicTransitMappingConfigGroup ptmConfig = ConfigUtils.addOrGetModule(config, PublicTransitMappingConfigGroup.class);

        ptmConfig.setInputNetworkFile("example/MMR/output_networks_3/MMR_MultiModal_CLEANED_REMOVED_Unmappped.xml.gz");
        ptmConfig.setOutputNetworkFile("example/MMR/output_networks_3/MMR_MultiModalNetwork_CLEANED_MAPPED_3.xml.gz");
        ptmConfig.setOutputStreetNetworkFile("example/MMR/output_networks_3/MMR_StreetNetwork_3.xml.gz"); // without PT
        ptmConfig.setInputScheduleFile("example/MMR/output_networks_2/MMR_Schedule_2.xml");
        ptmConfig.setOutputScheduleFile("example/MMR/output_networks_3/MMR_ScheduleMapped_3.xml");
        ptmConfig.setScheduleFreespeedModes(CollectionUtils.stringToSet("rail, light_rail"));
        // Save the mapping config
        // (usually done manually)
        new ConfigWriter(config).write("example/MMR/output_networks_3/config_3.xml");
        PublicTransitMapper.run("example/MMR/output_networks_3/config_3.xml");


    }
}
