package ce.view;

import ce.model.Transaction;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class NewTransaction extends Transaction {
	public NewTransaction(){}
}
