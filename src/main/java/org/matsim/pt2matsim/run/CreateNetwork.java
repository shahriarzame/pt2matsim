package org.matsim.pt2matsim.run;

import org.matsim.core.utils.geometry.transformations.TransformationFactory;
import org.matsim.pt2matsim.run.Osm2MultimodalNetwork;

public class CreateNetwork {


    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().maxMemory());
        Osm2MultimodalNetwork.run("C:\\Education\\matsim_ws22_23\\matsim_projects\\mat-sim-ws-2324-zame\\scenarios\\NetworkMMR\\MMR.osm", "C:\\Education\\matsim_ws22_23\\matsim_projects\\pt2matsim\\example\\MMR\\output_networks_7\\MMR_Highways_network_FINAL.xml", TransformationFactory.DHDN_GK4);
    }
}

