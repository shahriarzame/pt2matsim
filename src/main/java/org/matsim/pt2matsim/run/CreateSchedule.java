package org.matsim.pt2matsim.run;

import org.matsim.core.utils.geometry.transformations.TransformationFactory;
import org.matsim.pt2matsim.gtfs.GtfsConverter;
import org.matsim.pt2matsim.run.Gtfs2TransitSchedule;

public class CreateSchedule {

    /*public static void main(String[] args) {
        Gtfs2TransitSchedule.run("example/berlin/GTFS.zip", GtfsConverter.DAY_WITH_MOST_SERVICES, TransformationFactory.DHDN_GK4, "example/berlin/Berlin_Schedule.xml", "example/berlin/Berlin_Vehicles.xml");
    }*/


    public static void main(String[] args) {
        Gtfs2TransitSchedule.run("example/MMR/gtfs_mvv_2019.zip", GtfsConverter.DAY_WITH_MOST_SERVICES, TransformationFactory.DHDN_GK4, "C:\\Education\\matsim_ws22_23\\matsim_projects\\pt2matsim\\example\\MMR\\output_networks_6\\MMR_mvv_bus_2019_schedule.xml", "C:\\Education\\matsim_ws22_23\\matsim_projects\\pt2matsim\\example\\MMR\\output_networks_6\\MMR_mvv_bus_2019_Vehicles.xml");
    }


/*"C:\Education\TUM\Semester 7\Applied Transport Modeling with MATSim\GTFS\cityList_GTFS\cityList_GTFS\Aichach\outputs"*/

    /*public static void main(String[] args) {
        Gtfs2TransitSchedule.run("C:/Education/TUM/Semester 7/Applied Transport Modeling with MATSim/GTFS/Merged GTFS data _ MMR/output/", GtfsConverter.DAY_WITH_MOST_SERVICES, TransformationFactory.DHDN_GK4, "example/MMR/MMRSchedule.xml", "example/MMR/MMRVehicles.xml");
    }*/

    /*Exception in thread "main" java.lang.NumberFormatException: For input string: "stop_lon"
	at java.base/jdk.internal.math.FloatingDecimal.readJavaFormatString(FloatingDecimal.java:2054)
	at java.base/jdk.internal.math.FloatingDecimal.parseDouble(FloatingDecimal.java:110)
	at java.base/java.lang.Double.parseDouble(Double.java:792)
	at org.matsim.pt2matsim.gtfs.GtfsFeedImpl.loadStops(GtfsFeedImpl.java:207)
	at org.matsim.pt2matsim.gtfs.GtfsFeedImpl.loadFiles(GtfsFeedImpl.java:142)
	at org.matsim.pt2matsim.gtfs.GtfsFeedImpl.<init>(GtfsFeedImpl.java:88)
	at org.matsim.pt2matsim.run.Gtfs2TransitSchedule.run(Gtfs2TransitSchedule.java:106)
	at org.matsim.pt2matsim.run.CreateSchedule.main(CreateSchedule.java:10)*/




}
