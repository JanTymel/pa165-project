package cz.muni.fi.pa165.tireservice.dao;

import cz.muni.fi.pa165.tireservice.entity.TireVendor;
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
public class TireVendorDaoImpl implements TireVendorDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(TireVendor tv) {
        em.persist(tv);
    }

    @Override
    public TireVendor findById(Long id) {
        return em.find(TireVendor.class, id);
    }

    @Override
    public List<TireVendor> findAll() {
        return em.createQuery("select t from TireVendor t", TireVendor.class).getResultList();
    }

    @Override
    public void remove(TireVendor tv) throws IllegalArgumentException {
        em.remove(findById(tv.getId()));
    }

    @Override
    public TireVendor findByName(String namePattern) {
        return em.createQuery("SELECT t FROM TireVendor t WHERE t.name like :name",
                TireVendor.class).setParameter("name", "%" + namePattern + "%").getSingleResult();
    }

    @Override
    public TireVendor update(TireVendor tireVendor) {
        return em.merge(tireVendor);
    }

}
