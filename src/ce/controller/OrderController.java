package ce.controller;

import ce.model.DAO.OrderDAO;
import ce.model.LoggedInUser;
import ce.view.NewOrder;
import ce.model.Order;
import ce.model.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ManagedBean
@Stateless
public class OrderController {

	@EJB
	LoggedInUser loggedInUser;

    @EJB
    UserController userController;

    @EJB
	OrderDAO orderDAO;

    public void addOrder(NewOrder newOrder) {
        Date now = new Date();
        User currentUser = loggedInUser.getUser();
        Order order = new Order(now, currentUser, newOrder.getCurrency(),
                newOrder.getOrderType(), newOrder.getMaxAmount(), newOrder.getRate());
        orderDAO.saveOrder(order);
    }

    public List<Order> getOrders(){
        return orderDAO
				.getOrders()
				.stream()
				.sorted((order1, order2) -> order1.getDate().compareTo(order2.getDate()))
				.collect(Collectors.toList());
    }

	public List<Order> getMyOrders(){
		User currentUser = loggedInUser.getUser();
		List<Order> allOrders = getOrders();
		return allOrders
				.stream()
				.filter(order -> order.getDealer().equals(currentUser))
				.collect(Collectors.toList());
	}

	public void updateOrder(Order order){
		orderDAO.updateOrder(order);
	}
}
