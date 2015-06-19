package ce.model;

import java.io.Serializable;

public enum OrderType implements Serializable {
	BUY,
	SELL;

	public String getLabel(){
		return this.name();
	}
}
