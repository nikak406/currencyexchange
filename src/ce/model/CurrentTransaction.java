package ce.model;

import javax.ejb.Singleton;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;

@Singleton
@ApplicationScoped
//TODO @ConversationScoped() EJB
//TODO refactor name
public class CurrentTransaction implements Serializable {

	private Order order;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
}
