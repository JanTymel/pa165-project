package cz.muni.fi.pa165.tireservice.dao;

import cz.muni.fi.pa165.tireservice.entity.Tire;
import cz.muni.fi.pa165.tireservice.entity.TireVendor;
import cz.muni.fi.pa165.tireservice.enums.CarType;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Samuel Baniar
 */
public interface TireDao {
    public void create(Tire t);
    public Tire findById(Long id);
    public List<Tire> findAll();
    public void remove(Tire t) throws IllegalArgumentException;
    public List<Tire> findByName(String namePattern);
    public List<Tire> findByCarType(CarType ct);
    public List<Tire> findByDiameter(int d);
    public List<Tire> findByWidth(int w);
    public List<Tire> findByTireVendor(TireVendor tv);
    public List<Tire> findBySpeedIndex(String si);
    public List<Tire> getTiresWithPriceBetween(BigDecimal leftLimit, BigDecimal rightLimit);
    public Tire update(Tire tire);
}
