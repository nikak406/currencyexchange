package ce.view;

import ce.model.ExchangeOrder;

import javax.ejb.Singleton;
import javax.faces.bean.SessionScoped;

@Singleton
@SessionScoped
//TODO @FlowScoped()
//@Stateful
public class CurrentTransaction {

	private ExchangeOrder order;

	public ExchangeOrder getOrder() {
		return order;
	}

	public void setOrder(ExchangeOrder order) {
		this.order = order;
	}
}
