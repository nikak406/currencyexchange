package ce.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name="Orders")
public class Order implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int number;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@ManyToOne
	private User dealer;

	//TODO change String to Currency
    private String currency;

	private OrderType orderType;

	private int maxAmount;

	private double rate;

	private boolean closed = false;

	public Order(){}

    public Order(Date date, User dealer, String currency, OrderType orderType,
			int maxAmount, double rate){
		this.setDate(date);
		this.setDealer(dealer);
		this.setCurrency(currency);
		this.setMaxAmount(maxAmount);
		this.setOrderType(orderType);
		this.setRate(rate);
		this.setClosed(false);
	}

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

	public User getDealer() {
		return dealer;
	}

	public void setDealer(User dealer) {
        this.dealer = dealer;
    }

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
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

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}
}
