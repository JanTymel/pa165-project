package cz.muni.fi.pa165.tireservice.dao;

import cz.muni.fi.pa165.tireservice.entity.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Samuel Baniar
 */
@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(User u) {
        em.persist(u);
    }

    @Override
    public User findById(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public List<User> findUsersByName(String name) {
        return em.createQuery("Select u from User u Where name like :name",User.class).setParameter("name", "%" + name + "%").getResultList();
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("Select u from User u",User.class).getResultList();
    }
    
}
