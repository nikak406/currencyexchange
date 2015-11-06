package ce.view;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class Locations {

    private String[] locations;

	public String[] getLocations(){
		return locations;
	}

    public Locations(){
        locations = new String[3];
        locations[0] = "Gryshka, 3A";
        locations[1] = "Grygorenka, 22";
        locations[2] = "Kharkovskoe shosse, 175";
    }
}
