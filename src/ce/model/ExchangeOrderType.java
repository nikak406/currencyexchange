package ce.model;

public enum ExchangeOrderType {
	BUY,
	SELL;

	public String getLabel(){
		return this.name();
	}
}
