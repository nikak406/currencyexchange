package ce.controller;

import javax.ejb.Stateless;
import javax.faces.context.FacesContext;

@Stateless
public class FacesContextValue {
	public FacesContext getInstance(){
		return FacesContext.getCurrentInstance();
	}
}


