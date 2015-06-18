package ce.model;

import javax.ejb.Singleton;
import javax.faces.bean.SessionScoped;

@Singleton
@SessionScoped
//TODO @FlowScoped()
//@Stateful
//TODO refactor name
public class CurrentTransaction {

	private Order order;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
}
