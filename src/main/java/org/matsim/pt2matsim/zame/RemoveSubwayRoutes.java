/*
package org.matsim.pt2matsim.zame;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.Scenario;
import org.matsim.api.core.v01.network.Link;
import org.matsim.api.core.v01.network.Network;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.network.NetworkUtils;
import org.matsim.core.scenario.ScenarioUtils;
import org.matsim.pt.transitSchedule.TransitScheduleFactoryImpl;
import org.matsim.pt.transitSchedule.api.*;
import org.matsim.pt2matsim.tools.ScheduleTools;

import java.util.*;

public class RemoveSubwayRoutes {

    public static void main(String[] args) {
        String scheduleFile = "example/MMR/output_networks_5/MMR_MVV_Bus_Schedule_MAPPED.xml"; // Replace with your file path

        // Create a MATSim config and scenario
        Config config = ConfigUtils.createConfig();
        Scenario scenario = ScenarioUtils.createScenario(config);

        // Read the transit schedule into the scenario
        new TransitScheduleReader(scenario).readFile(scheduleFile);

        // Get the transit schedule from the scenario
        TransitSchedule schedule = scenario.getTransitSchedule();

        // Remove subway routes
        removeSubwayRoutes(schedule);

        // Write the modified schedule to a file
        String outputFile = "example/MMR/output_networks_5/MMR_MVV_Bus_Schedule_MAPPED_subwayRemvd.xml"; // Replace with your desired output file path
        new TransitScheduleWriter(schedule).writeFile(outputFile);
    }


    private static void removeSubwayRoutes(TransitSchedule schedule) {
        List<TransitRoute> routesToRemove = new ArrayList<>();
        for (TransitLine line : schedule.getTransitLines().values()) {
            for (TransitRoute route : line.getRoutes().values()) {
                if ("subway".equals(route.getTransportMode())) {
                    routesToRemove.add(route);
                }
            }
        }

        // Now remove the routes
        for (TransitRoute route : routesToRemove) {
            schedule.getTransitLines().get(route.getLine().getId()).removeRoute(route);
        }
    }
}*/
