package ce.view;

import ce.model.Transaction;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
//TODO ajax money calculation add
@Named
@RequestScoped
public class NewTransaction extends Transaction {
	public NewTransaction(){}
}
