package ce.view;

import ce.model.ExchangeOrderType;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class OrderTypeBean {
	public ExchangeOrderType[] getValues(){
		return ExchangeOrderType.values();
	}
}
