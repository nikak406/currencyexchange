package ce;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.Date;
import java.util.List;

@ManagedBean
@Stateful
@SessionScoped
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
	//TODO public List<ExchangeOrder> getOrders(User user)
}
