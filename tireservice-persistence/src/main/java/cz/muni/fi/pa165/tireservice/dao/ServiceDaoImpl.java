package cz.muni.fi.pa165.tireservice.dao;

import cz.muni.fi.pa165.tireservice.entity.Service;
import java.math.BigDecimal;
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
public class ServiceDaoImpl implements ServiceDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Service service) {
        em.persist(service);
    }

    @Override
    public Service findById(Long id) {
        return em.find(Service.class, id);
    }

    @Override
    public List<Service> findAll() {
        return em.createQuery("select s from Service s", Service.class).getResultList();
    }

    @Override
    public void remove(Service service) throws IllegalArgumentException {
        em.remove(findById(service.getId()));
    }

    @Override
    public Service findByName(String name) {
        return em.createQuery("SELECT s FROM Service s WHERE s.name like :name",
                Service.class).setParameter("name", "%" + name + "%").getSingleResult();
    }

    @Override
    public List<Service> getServicesWithPriceBetween(BigDecimal leftLimit, BigDecimal rightLimit) {
        if (rightLimit.compareTo(leftLimit) == -1){
            throw new IllegalArgumentException();
        }
        return em.createQuery("Select s From Service s Where price BETWEEN :left AND :right",Service.class).setParameter("left", leftLimit).setParameter("right", rightLimit).getResultList();
    }

    @Override
    public Service update(Service service) {
        return em.merge(service);
    }

}
