package ce;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

//TODO refactor names
//TODO refactor packets
@Entity
public class ExchangeOrder implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int number;

	//TODO switch to Java8 date
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime date;

	@ManyToOne
	private User dealer;

	//TODO change String to Currency
    private String currency;

	private ExchangeOrderType exchangeOrderType;

	private int maxAmount;

	private double rate;

	private boolean closed = false;

	public ExchangeOrder(){}

    public ExchangeOrder(LocalDateTime date, User dealer, String currency, ExchangeOrderType exchangeOrderType,
                         int maxAmount, double rate){
		this.setDate(date);
		this.setDealer(dealer);
		this.setCurrency(currency);
		this.setMaxAmount(maxAmount);
		this.setExchangeOrderType(exchangeOrderType);
		this.setRate(rate);
		this.setClosed(false);
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
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
