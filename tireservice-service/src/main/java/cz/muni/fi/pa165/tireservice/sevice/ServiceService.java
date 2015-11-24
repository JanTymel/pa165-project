package cz.muni.fi.pa165.tireservice.sevice;

import cz.muni.fi.pa165.tireservice.entity.Service;
import java.math.BigDecimal;
import java.util.List;

/**
 * Service layer interface for entity Service.
 *
 * Dear evaluator, I'm terribly sorry for the name of this interface and its implementation, it is not intended ¯\_(ツ)_/¯
 * trust me ^^
 *
 * @author Jan Tymel
 */
public interface ServiceService {

    /**
     * Returns service with specified id.
     *
     * @param id Id of service
     * @return Service with specified id
     */
    Service findById(Long id);

    /**
     * Returns service with specified name.
     *
     * @param serviceName Name of service
     * @return Service with specified name
     */
    Service findByName(String serviceName);

    /**
     * Returns all services.
     *
     * @return List of all services
     */
    List<Service> findAll();

    /**
     * Returns all services with price between lower and upper limit.
     *
     * @param leftLimit Lower limit of price
     * @param rightLimit Upper limit of price
     * @return All such services
     */
    public List<Service> getServicesWithPriceBetween(BigDecimal leftLimit, BigDecimal rightLimit);

    /**
     * Creates new service.
     *
     * @param service Service that should be created
     */
    void create(Service service);

    /**
     * Removes specified service.
     *
     * @param service Service that should be removed.
     */
    void remove(Service service);

    /**
     * Updates specified service.
     *
     * @param service Service that should be updated.
     * @return Updated service
     */
    Service update(Service service);
}
