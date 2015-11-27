/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.tireservice.sevice;

import cz.muni.fi.pa165.tireservice.entity.Tire;
import cz.muni.fi.pa165.tireservice.entity.TireVendor;
import cz.muni.fi.pa165.tireservice.enums.CarType;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Samuel Baniar
 */
@Service
public interface TireService {
    
    public void createTire(Tire tire);
    
    public void deleteTire(Tire tire);
    
    public Tire findById(Long id);
    
    public List<Tire> findAll();
    
    public List<Tire> findByName(String namePattern);
    
    public List<Tire> findByCarType(CarType carType);
    
    public List<Tire> findByDiameter(int d);
    
    public List<Tire> findByWidth(int w);
    
    public List<Tire> findByTireVendor(TireVendor tv);
    
    public List<Tire> findBySpeedIndex(String si);
    
    public List<Tire> getTiresWithPriceBetween(BigDecimal leftLimit, BigDecimal rightLimit);    
    
    public void changePrice(Tire tire, BigDecimal newPrice);
}
