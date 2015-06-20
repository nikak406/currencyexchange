package ce.view;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
//TODO ajax money calculation add
@Named
@RequestScoped
public class IntBean{

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
