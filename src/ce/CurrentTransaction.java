package ce;

import javax.ejb.Stateful;

@Stateful
//@FlowScoped()
public class CurrentTransaction {

	private ExchangeOrder order;

	public ExchangeOrder getOrder() {
		return order;
	}

	public void setOrder(ExchangeOrder order) {
		this.order = order;
	}
}
