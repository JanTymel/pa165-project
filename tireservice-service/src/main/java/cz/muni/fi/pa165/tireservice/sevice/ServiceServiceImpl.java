package cz.muni.fi.pa165.tireservice.sevice;

import cz.muni.fi.pa165.tireservice.dao.ServiceDao;
import cz.muni.fi.pa165.tireservice.entity.Service;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Implementation of SerivceService. ^^
 *
 * @author Jan Tymel
 */
public class ServiceServiceImpl implements ServiceService {
    @Autowired
    private ServiceDao serviceDao;

    @Override
    public Service findById(Long id) {
        return serviceDao.findById(id);
    }

    @Override
    public Service findByName(String serviceName) {
        return serviceDao.findByName(serviceName);
    }

    @Override
    public List<Service> findAll() {
        return serviceDao.findAll();
    }

    @Override
    public List<Service> getServicesWithPriceBetween(BigDecimal leftLimit, BigDecimal rightLimit) {
        return serviceDao.getServicesWithPriceBetween(leftLimit, rightLimit);
    }

    @Override
    public void create(Service service) {
        serviceDao.create(service);
    }

    @Override
    public void remove(Service service) {
        serviceDao.remove(service);
    }

    @Override
    public Service update(Service service) {
        return serviceDao.update(service);
    }

}
