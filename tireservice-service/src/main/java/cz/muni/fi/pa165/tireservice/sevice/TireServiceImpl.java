package cz.muni.fi.pa165.tireservice.sevice;

import cz.muni.fi.pa165.tireservice.dao.TireDao;
import cz.muni.fi.pa165.tireservice.dao.TireVendorDao;
import cz.muni.fi.pa165.tireservice.entity.Tire;
import cz.muni.fi.pa165.tireservice.entity.TireVendor;
import cz.muni.fi.pa165.tireservice.enums.CarType;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Samuel Baniar
 */

@Service
public class TireServiceImpl implements TireService {

    @Autowired
    private TireDao tireDao;
    
    @Autowired
    private TireVendorDao tireVendorDao;
          
    @Override
    public void createTire(Tire tire) {
        //tireVendorDao.create(tire.getTireVendor());
        tireDao.create(tire);
    }

    @Override
    public void deleteTire(Tire tire) {
        tireDao.remove(tire);
    }

    @Override
    public Tire findById(Long id) {
        return tireDao.findById(id);
    }

    @Override
    public List<Tire> findAll() {
        return tireDao.findAll();
    }

    @Override
    public List<Tire> findByName(String namePattern) {
        return tireDao.findByName(namePattern);
    }

    @Override
    public List<Tire> findByCarType(CarType carType) {
        return tireDao.findByCarType(carType);
    }

    @Override
    public List<Tire> findByDiameter(int d) {
        return tireDao.findByDiameter(d);
    }

    @Override
    public List<Tire> findByWidth(int w) {
        return tireDao.findByWidth(w);
    }

    @Override
    public List<Tire> findByTireVendor(TireVendor tv) {
        return tireDao.findByTireVendor(tv);
    }

    @Override
    public List<Tire> findBySpeedIndex(String si) {
        return tireDao.findBySpeedIndex(si);
    }

    @Override
    public List<Tire> getTiresWithPriceBetween(BigDecimal leftLimit, BigDecimal rightLimit) {
        return tireDao.getTiresWithPriceBetween(leftLimit, rightLimit);
    }    

    @Override
    public void changePrice(Tire tire, BigDecimal newPrice) {
        //Tire tire = tireDao.findById(id);
        tire.setPrice(newPrice);
        
        tireDao.update(tire);
    }
}
