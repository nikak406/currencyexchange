package ce;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ManagedBean
@Stateless
public class ExchangeOrderController {

	@EJB
    LoggedInUser loggedInUser;

    @EJB
    UserController userController;

    @EJB
    ExchangeOrderDAO exchangeOrderDAO;

    public void addOrder(NewOrder newOrder) {
        Date now = new Date();
        User currentUser = loggedInUser.getUser();
        ExchangeOrder order = new ExchangeOrder(now, currentUser, newOrder.getCurrency(),
                newOrder.getExchangeOrderType(), newOrder.getMaxAmount(), newOrder.getRate());
        exchangeOrderDAO.registerOrder(order);
    }

    public List<ExchangeOrder> getOrders(){
        return exchangeOrderDAO
				.getOrders()
				.stream()
				.sorted((order1, order2) -> order1.getDate().compareTo(order2.getDate()))
				.collect(Collectors.toList());
    }

	public List<ExchangeOrder> getMyOrders(){
		User currentUser = loggedInUser.getUser();
		List<ExchangeOrder> allOrders = getOrders();
		return allOrders
				.stream()
				.filter(order -> order.getDealer().equals(currentUser))
				.collect(Collectors.toList());
	}

	public void updateOrder(ExchangeOrder order){
		exchangeOrderDAO.updateOrder(order);
	}
}
