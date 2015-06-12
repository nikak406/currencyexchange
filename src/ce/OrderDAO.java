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
public class OrderDAO {

	@PersistenceContext
	EntityManager em;

	public void registerOrder(Order order){
		em.persist(order);
	}

	public void updateOrder(Order order){
		em.refresh(order);
	}

	public List<Order> getOrders(){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Order> cq = cb.createQuery(Order.class);
		Root<Order> rootEntry = cq.from(Order.class);
		CriteriaQuery<Order> all = cq.select(rootEntry);
		TypedQuery<Order> allQuery = em.createQuery(all);
		return allQuery.getResultList();
	}

	public List<Order> getOrders(User user){
		List<Order> allOrders = getOrders();
		//TODO
		return allOrders;
	}

	//TODO public Order getOrder(int number)
}
