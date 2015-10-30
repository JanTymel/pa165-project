package cz.muni.fi.pa165.tireservice.dao;

import cz.muni.fi.pa165.tireservice.entity.Tire;
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
}
