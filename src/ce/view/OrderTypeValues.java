package ce.view;

import ce.model.OrderType;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class OrderTypeValues {
	public OrderType[] getValues(){
		return OrderType.values();
	}
}
