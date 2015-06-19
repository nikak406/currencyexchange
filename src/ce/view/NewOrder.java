package ce.view;

import ce.model.Order;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

//TODO add popup
//TODO add css
@Named
@RequestScoped
public class NewOrder extends Order{
	public NewOrder(){}
}
