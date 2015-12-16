package cz.muni.fi.pa165.tireservice.dao;

import cz.muni.fi.pa165.tireservice.entity.Service;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Michal Kysilko
 */
public interface ServiceDao {

    /**
     * Creates new service.
     *
     * @param service Service that should be created
     */
    public void create(Service service);

    /**
     * Returns service with specified id.
     *
     * @param id Id of service
     * @return Service with specified id
     */
    public Service findById(Long id);

    /**
     * Returns all services.
     *
     * @return All services
     */
    public List<Service> findAll();

    /**
     * Removes specified service.
     *
     * @param service Service that should be removed
     */
    public void remove(Service service);

    /**
     * Returns service with specified name.
     *
     * @param name Name of service
     * @return Service with specified name
     */
    public Service findByName(String name);

    /**
     * Returns all service in specified price limit.
     *
     * @param leftLimit Lower bound of price
     * @param rightLimit Upper bound of price
     * @return All services in specified price limit
     */
    public List<Service> getServicesWithPriceBetween(BigDecimal leftLimit, BigDecimal rightLimit);

    /**
     * Updates specified service.
     *
     * @param service Service that should be updated
     * @return Updated service
     */
    public Service update(Service service);
}
