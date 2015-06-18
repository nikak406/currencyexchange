package ce.view;

import ce.model.OrderType;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class OrderTypeValues {
	public OrderType[] getValues(){
		return OrderType.values();
	}
}
