package cz.muni.fi.pa165.tireservice.service.facade;

import cz.muni.fi.pa165.tireservice.dto.ServiceCreateDto;
import cz.muni.fi.pa165.tireservice.dto.ServiceDto;
import cz.muni.fi.pa165.tireservice.entity.Service;
import cz.muni.fi.pa165.tireservice.facade.ServiceFacade;
import cz.muni.fi.pa165.tireservice.sevice.BeanMappingService;
import cz.muni.fi.pa165.tireservice.sevice.ServiceService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Implementation of ServiceFacade.
 *
 * @author Jan Tymel
 */
public class ServiceFacadeImpl implements ServiceFacade {

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public Long createService(ServiceCreateDto serviceCreateDto) {
        Service service = new Service();
        service.setName(serviceCreateDto.getName());
        service.setPrice(serviceCreateDto.getPrice());
        serviceService.create(service);
        return service.getId();
    }

    @Override
    public List<ServiceDto> getAllServices() {
        return beanMappingService.mapTo(serviceService.findAll(), ServiceDto.class);
    }

    @Override
    public ServiceDto getServiceById(Long serviceId) {
        return beanMappingService.mapTo(serviceService.findById(serviceId), ServiceDto.class);
    }

    @Override
    public ServiceDto getServiceByName(String serviceName) {
        return beanMappingService.mapTo(serviceService.findByName(serviceName), ServiceDto.class);
    }

    @Override
    public List<ServiceDto> getServicesWithPriceBetween(BigDecimal leftLimit, BigDecimal rightLimit) {
        return beanMappingService.mapTo(serviceService.getServicesWithPriceBetween(leftLimit, rightLimit), ServiceDto.class);
    }

    @Override
    public void deleteService(Long serviceId) {
        serviceService.remove(new Service(serviceId));
    }

    @Override
    public void updateService(ServiceDto serviceDto) {
        serviceService.update(beanMappingService.mapTo(serviceDto, Service.class));
    }

}
