package ce.model.DAO;

import ce.model.Order;
import ce.model.User;

import javax.ejb.Singleton;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
		CriteriaQuery<Order> all = query.select(root);
        all.orderBy(builder.desc(root.get("date").as(Date.class)));
		TypedQuery<Order> allQuery = em.createQuery(all);
		return allQuery.getResultList();
	}

	//TODO don't repeat yourself
    public List<Order> getOrders(User user){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Order> query = builder.createQuery(Order.class);
        Root<Order> root = query.from(Order.class);
        CriteriaQuery<Order> selected = query.select(root).where(builder.equal(root.get("dealer"), user));
        selected.orderBy(builder.desc(root.get("date").as(Date.class)));
        TypedQuery<Order> selectedQuery = em.createQuery(selected);
        return selectedQuery.getResultList();
    }
}
