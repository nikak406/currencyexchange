package ce.view;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.Min;

//TODO ajax money calculation add
@Named
@RequestScoped
public class IntBean{

    @Min(1)
	private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
