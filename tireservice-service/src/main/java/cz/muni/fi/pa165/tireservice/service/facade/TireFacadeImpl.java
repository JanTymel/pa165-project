package cz.muni.fi.pa165.tireservice.service.facade;

import cz.muni.fi.pa165.tireservice.dto.TireDto;
import cz.muni.fi.pa165.tireservice.dto.TireVendorDto;
import cz.muni.fi.pa165.tireservice.entity.Tire;
import cz.muni.fi.pa165.tireservice.entity.TireVendor;
import cz.muni.fi.pa165.tireservice.enums.CarType;
import cz.muni.fi.pa165.tireservice.facade.TireFacade;
import cz.muni.fi.pa165.tireservice.sevice.BeanMappingService;
import cz.muni.fi.pa165.tireservice.sevice.TireService;
import cz.muni.fi.pa165.tireservice.sevice.TireVendorService;
import java.math.BigDecimal;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Samuel Baniar
 */
@org.springframework.stereotype.Service
@Transactional
public class TireFacadeImpl implements TireFacade{

    @Autowired
    private TireService tireService;

    @Autowired TireVendorService tireVendorService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public Long createTire(TireDto t) {
        Tire mappedTire = beanMappingService.mapTo(t, Tire.class);

        mappedTire.setTireVendor(tireVendorService.findById(t.getTireVendor().getId()));

        tireService.createTire(mappedTire);
        return mappedTire.getId();
    }

    @Override
    public void deleteTire(Long productId) {
        tireService.deleteTire(new Tire(productId));
    }

    @Override
    public TireDto getTireWithId(Long id) {
        return beanMappingService.mapTo(tireService.findById(id), TireDto.class);
    }

    @Override
    public List<TireDto> getAllTires() {
        return beanMappingService.mapTo(tireService.findAll(), TireDto.class);
    }

    @Override
    public List<TireDto> getTiresByName(String namePattern) {
        return beanMappingService.mapTo(tireService.findByName(namePattern), TireDto.class);
    }

    @Override
    public List<TireDto> getTiresByCarType(CarType carType) {
        return beanMappingService.mapTo(tireService.findByCarType(carType), TireDto.class);
    }

    @Override
    public List<TireDto> getTiresByDiameter(int diameter) {
        return beanMappingService.mapTo(tireService.findByDiameter(diameter), TireDto.class);
    }

    @Override
    public List<TireDto> getTiresByWidth(int width) {
        return beanMappingService.mapTo(tireService.findByWidth(width), TireDto.class);
    }

    @Override
    public List<TireDto> getTiresByTireVendor(TireVendorDto vendor) {
        TireVendor tv = tireVendorService.findById(vendor.getId());
        return beanMappingService.mapTo(tireService.findByTireVendor(tv), TireDto.class);
    }

    @Override
    public List<TireDto> getTiresBySpeedIndex(String speedIndex) {
        return beanMappingService.mapTo(tireService.findBySpeedIndex(speedIndex), TireDto.class);
    }

    @Override
    public List<TireDto> getTiresWithPriceBetween(BigDecimal leftLimit, BigDecimal rightLimit) {
        return beanMappingService.mapTo(tireService.getTiresWithPriceBetween(leftLimit, rightLimit), TireDto.class);
    }

    @Override
    public void changePrice(Long id, BigDecimal newPrice) {
        tireService.changePrice(id, newPrice);
    }
}
