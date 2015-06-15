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

	public void addTransaction(NewTransaction newTransaction, ExchangeOrder order){
		if (order.getMaxAmount() < newTransaction.getAmount()){
			newTransaction.setAmount(order.getMaxAmount());
		}
		order.setMaxAmount(order.getMaxAmount() - newTransaction.getAmount());
		exchangeOrderController.updateOrder(order);
		Transaction transaction = new Transaction();
		transaction.setAmount(newTransaction.getAmount());
		User customer = loggedInUser.getUser();
		transaction.setCustomer(customer);
		Date now = new Date();
		transaction.setDate(now);
		transaction.setOrder(order);
		transactionDAO.registerTransaction(transaction);
	}
}
