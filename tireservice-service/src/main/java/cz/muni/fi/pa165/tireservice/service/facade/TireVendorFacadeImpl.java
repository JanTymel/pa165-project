package cz.muni.fi.pa165.tireservice.service.facade;

import cz.muni.fi.pa165.tireservice.dto.TireVendorCreateDto;
import cz.muni.fi.pa165.tireservice.dto.TireVendorDto;
import cz.muni.fi.pa165.tireservice.entity.TireVendor;
import cz.muni.fi.pa165.tireservice.facade.TireVendorFacade;
import cz.muni.fi.pa165.tireservice.sevice.BeanMappingService;
import cz.muni.fi.pa165.tireservice.sevice.TireVendorService;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Jan Tymel
 */
@org.springframework.stereotype.Service
@Transactional
public class TireVendorFacadeImpl implements TireVendorFacade {

    @Autowired
    private TireVendorService tireVendorService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public Long createTireVendor(TireVendorCreateDto tireVendorCreateDto) {
        TireVendor tireVendor = new TireVendor();
        tireVendor.setName(tireVendorCreateDto.getName());
        tireVendorService.create(tireVendor);
        return tireVendor.getId();
    }

    @Override
    public List<TireVendorDto> getAllTireVendors() {
        return beanMappingService.mapTo(tireVendorService.findAll(),TireVendorDto.class);
    }

    @Override
    public TireVendorDto getTireVendorById(Long tireVendorId) {
        return beanMappingService.mapTo(tireVendorService.findById(tireVendorId),TireVendorDto.class);
    }

    @Override
    public TireVendorDto getTireVendorByName(String tireVendorName) {
        return beanMappingService.mapTo(tireVendorService.findByName(tireVendorName),TireVendorDto.class);
    }

    @Override
    public void deleteTireVendor(Long tireVendorId) {
        tireVendorService.remove(new TireVendor(tireVendorId));
    }

    @Override
    public void updateTireVendor(TireVendorDto tireVendorDto) {
        tireVendorService.update(beanMappingService.mapTo(tireVendorDto, TireVendor.class));
    }

}
