package ce.controller;

import javax.ejb.Stateless;
import javax.faces.context.FacesContext;

@Stateless
public class FacesContextBean {
	public FacesContext getFC(){
		return FacesContext.getCurrentInstance();
	}
}


