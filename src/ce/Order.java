package ce;

import javax.persistence.*;
import java.util.Currency;
import java.util.Date;

@Entity
public class Order{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int number;

	@Temporal(TemporalType.DATE)
	private Date date;

	//TODO @ManyToOne
	//TODO @JoinColumn(name = "")
	//TODO private User dealer;

	private Currency currency;

	private OrderType orderType;

	private int maxAmount;

	private double course;

	private boolean closed = false;

	public Order(){}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	/*public User getDealer() {
		return dealer;
	}

	public void setDealer(User dealer) {
		this.dealer = dealer;
	}*/

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	public int getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(int maxAmount) {
		this.maxAmount = maxAmount;
	}

	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	public double getCourse() {
		return course;
	}

	public void setCourse(double course) {
		this.course = course;
	}
}
