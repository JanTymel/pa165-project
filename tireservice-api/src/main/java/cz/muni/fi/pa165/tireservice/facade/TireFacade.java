package cz.muni.fi.pa165.tireservice.facade;

import cz.muni.fi.pa165.tireservice.dto.TireDto;
import cz.muni.fi.pa165.tireservice.dto.TireVendorDto;
import cz.muni.fi.pa165.tireservice.enums.CarType;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Samuel Baniar
 */
public interface TireFacade {
    public Long createTire(TireDto t);
    public void deleteTire(Long productId);
    public TireDto getTireWithId(Long id);
    public List<TireDto> getAllTires();
    public List<TireDto> getTiresByName(String namePattern);
    public List<TireDto> getTiresByCarType(CarType carType);
    public List<TireDto> getTiresByDiameter(int diameter);
    public List<TireDto> getTiresByWidth(int width);
    public List<TireDto> getTiresByTireVendor(TireVendorDto vendor);
    public List<TireDto> getTiresBySpeedIndex(String speedIndex);
    public List<TireDto> getTiresWithPriceBetween(BigDecimal leftLimit, BigDecimal rightLimit);
    public void changePrice(Long id, BigDecimal newPrice);    
}
