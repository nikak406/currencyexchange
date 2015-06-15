package ce;

public enum ExchangeOrderType {
	BUY,
	SELL;

	public String getLabel(){
		return this.name();
	}
}
