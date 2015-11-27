/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.tireservice.sevice;

import cz.muni.fi.pa165.tireservice.dao.ServiceDao;
import cz.muni.fi.pa165.tireservice.entity.Service;
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

import java.math.BigDecimal;;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

@ContextConfiguration(classes = ServiceConfiguration.class)
public class ServiceServiceTest extends AbstractTestNGSpringContextTests {
    @Mock
    private ServiceDao serviceDao;

    @Autowired
    @InjectMocks
    private ServiceService serviceService;

    private Service testService;

    @BeforeMethod
    public void createServices() {
        testService = new Service(1L);
        testService.setName("Konrola sbihavosti");
        testService.setPrice(new BigDecimal(600));
    }

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testCreate() {
        serviceService.create(testService);
        verify(serviceDao).create(testService);
    }
    
    @Test
    public void testRemove() {
        serviceService.remove(testService);
        verify(serviceDao).remove(testService);
    }
    
    @Test
    public void testUpdate() {
        serviceService.update(testService);
        verify(serviceDao).update(testService);
    }

    @Test
    public void testFindAll() {
        when(serviceDao.findAll()).thenReturn(new ArrayList<>());
        assertEquals(serviceService.findAll().size(), 0);
        when(serviceDao.findAll()).thenReturn(Arrays.asList(testService));
        assertEquals(serviceService.findAll().size(), 1);
        Service s = new Service(2L);
        s.setName("Bezna kontrola");
        s.setPrice(new BigDecimal(400));
        when(serviceDao.findAll()).thenReturn(Arrays.asList(testService, s));
        assertEquals(serviceService.findAll().size(), 2);
    }
    
    @Test
    public void testFindByIdNotExisting() {
        when(serviceDao.findById(Long.MAX_VALUE)).thenReturn(null);
        assertNull(serviceService.findById(Long.MAX_VALUE));
    }

    @Test
    public void testFindById() {
        when(serviceDao.findById(testService.getId())).thenReturn(testService);
        assertDeepEquals(testService, serviceService.findById(testService.getId()));
    }
    
    @Test 
    public void testFindByName() {
        String str = testService.getName();
        when(serviceDao.findByName(str)).thenReturn(testService);
        assertDeepEquals(serviceService.findByName(str), testService);             
    }
    
    @Test
    public void testGetServicesWithPriceBetween() {
        BigDecimal low = new BigDecimal(400);
        BigDecimal high = new BigDecimal(700);
        
        when(serviceDao.getServicesWithPriceBetween(low, high)).thenReturn(Arrays.asList(testService));
        List<Service> l = serviceService.getServicesWithPriceBetween(low, high);
        assertEquals(l.size(), 1);
        assertDeepEquals(l.get(0), testService);  
    }
    private void assertDeepEquals(Service s1, Service s2) {
        assertEquals(s1, s2);
        assertEquals(s1.getId(), s2.getId());
        assertEquals(s1.getPrice(), s2.getPrice());
        assertEquals(s1.getName(), s2.getName());

    }

}

