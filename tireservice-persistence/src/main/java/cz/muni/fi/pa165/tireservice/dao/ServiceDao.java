package cz.muni.fi.pa165.tireservice.dao;

import cz.muni.fi.pa165.tireservice.entity.Service;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Michal Kysilko
 */
public interface ServiceDao {
    public void create(Service t);
    public Service findById(Long id);
    public List<Service> findAll();
    public void remove(Service t) throws IllegalArgumentException;
    public Service findByName(String namePattern);
    public List<Service> getServicesWithPriceBetween(BigDecimal leftLimit, BigDecimal rightLimit);
    public Service update(Service service);
}
