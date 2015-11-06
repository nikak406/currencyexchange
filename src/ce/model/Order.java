package ce.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Currency;
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

	@Convert(converter = CurrencyConverter.class)
    private Currency currency;

    @Enumerated(EnumType.STRING)
    private OrderType orderType;

	@Min(1)
	private int maxAmount;

	@Min(0)
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
		return currency.getCurrencyCode();
	}

	public void setCurrency(String currency) {
		this.currency = Currency.getInstance(currency);
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

	@Override
	public String toString(){
		return orderType.getLabel() + ' ' + getMaxAmount() + ' ' + getCurrency();
	}
}
