package cz.muni.fi.pa165.tireservice.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import cz.muni.fi.pa165.tireservice.entity.Tire;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Samuel Baniar
 */
@Repository
@Transactional
public class TireDaoImpl implements TireDao {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void create(Tire t) {
        em.persist(t);
    }

    @Override
    public Tire findById(Long id) {
        return em.find(Tire.class, id);
    }

    @Override
    public List<Tire> findAll() {
        return em.createQuery("select t from Tire t", Tire.class).getResultList();
    }

    @Override
    public void remove(Tire t) throws IllegalArgumentException {
        em.remove(findById(t.getId()));
    }

    @Override
    public List<Tire> findByName(String namePattern) {
        return em.createQuery("SELECT t FROM Tire t WHERE t.name like :name",
                Tire.class).setParameter("name", "%" + namePattern + "%").getResultList();
    }
    
}
