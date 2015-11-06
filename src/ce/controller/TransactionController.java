package ce.controller;

import ce.model.*;
import ce.model.DAO.TransactionDAO;
import ce.view.IntBean;
import ce.view.NewTransaction;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Named
@Stateless
public class TransactionController {

	@EJB
	LoginController loginController;

	@EJB
	UserController userController;

	@EJB
	TransactionDAO transactionDAO;

	@EJB
	OrderController orderController;

    @Inject @LoggedInUser
    User loggedInUser;

	@EJB
    NewTransaction newTransaction;

	private List<Transaction> getTransactions(){
		return transactionDAO.getTransactions();
	}

	public List<Transaction> getMyTransactions(){
		List<Transaction> allTransactions = getTransactions();
		return allTransactions
				.stream()
				.filter(transaction ->
						transaction.getCustomer().equals(loggedInUser)
						|| transaction.getOrder().getDealer().equals(loggedInUser))
				.collect(Collectors.toList());
	}
// TODO Mail
	public void addTransaction(IntBean intBean){
		Order order = newTransaction.getOrder();
		if (order == null) return;
		int maxAmount = order.getMaxAmount();
		int amount = intBean.getValue();
		if (maxAmount < amount){
			amount = maxAmount;
		}
		order.setMaxAmount(maxAmount - amount);
		orderController.updateOrder(order);
		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		transaction.setCustomer(loggedInUser);
		Date now = new Date();
		transaction.setDate(now);
		transaction.setOrder(order);
		transactionDAO.registerTransaction(transaction);
		//MailSender.sendMail(transaction);
	}

	public void addTransactionOrder(Order order){
		newTransaction.setOrder(order);
	}

	public List<Order> getNewTransaction(){
		List<Order> list = new ArrayList<>();
		list.add(newTransaction.getOrder());
		return list;
	}
}
