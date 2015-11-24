package cz.muni.fi.pa165.tireservice.dao;

import cz.muni.fi.pa165.tireservice.entity.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Samuel Baniar
 */
@Repository
@Transactional
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
        return em.createQuery("Select u from User u Where name like :name", User.class).setParameter("name", "%" + name + "%").getResultList();
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("Select u from User u", User.class).getResultList();
    }

    @Override
    public void remove(User user) {
        em.remove(user);
    }

    @Override
    public User update(User user) {
        return em.merge(user);
    }

    @Override
    public List<User> findByAddress(String address) {
        return em.createQuery("Select u from User u Where address like :address", User.class).setParameter("address", "%" + address + "%").getResultList();
    }

    @Override
    public User findByPhone(String phone) {
        return em.createQuery("SELECT u FROM User u WHERE u.phone like :phone",
                User.class).setParameter("phone", "%" + phone + "%").getSingleResult();
    }

    @Override
    public List<User> findAllAdmins() {
        return em.createQuery("Select u from User u Where isAdmin = 1", User.class).getResultList(); // needs to be checked
    }

    @Override
    public List<User> findAllCustomers() {
        return em.createQuery("Select u from User u Where isAdmin = 0", User.class).getResultList(); // needs to be checked
    }

}
