package org.matsim.pt2matsim.run;

import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.config.ConfigWriter;
import org.matsim.core.utils.collections.CollectionUtils;
import org.matsim.pt2matsim.config.PublicTransitMappingConfigGroup;
import org.matsim.pt2matsim.run.CreateDefaultPTMapperConfig;
import org.matsim.pt2matsim.run.PublicTransitMapper;

public class MapSchedule2Network {

    public static void main(String[] args) {
        // Create a mapping config:
        CreateDefaultPTMapperConfig.main(new String[]{ "C:\\Education\\TUM\\Semester 7\\Applied Transport Modeling with MATSim\\MATSim\\pt2matsim\\example\\MMR\\config.xml"});
        // Open the mapping config and set the parameters to the required values
        // (usually done manually by opening the config with a simple editor)
        Config config = ConfigUtils.loadConfig(
                "C:\\Education\\TUM\\Semester 7\\Applied Transport Modeling with MATSim\\MATSim\\pt2matsim\\example\\MMR\\config.xml",
                PublicTransitMappingConfigGroup.createDefaultConfig());
        PublicTransitMappingConfigGroup ptmConfig = ConfigUtils.addOrGetModule(config, PublicTransitMappingConfigGroup.class);

        ptmConfig.setInputNetworkFile("C:\\Education\\TUM\\Semester 7\\Applied Transport Modeling with MATSim\\MATSim\\pt2matsim\\example\\MMR\\MMR_MultiModalNetwork.xml");
        ptmConfig.setOutputNetworkFile("C:\\Education\\TUM\\Semester 7\\Applied Transport Modeling with MATSim\\MATSim\\pt2matsim\\example\\MMR\\MMR_MultiModal_Mapped.xml.gz");
        ptmConfig.setOutputStreetNetworkFile("C:\\Education\\TUM\\Semester 7\\Applied Transport Modeling with MATSim\\MATSim\\pt2matsim\\example\\MMR\\MMR_streetnetwork.xml.gz"); // without PT
        ptmConfig.setInputScheduleFile("C:\\Education\\TUM\\Semester 7\\Applied Transport Modeling with MATSim\\MATSim\\pt2matsim\\example\\MMR\\MMR_Schedule.xml");
        ptmConfig.setOutputScheduleFile("C:\\Education\\TUM\\Semester 7\\Applied Transport Modeling with MATSim\\MATSim\\pt2matsim\\example\\MMR\\MMR_ScheduleMapped.xml");
        ptmConfig.setScheduleFreespeedModes(CollectionUtils.stringToSet("rail, light_rail"));
        // Save the mapping config
        // (usually done manually)
        new ConfigWriter(config).write("C:\\Education\\TUM\\Semester 7\\Applied Transport Modeling with MATSim\\MATSim\\pt2matsim\\example\\MMR\\config.xml");
        PublicTransitMapper.run("C:\\Education\\TUM\\Semester 7\\Applied Transport Modeling with MATSim\\MATSim\\pt2matsim\\example\\MMR\\config.xml");
    }
}
