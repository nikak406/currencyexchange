package ce.model;

import java.io.Serializable;

public enum Currencies implements Serializable {
	USD,
	EUR,
	RUR,
	JPY,
	GPB,
	CHF;

	public String getLabel(){
		return this.name();
	}
}
