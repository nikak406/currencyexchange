package ce;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Transaction implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int number;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	private int amount;

	@ManyToOne
    private ExchangeOrder order;

	@ManyToOne
    private User customer;

	public Transaction(){}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public ExchangeOrder getOrder() {
		return order;
	}

	public void setOrder(ExchangeOrder order) {
		this.order = order;
	}

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
