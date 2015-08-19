package ce.controller;

import ce.model.DAO.OrderDAO;
import ce.model.Order;
import ce.model.User;
import ce.view.NewOrder;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.List;

@Named
@Stateless
public class OrderController {

	@Inject @LoggedInUser
    User loggedInUser;

    @EJB
    UserController userController;

    @EJB
	OrderDAO orderDAO;

    public void addOrder(NewOrder newOrder) {
        Date now = new Date();
        Order order = new Order(now, loggedInUser, newOrder.getCurrency(),
                newOrder.getOrderType(), newOrder.getMaxAmount(), newOrder.getRate());
        orderDAO.saveOrder(order);
    }

    public List<Order> getOrders(){
        return orderDAO.getOrders();
    }
//todo cc interface
	public List<Order> getMyOrders(){
		return orderDAO.getOrders(loggedInUser);
	}

	public void updateOrder(Order order){
		orderDAO.updateOrder(order);
	}
}
