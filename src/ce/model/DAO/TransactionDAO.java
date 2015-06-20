package ce.model.DAO;

import ce.model.Transaction;

import javax.ejb.Singleton;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Transaction> cq = cb.createQuery(Transaction.class);
		Root<Transaction> rootEntry = cq.from(Transaction.class);
		CriteriaQuery<Transaction> all = cq.select(rootEntry);
		TypedQuery<Transaction> allQuery = em.createQuery(all);
		return allQuery.getResultList();
	}

	//todo get transactions by name
}
