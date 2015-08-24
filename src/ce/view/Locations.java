package ce.view;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@Named
@RequestScoped
public class Locations {
	//TODO NPE in constructor
    private String[] locations;

	public String[] getLocations(){
		return locations;
	}

    public Locations(){
        init();
    }

    public void init(){
        try {
            File xmlLocations = Paths.get("web/resources/locations.xml").toFile();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlLocations);
            NodeList locationsNode = document.getElementsByTagName("locations");
            NodeList locations = locationsNode.item(0).getChildNodes();
            int length = locations.getLength();
            this.locations = new String[length];
            for (int i=0; i < length; i++){
                Element location = (Element) locations.item(i);
                this.locations[i] = location.getTextContent();
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            this.locations[0] = e.getLocalizedMessage();
        }
    }
}
