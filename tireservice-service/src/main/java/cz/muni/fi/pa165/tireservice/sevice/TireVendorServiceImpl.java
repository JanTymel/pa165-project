package cz.muni.fi.pa165.tireservice.sevice;

import cz.muni.fi.pa165.tireservice.dao.TireVendorDao;
import cz.muni.fi.pa165.tireservice.entity.TireVendor;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of TireVendorService
 *
 * @author Jan Tymel
 */
@org.springframework.stereotype.Service
public class TireVendorServiceImpl implements TireVendorService {
    @Autowired
    private TireVendorDao tireVendorDao;

    @Override
    public TireVendor findById(Long id) {
        return tireVendorDao.findById(id);
    }

    @Override
    public TireVendor findByName(String tireVendorName) {
        return tireVendorDao.findByName(tireVendorName);
    }

    @Override
    public List<TireVendor> findAll() {
        return tireVendorDao.findAll();
    }

    @Override
    public void create(TireVendor tireVendor) {
        tireVendorDao.create(tireVendor);
    }

    @Override
    public void remove(TireVendor tireVendor) {
        tireVendorDao.remove(tireVendor);
    }

    @Override
    public TireVendor update(TireVendor tireVendor) {
        return tireVendorDao.update(tireVendor);
    }


}
