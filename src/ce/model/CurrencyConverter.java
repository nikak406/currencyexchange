package ce.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Currency;

@Converter
public class CurrencyConverter implements AttributeConverter<Currency, String>{

	@Override
	public String convertToDatabaseColumn(Currency currency) {
		return currency.getCurrencyCode();
	}

	@Override
	public Currency convertToEntityAttribute(String s) {
		return Currency.getInstance(s);
	}
}
