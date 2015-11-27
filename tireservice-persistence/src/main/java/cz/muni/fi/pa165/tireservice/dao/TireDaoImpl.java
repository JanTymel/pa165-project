package cz.muni.fi.pa165.tireservice.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import cz.muni.fi.pa165.tireservice.entity.Tire;
import cz.muni.fi.pa165.tireservice.entity.TireVendor;
import cz.muni.fi.pa165.tireservice.enums.CarType;
import java.math.BigDecimal;
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
        return em.createQuery("SELECT t FROM Tire t WHERE name like :name",
                Tire.class).setParameter("name", "%" + namePattern + "%").getResultList();
    }

    @Override
    public List<Tire> findByCarType(CarType ct) {
        return em.createQuery("SELECT t FROM Tire t WHERE carType = :carType",
                Tire.class).setParameter("carType", ct).getResultList();
    }

    @Override
    public List<Tire> findByDiameter(int d) {
        return em.createQuery("SELECT t FROM Tire t WHERE diameter = :diameter",
                Tire.class).setParameter("diameter", d).getResultList();
    }

    @Override
    public List<Tire> findByWidth(int w) {
        return em.createQuery("SELECT t FROM Tire t WHERE width = :width",
                Tire.class).setParameter("width", w).getResultList();
    }

    @Override
    public List<Tire> findByTireVendor(TireVendor tv) {
        return em.createQuery("SELECT t FROM Tire t WHERE tireVendor = :vendor",
                Tire.class).setParameter("vendor", tv).getResultList();
    }

    @Override
    public List<Tire> findBySpeedIndex(String si) {
        return em.createQuery("SELECT t FROM Tire t WHERE t.speedIndex like :index",
                Tire.class).setParameter("index", "%" + si + "%").getResultList();
    }

    @Override
    public List<Tire> getTiresWithPriceBetween(BigDecimal leftLimit, BigDecimal rightLimit) {
        if (rightLimit.compareTo(leftLimit) == -1){
            throw new IllegalArgumentException();
        }
        return em.createQuery("Select t From Tire t Where price BETWEEN :left AND :right",Tire.class).setParameter("left", leftLimit).setParameter("right", rightLimit).getResultList();
    }

    @Override
    public Tire update(Tire tire){
        return em.merge(tire);
    }
}
