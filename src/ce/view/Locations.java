package ce.view;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class Locations {
	private String[] locations = {"Gryshka, 3A", "Grygorenko, 40"};

	public String[] getLocations(){
		return locations;
	}
}
