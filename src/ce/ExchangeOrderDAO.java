package ce;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Singleton
public class ExchangeOrderDAO {

	@PersistenceContext
	EntityManager em;

	public void registerOrder(ExchangeOrder exchangeOrder){
		em.persist(exchangeOrder);
	}

	public void updateOrder(ExchangeOrder exchangeOrder){
		em.refresh(exchangeOrder);
	}

	public List<ExchangeOrder> getOrders(){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ExchangeOrder> cq = cb.createQuery(ExchangeOrder.class);
		Root<ExchangeOrder> rootEntry = cq.from(ExchangeOrder.class);
		CriteriaQuery<ExchangeOrder> all = cq.select(rootEntry);
		TypedQuery<ExchangeOrder> allQuery = em.createQuery(all);
		return allQuery.getResultList();
	}

	public List<ExchangeOrder> getOrders(User user){
		List<ExchangeOrder> allExchangeOrders = getOrders();
		//TODO
		return allExchangeOrders;
	}

	//TODO public Order getOrder(int number)
}