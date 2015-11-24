package cz.muni.fi.pa165.tireservice.facade;

import cz.muni.fi.pa165.tireservice.dto.ServiceCreateDto;
import cz.muni.fi.pa165.tireservice.dto.ServiceDto;
import java.math.BigDecimal;
import java.util.List;

/**
 * Facade layer interface for Service entity.
 *
 * @author Jan Tymel
 */
public interface ServiceFacade {

    /**
     * Creates new service.
     *
     * @param serviceCreateDto CreateDTO of new service
     * @return Id of newly created service.
     */
    public Long createService(ServiceCreateDto serviceCreateDto);

    /**
     * Returns all services.
     *
     * @return List of all DTO services.
     */
    public List<ServiceDto> getAllServices();

    /**
     * Returns service by specified id.
     *
     * @param serviceId Id of requested service.
     * @return DTO of service with specified id.
     */
    public ServiceDto getServiceById(Long serviceId);

    /**
     * Returns service by specified name.
     *
     * @param  serviceName name of service.
     * @return DTO of service with specified name.
     */
    public ServiceDto getServiceByName(String serviceName);

    /**
     * Returns all services with price between lower and upper limit.
     *
     * @param leftLimit Lower limit of price
     * @param rightLimit Upper limit of price
     * @return DTOs of all such services
     */
    public List<ServiceDto> getServicesWithPriceBetween(BigDecimal leftLimit, BigDecimal rightLimit);

    /**
     * Deletes service with specified id.
     *
     * @param serviceId Id of service which should be removed.
     */
    public void deleteService(Long serviceId);

    /**
     * Updates service.
     *
     * @param serviceDto DTO of updated service.
     */
    public void updateService(ServiceDto serviceDto);
}
