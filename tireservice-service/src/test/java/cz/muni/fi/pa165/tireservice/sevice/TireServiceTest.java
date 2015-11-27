/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.tireservice.sevice;

import cz.muni.fi.pa165.tireservice.dao.TireDao;
import cz.muni.fi.pa165.tireservice.entity.Tire;
import cz.muni.fi.pa165.tireservice.entity.TireVendor;
import cz.muni.fi.pa165.tireservice.enums.CarType;
import cz.muni.fi.pa165.tireservice.service.config.ServiceConfiguration;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

@ContextConfiguration(classes = ServiceConfiguration.class)
public class TireServiceTest extends AbstractTestNGSpringContextTests {
    @Mock
    private TireDao tireDao;

    @Autowired
    @InjectMocks
    private TireService tireService;

    private Tire testTire;

    @BeforeMethod
    public void createTires() {
        testTire = new Tire(1L);
        testTire.setCarType(CarType.TRUCK);
        testTire.setName("nice Tires");
        testTire.setWidth(20);
        testTire.setDiameter(50);
        testTire.setPrice(new BigDecimal(1000));
        testTire.setSpeedIndex("160km/h");
        TireVendor tireVendor = new TireVendor(1L);
        testTire.setTireVendor(tireVendor);
    }

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testCreateTire() {
        tireService.createTire(testTire);
        verify(tireDao).create(testTire);
    }
    
    @Test
    public void testDeleteTire() {
        tireService.deleteTire(testTire);
        verify(tireDao).remove(testTire);
    }

    @Test
    public void testFindAll() {
        when(tireDao.findAll()).thenReturn(new ArrayList<>());
        assertEquals(tireService.findAll().size(), 0);
        when(tireDao.findAll()).thenReturn(Arrays.asList(testTire));
        assertEquals(tireService.findAll().size(), 1);
        Tire t = new Tire(2L);
        when(tireDao.findAll()).thenReturn(Arrays.asList(testTire, t));
        assertEquals(tireService.findAll().size(), 2);
    }
    
    @Test 
    public void testFindByName() {
        String str = testTire.getName();
        when(tireDao.findByName(str)).thenReturn(Arrays.asList(testTire));
        List<Tire> l = tireService.findByName(str);
        assertEquals(l.size(), 1);
        assertDeepEquals(l.get(0), testTire);             
    }
    
    @Test 
    public void testFindByDiameter() {
        int d = testTire.getDiameter();
        when(tireDao.findByDiameter(d)).thenReturn(Arrays.asList(testTire));
        List<Tire> l = tireService.findByDiameter(d);
        assertEquals(l.size(), 1);
        assertDeepEquals(l.get(0), testTire);             
    }
    
    @Test 
    public void testFindByWidth() {
        int w = testTire.getWidth();
        when(tireDao.findByWidth(w)).thenReturn(Arrays.asList(testTire));
        List<Tire> l = tireService.findByWidth(w);
        assertEquals(l.size(), 1);
        assertDeepEquals(l.get(0), testTire);             
    }
    
    @Test 
    public void testFindBySpeedIndex() {
        String si = testTire.getSpeedIndex();
        when(tireDao.findBySpeedIndex(si)).thenReturn(Arrays.asList(testTire));
        List<Tire> l = tireService.findBySpeedIndex(si);
        assertEquals(l.size(), 1);
        assertDeepEquals(l.get(0), testTire);             
    }
    
    @Test
    public void testFindByIdNotExisting() {
        when(tireDao.findById(Long.MAX_VALUE)).thenReturn(null);
        assertNull(tireService.findById(Long.MAX_VALUE));
    }

    @Test
    public void testFindById() {
        when(tireDao.findById(testTire.getId())).thenReturn(testTire);
        assertDeepEquals(testTire, tireService.findById(testTire.getId()));
    }
    
    @Test 
    public void testFindByCarType() {
        CarType ct = testTire.getCarType();
        when(tireDao.findByCarType(ct)).thenReturn(Arrays.asList(testTire));
        List<Tire> l = tireService.findByCarType(ct);
        assertEquals(l.size(), 1);
        assertDeepEquals(l.get(0), testTire);        
        
    }
    
    @Test
    public void testGetTiresWithPriceBetween() {
        BigDecimal low = new BigDecimal(600);
        BigDecimal high = new BigDecimal(1100);
        
        when(tireDao.getTiresWithPriceBetween(low, high)).thenReturn(Arrays.asList(testTire));
        List<Tire> l = tireService.getTiresWithPriceBetween(low, high);
        assertEquals(l.size(), 1);
        assertDeepEquals(l.get(0), testTire); 
    }

    @Test
    public void testChangePrice(){
        tireService.changePrice(testTire, new BigDecimal(1200));
        assertEquals(testTire.getPrice(), new BigDecimal(1200));
    }

    private void assertDeepEquals(Tire t1, Tire t2) {
        assertEquals(t1, t2);
        assertEquals(t1.getId(), t2.getId());
        assertEquals(t1.getCarType(), t2.getCarType());
        assertEquals(t1.getName(), t2.getName());
        assertEquals(t1.getPrice(), t2.getPrice());
        assertEquals(t1.getSpeedIndex(), t2.getSpeedIndex());
        assertEquals(t1.getDiameter(), t2.getDiameter());
        assertEquals(t1.getWidth(), t2.getWidth());
        assertEquals(t1.getTireVendor().getId(), t2.getTireVendor().getId());
    }

}

