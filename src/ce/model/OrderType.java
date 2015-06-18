package ce.model;

public enum OrderType {
	BUY,
	SELL;

	public String getLabel(){
		return this.name();
	}
}
