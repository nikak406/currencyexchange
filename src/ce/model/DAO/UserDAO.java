package ce.model.DAO;

import ce.model.User;

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
public class UserDAO {

	@PersistenceContext
    EntityManager em;

	public void registerUser(User user){
        em.persist(user);
    }

    public void updateUser(User user){
        em.merge(user);
    }

	public List<User> getUsers(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> rootEntry = cq.from(User.class);
        CriteriaQuery<User> all = cq.select(rootEntry);
        TypedQuery<User> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    public User getUser(String login){
        return em.find(User.class, login);
    }
}
