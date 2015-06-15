package ce;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class ExchangeOrder implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int number;

	@Temporal(TemporalType.DATE)
	private Date date;

	@ManyToOne
	private User dealer;

	//TODO change String to Currency
    private String currency;

	private ExchangeOrderType exchangeOrderType;

	private int maxAmount;

	private double rate;

	private boolean closed = false;

	public ExchangeOrder(){}

    public ExchangeOrder(Date date, User dealer, String currency, ExchangeOrderType exchangeOrderType,
                         int maxAmount, double rate){}

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

	public ExchangeOrderType getExchangeOrderType() {
		return exchangeOrderType;
	}

	public void setExchangeOrderType(ExchangeOrderType exchangeOrderType) {
		this.exchangeOrderType = exchangeOrderType;
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
