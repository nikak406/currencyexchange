package ce.controller;

import ce.view.CurrentTransaction;
import ce.model.LoggedInUser;
import ce.view.NewTransaction;
import ce.model.TransactionDAO;
import ce.model.ExchangeOrder;
import ce.model.Transaction;
import ce.model.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

	@EJB
	CurrentTransaction currentTransaction;

	private List<Transaction> getTransactions(){
		return transactionDAO
				.getTransactions()
				.stream()
				.sorted((transaction1, transaction2) -> transaction1.getDate().compareTo(transaction2.getDate()))
				.collect(Collectors.toList());
	}

	public List<Transaction> getMyTransactions(){
		List<Transaction> allTransactions = getTransactions();
		User currentUser = loggedInUser.getUser();
		return allTransactions
				.stream()
				.filter(transaction -> transaction.getCustomer().equals(currentUser)
						|| transaction.getOrder().getDealer().equals(currentUser))
				.collect(Collectors.toList());
	}

	public void addTransaction(NewTransaction newTransaction){
		ExchangeOrder order = currentTransaction.getOrder();
		if (order == null) return;
		int maxAmount = order.getMaxAmount();
		int amount = newTransaction.getAmount();
		if (maxAmount < amount){
			newTransaction.setAmount(maxAmount);
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

	public void addTransactionOrder(ExchangeOrder order){
		currentTransaction.setOrder(order);
	}

	public List<ExchangeOrder> getCurrentTransaction(){
		List<ExchangeOrder> list = new ArrayList<>();
		list.add(currentTransaction.getOrder());
		return list;
	}
}
