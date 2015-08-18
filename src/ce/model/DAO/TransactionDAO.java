package ce.model.DAO;

import ce.model.Order;
import ce.model.Transaction;
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
public class TransactionDAO {

	@PersistenceContext
	EntityManager em;

	public void registerTransaction(Transaction transaction){
		em.persist(transaction);
	}

	public void updateTransaction(Transaction transaction){
		em.merge(transaction);
	}

	public List<Transaction> getTransactions(){
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Transaction> query = builder.createQuery(Transaction.class);
		Root<Transaction> root = query.from(Transaction.class);
		Path<Date> date = root.get("date");
		CriteriaQuery<Transaction> all = query
				.select(root)
				.orderBy(builder.desc(date));
		TypedQuery<Transaction> allQuery = em.createQuery(all);
		return allQuery.getResultList();
	}

    public List<Transaction> getTransactions(User user){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Transaction> query = builder.createQuery(Transaction.class);
        Root<Transaction> root = query.from(Transaction.class);
		Path<User> customer = root.get("customer");
		Predicate customerCondition = builder.equal(customer, user);
		Path<Order> order = root.get("order");
		Path<User> dealer = order.get("dealer");
		Predicate dealerCondition = builder.equal(dealer, user);
		Predicate anyCondition = builder.or(dealerCondition, customerCondition);
		Path<Date> date = root.get("date");
		CriteriaQuery<Transaction> selected = query
                .select(root)
				.where(anyCondition)
				.orderBy(builder.desc(date));
        TypedQuery<Transaction> selectedQuery = em.createQuery(selected);
        return selectedQuery.getResultList();
    }
}
