package ce.controller;

import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@Stateless
public class SessionService {

    private HttpSession getSession(){
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    }

    public void attachToSession(String attributeName, Object object){
        HttpSession httpSession = getSession();
        if (httpSession == null) return;
        httpSession.setAttribute(attributeName, object);
    }

    public Object readFromSession(String attributeName) {
        HttpSession httpSession = getSession();
        if (httpSession == null) return null;
        return httpSession.getAttribute(attributeName);
    }

    public void removeFromSession(String attributeName){
        HttpSession httpSession = getSession();
        if (httpSession == null) return;
        httpSession.removeAttribute(attributeName);
    }
}
