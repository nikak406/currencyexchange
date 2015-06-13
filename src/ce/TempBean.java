package ce;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;

@ManagedBean
public class TempBean {
    @EJB
    LoginController loginController;

    @EJB
    UserController userController;

    @EJB
    ExchangeOrderController orderController;

    public TempBean(){}

    public String getCurrentUser() {
        return loginController.getCurrentUser();
    }

    public int getUsersNumber() {
        return userController.getUsers().size();
    }

    public int getOrderNumber() {
        return orderController.getOrders().size();
    }
}
