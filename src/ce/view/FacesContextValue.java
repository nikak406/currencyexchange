package ce.view;

import javax.ejb.Stateless;

@Stateless
public class FacesContextValue {
	public javax.faces.context.FacesContext getInstance(){
		return javax.faces.context.FacesContext.getCurrentInstance();
	}
}


