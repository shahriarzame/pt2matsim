package org.matsim.pt2matsim.zame;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.network.Link;
import org.matsim.api.core.v01.network.Network;
import org.matsim.core.network.NetworkUtils;
import org.matsim.pt.transitSchedule.api.TransitLine;
import org.matsim.pt.transitSchedule.api.TransitRoute;
import org.matsim.pt.transitSchedule.api.TransitSchedule;
import org.matsim.pt2matsim.tools.ScheduleTools;

import java.util.*;
import java.util.stream.Collectors;

import static org.matsim.pt2matsim.tools.NetworkTools.writeNetwork;

public class LoopLinkIdentifier_ScheduleCleaner {


    private static final String inputNetworkFile = "example/MMR/output_networks_3/MMR_MultiModalNetwork_CLEANED_MAPPED_3.xml.gz";
    private static final String inputScheduleFile = "example/MMR/output_networks_3/MMR_ScheduleMapped_3.xml";

    public static void main(String[] args) {

        // Read the network
        Network network = NetworkUtils.readNetwork(inputNetworkFile);

        // Read the transit schedule
        TransitSchedule schedule = ScheduleTools.readTransitSchedule(inputScheduleFile);

        // Read Loop Links
        List<Id<Link>> loopLinkIds = findLoopLinks(network);

        // In Schedule find the "route" containing the loop links, and associated "transitRoute"
        // Print the count and list of "transitRoute" for each loop link
        // Remove the "transitRoute" from the schedule
        removeTransitRoutesWithLoopLinks(schedule, loopLinkIds);


        // Remove the loop links from the network
        removeLoopLinks(network, loopLinkIds);

        // Set allowed modes
        setAllowedModes(network);


        // Write the cleaned network and schedule to files
        writeNetwork(network, "example/MMR/output_networks_3/looplinks_cleanedNetwork_2.xml.gz");
        ScheduleTools.writeTransitSchedule(schedule, "example/MMR/output_networks_3/looplinks_cleanedSchedule_2.xml");

    }


    public static List<Id<Link>> findLoopLinks(Network network) {
        List<Id<Link>> loopLinkIds = new ArrayList<>();
        for (Link link : network.getLinks().values()) {
            if (link.getFromNode().getId().equals(link.getToNode().getId())) {
                loopLinkIds.add(link.getId());
            }
        }
        return loopLinkIds;
    }


    public static void removeTransitRoutesWithLoopLinks(TransitSchedule schedule, List<Id<Link>> loopLinkIds) {

        // Calculate the total number of routes before removal
        int totalRoutesBefore = schedule.getTransitLines().values().stream()
                .mapToInt(line -> line.getRoutes().size())
                .sum();


        Set<Id<Link>> loopLinkIdsSet = new HashSet<>(loopLinkIds);
        Map<Id<Link>, Set<Id<TransitRoute>>> loopLinkToRoutesMap = new HashMap<>();

        for (Id<Link> loopLinkId : loopLinkIdsSet) {
            loopLinkToRoutesMap.put(loopLinkId, new HashSet<>());
        }

        Map<TransitLine, Set<TransitRoute>> routesToRemove = new HashMap<>();

        for (TransitLine transitLine : schedule.getTransitLines().values()) {
            for (TransitRoute transitRoute : transitLine.getRoutes().values()) {
                List<Id<Link>> routeLinkIds = transitRoute.getRoute().getLinkIds();

                for (Id<Link> loopLinkId : loopLinkIdsSet) {
                    if (routeLinkIds.contains(loopLinkId)) {
                        loopLinkToRoutesMap.get(loopLinkId).add(transitRoute.getId());

                        routesToRemove.computeIfAbsent(transitLine, k -> new HashSet<>()).add(transitRoute);
                        break;
                    }
                }
            }
        }

        // Now remove the routes outside of the original loop
        for (Map.Entry<TransitLine, Set<TransitRoute>> entry : routesToRemove.entrySet()) {
            TransitLine line = entry.getKey();
            for (TransitRoute route : entry.getValue()) {
                line.removeRoute(route);
            }
        }

        // ... Rest of your method


        // Print the matched transitRoutes and counts for each loop link
        for (Map.Entry<Id<Link>, Set<Id<TransitRoute>>> entry : loopLinkToRoutesMap.entrySet()) {
            Id<Link> loopLinkId = entry.getKey();
            Set<Id<TransitRoute>> routes = entry.getValue();

            System.out.println("Loop Link: " + loopLinkId + " is used by " + routes.size() + " transitRoutes.");
            for (Id<TransitRoute> routeId : routes) {
                System.out.println(" - TransitRoute ID: " + routeId);
            }
        }


        // Calculate the total number of routes after removal
        int totalRoutesAfter = schedule.getTransitLines().values().stream()
                .mapToInt(line -> line.getRoutes().size())
                .sum();

        // Calculate and print the percentage of routes removed
        int totalRoutesRemoved = totalRoutesBefore - totalRoutesAfter;
        double percentageRemoved = ((double) totalRoutesRemoved / totalRoutesBefore) * 100;
        System.out.println("Percentage of transit routes removed: " + percentageRemoved + "%");

    }


    public static void removeLoopLinks(Network network, List<Id<Link>> loopLinkIds) {
        for (Id<Link> linkId : loopLinkIds) {
            // It's a good practice to check if the network actually contains
            // the link before attempting to remove it.
            if (network.getLinks().containsKey(linkId)) {
                network.removeLink(linkId);
                System.out.println("Removed loop link: " + linkId.toString());
            }
        }
    }

    public static void setAllowedModes(Network network) {
        // Finding all the unique allowed modes in the network

        for (Link link : network.getLinks().values()) {
            Set<String> allowedModes = new HashSet<>(link.getAllowedModes());
            allowedModes.add("bus");

            if (allowedModes.contains("car")) {
                allowedModes.add("car_passenger");
                link.setAllowedModes(allowedModes);
            }
            if (allowedModes.contains("pt")) {
                link.setAllowedModes(allowedModes);
            }
            if (allowedModes.contains("rail")) {
                allowedModes.add("train");
                link.setAllowedModes(allowedModes);
            }
        }
    }


}
