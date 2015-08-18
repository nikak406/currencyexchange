package ce.model.DAO;

import ce.model.Order;
import ce.model.User;

import javax.ejb.Singleton;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;

@Singleton
@ApplicationScoped
public class OrderDAO {

	@PersistenceContext
	EntityManager em;

	public void saveOrder(Order order){
		em.persist(order);
	}

	public void updateOrder(Order order){
		em.merge(order);
	}

	public List<Order> getOrders(){
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Order> query = builder.createQuery(Order.class);
		Root<Order> root = query.from(Order.class);
		Path<Date> date = root.get("date");
		CriteriaQuery<Order> all = query
				.select(root)
				.orderBy(builder.desc(date));
		TypedQuery<Order> allQuery = em.createQuery(all);
		return allQuery.getResultList();
	}

    public List<Order> getOrders(User user){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Order> query = builder.createQuery(Order.class);
        Root<Order> root = query.from(Order.class);
		Path<Date> date = root.get("date");
		Path<User> dealer = root.get("dealer");
		Predicate condition = builder.equal(dealer, user);
        CriteriaQuery<Order> selected = query
				.select(root)
				.where(condition)
				.orderBy(builder.desc(date));
        TypedQuery<Order> selectedQuery = em.createQuery(selected);
        return selectedQuery.getResultList();
    }
}
