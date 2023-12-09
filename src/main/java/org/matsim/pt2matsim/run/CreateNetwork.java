package org.matsim.pt2matsim.run;

import org.matsim.core.utils.geometry.transformations.TransformationFactory;
import org.matsim.pt2matsim.run.Osm2MultimodalNetwork;

public class CreateNetwork {


    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().maxMemory());
        Osm2MultimodalNetwork.run("C:\\Education\\TUM\\Semester 7\\Applied Transport Modeling with MATSim\\MATSim\\mat-sim-ws-2324-zame\\scenarios\\NetworkMMR\\MMR.osm", "C:\\Education\\TUM\\Semester 7\\Applied Transport Modeling with MATSim\\MATSim\\pt2matsim\\example\\MMR\\MMR_MultiModalNetwork.xml", TransformationFactory.DHDN_GK4);
    }
}

