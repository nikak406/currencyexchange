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
public class TransactionController {

	@EJB
	LoginController loginController;

	@EJB
	UserController userController;

	@EJB
	TransactionDAO transactionDAO;

	@EJB
	ExchangeOrderController exchangeOrderController;

	@EJB
	LoggedInUser loggedInUser;

	private List<Transaction> getTransactions(){
		return transactionDAO.getTransactions();
	}

	public List<Transaction> getMyTransactions(){
		List<Transaction> allTransactions = transactionDAO.getTransactions();
		User currentUser = loggedInUser.getUser();
		return allTransactions
				.stream()
				.filter(transaction -> transaction.getCustomer().equals(currentUser)
						|| transaction.getOrder().getDealer().equals(currentUser))
				.collect(Collectors.toList());
	}

	public void addTransaction(ExchangeOrder order){
		//TODO all transactions have 0 amount
		int maxAmount = order.getMaxAmount();
		int amount = order.getAmount();
		if (maxAmount < amount){
			order.setAmount(maxAmount);
			amount = maxAmount;
		}
		order.setMaxAmount(maxAmount - amount);
		exchangeOrderController.updateOrder(order);
		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		User customer = loggedInUser.getUser();
		transaction.setCustomer(customer);
		Date now = new Date();
		transaction.setDate(now);
		transaction.setOrder(order);
		transactionDAO.registerTransaction(transaction);
	}
}
