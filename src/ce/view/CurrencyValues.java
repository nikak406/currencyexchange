package ce.view;

import ce.model.Currencies;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class CurrencyValues {
	public Currencies[] getValues(){
		return Currencies.values();
	}
}
