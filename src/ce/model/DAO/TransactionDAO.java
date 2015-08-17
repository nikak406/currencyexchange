package ce.model.DAO;

import ce.model.Transaction;
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
		CriteriaQuery<Transaction> all = query.select(root);
        all.orderBy(builder.desc(root.get("date").as(Date.class)));
		TypedQuery<Transaction> allQuery = em.createQuery(all);
		return allQuery.getResultList();
	}

    //TODO have to fix
    public List<Transaction> getTransactions(User user){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Transaction> query = builder.createQuery(Transaction.class);
        Root<Transaction> root = query.from(Transaction.class);
        CriteriaQuery<Transaction> selected = query
                .select(root)
                .where(builder.equal(root.get("customer"), user));
        selected.orderBy(builder.desc(root.get("date").as(Date.class)));
        TypedQuery<Transaction> selectedQuery = em.createQuery(selected);
        return selectedQuery.getResultList();
    }
}
