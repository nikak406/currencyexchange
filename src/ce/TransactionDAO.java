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
public class TransactionDAO {

	@PersistenceContext
	EntityManager em;

	public void registerTransaction(Transaction transaction){
		em.persist(transaction);
	}

	public void updateTransaction(Transaction transaction){
		em.refresh(transaction);
	}

	public List<Transaction> getTransactions(){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Transaction> cq = cb.createQuery(Transaction.class);
		Root<Transaction> rootEntry = cq.from(Transaction.class);
		CriteriaQuery<Transaction> all = cq.select(rootEntry);
		TypedQuery<Transaction> allQuery = em.createQuery(all);
		return allQuery.getResultList();
	}

	public List<Transaction> getTransactions(User user){
		List<Transaction> allTransactions = getTransactions();
		//TODO
		return allTransactions;
	}

	//TODO public Transaction getTransaction(int number)
}
