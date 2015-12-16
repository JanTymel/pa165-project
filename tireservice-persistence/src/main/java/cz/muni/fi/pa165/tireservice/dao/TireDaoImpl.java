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
    public void create(Tire tire) {
        em.persist(tire);
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
    public void remove(Tire tire) {
        em.remove(findById(tire.getId()));
    }

    @Override
    public List<Tire> findByName(String name) {
        return em.createQuery("SELECT t FROM Tire t WHERE name like :name",
                Tire.class).setParameter("name", "%" + name + "%").getResultList();
    }

    @Override
    public List<Tire> findByCarType(CarType carType) {
        return em.createQuery("SELECT t FROM Tire t WHERE carType = :carType",
                Tire.class).setParameter("carType", carType).getResultList();
    }

    @Override
    public List<Tire> findByDiameter(int diameter) {
        return em.createQuery("SELECT t FROM Tire t WHERE diameter = :diameter",
                Tire.class).setParameter("diameter", diameter).getResultList();
    }

    @Override
    public List<Tire> findByWidth(int width) {
        return em.createQuery("SELECT t FROM Tire t WHERE width = :width",
                Tire.class).setParameter("width", width).getResultList();
    }

    @Override
    public List<Tire> findByTireVendor(TireVendor tireVendor) {
        return em.createQuery("SELECT t FROM Tire t WHERE tireVendor = :vendor",
                Tire.class).setParameter("vendor", tireVendor).getResultList();
    }

    @Override
    public List<Tire> findBySpeedIndex(String speedIndex) {
        return em.createQuery("SELECT t FROM Tire t WHERE t.speedIndex like :index",
                Tire.class).setParameter("index", "%" + speedIndex + "%").getResultList();
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
