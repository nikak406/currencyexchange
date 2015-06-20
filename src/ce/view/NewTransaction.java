package ce.view;

import ce.model.Order;

import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import java.io.Serializable;

@Stateful
@ConversationScoped
public class NewTransaction implements Serializable {

	private Order order;

    @Inject
    private Conversation conversation;

	public Order getOrder() {
		if (!conversation.isTransient()) conversation.end();
        return order;
	}

	public void setOrder(Order order) {
		if (conversation.isTransient()) conversation.begin();
        this.order = order;
	}
}
