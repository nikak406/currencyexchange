package ce;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

//TODO add sorting
@ManagedBean
@Stateless
public class ExchangeOrderController {

	@EJB
    LoginController loginController;

    @EJB
    UserController userController;

    @EJB
    ExchangeOrderDAO exchangeOrderDAO;

    public void addOrder(NewOrder newOrder) {
        Date now = new Date();
        String currentLogin = loginController.getCurrentUser();
        User currentUser = userController.getUser(currentLogin);

        ExchangeOrder order = new ExchangeOrder(now, currentUser, newOrder.getCurrency(),
                ExchangeOrderType.BUY, newOrder.getMaxAmount(), newOrder.getRate());
        exchangeOrderDAO.registerOrder(order);
    }

    public List<ExchangeOrder> getOrders(){
        return exchangeOrderDAO.getOrders();
    }

	public List<ExchangeOrder> getMyOrders(){
		String login = loginController.getCurrentUser();
		User currentUser = userController.getUser(login);
		List<ExchangeOrder> allOrders = getOrders();
		return allOrders.stream().filter(order -> order.getDealer() == currentUser).collect(Collectors.toList());
	}

	public void updateOrder(ExchangeOrder order){
		exchangeOrderDAO.updateOrder(order);
	}
}
