package org.matsim.pt2matsim.zame;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;

public class TransitRouteRemover_NoAPI {

    public static void main(String[] args) {
        try {
            // Load and parse the XML document
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse("example/MMR/output_networks_5/MMR_MVV_Bus_Schedule_MAPPED.xml");

            // Normalize the XML structure
            doc.getDocumentElement().normalize();

            // Get all transitRoute elements
            NodeList transitRouteList = doc.getElementsByTagName("transitRoute");
            int removedCount = 0;

            for (int i = 0; i < transitRouteList.getLength(); i++) {
                Node transitRouteNode = transitRouteList.item(i);

                if (transitRouteNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element transitRouteElement = (Element) transitRouteNode;

                    // Check if the transportMode is "subway"
                    String transportMode = transitRouteElement.getElementsByTagName("transportMode").item(0).getTextContent();
                    if ("subway".equals(transportMode)) {
                        // Remove the transitRoute element
                        transitRouteElement.getParentNode().removeChild(transitRouteElement);
                        removedCount++;
                        i--; // Adjust the index after removal
                    }
                }
            }

            System.out.println("Number of transit routes removed: " + removedCount);

            // Save the modified XML document if needed
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("example/MMR/output_networks_5/MMR_MVV_Bus_Schedule_MAPPED_SubwayRemvd.xml"));
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
