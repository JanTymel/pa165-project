/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.tireservice.sevice;


import cz.muni.fi.pa165.tireservice.dao.TireVendorDao;
import cz.muni.fi.pa165.tireservice.entity.TireVendor;
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

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

@ContextConfiguration(classes = ServiceConfiguration.class)
public class TireVendorServiceTest extends AbstractTestNGSpringContextTests {
    @Mock
    private TireVendorDao tireVendorDao;

    @Autowired
    @InjectMocks
    private TireVendorService tireVendorService;

    private TireVendor testTireVendor;

    @BeforeMethod
    public void createTires() {
        testTireVendor = new TireVendor(1L);
        testTireVendor.setName("DUNLOP");
        
    }

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testCreateTireVendor() {
        tireVendorService.create(testTireVendor);
        verify(tireVendorDao).create(testTireVendor);
    }
    
    @Test
    public void testDeleteTireVendor() {
        tireVendorService.remove(testTireVendor);
        verify(tireVendorDao).remove(testTireVendor);
    }
    
    @Test
    public void testUpdateTireVendro() {
        tireVendorService.update(testTireVendor);
        verify(tireVendorDao).update(testTireVendor);     
    }
    

    @Test
    public void testFindAll() {
        when(tireVendorDao.findAll()).thenReturn(new ArrayList<>());
        assertEquals(tireVendorService.findAll().size(), 0);
        when(tireVendorDao.findAll()).thenReturn(Arrays.asList(testTireVendor));
        assertEquals(tireVendorService.findAll().size(), 1);
        TireVendor t = new TireVendor(2L);
        when(tireVendorDao.findAll()).thenReturn(Arrays.asList(testTireVendor, t));
        assertEquals(tireVendorService.findAll().size(), 2);
    }
    
    @Test 
    public void testFindByName() {
        String str = testTireVendor.getName();
        when(tireVendorDao.findByName(str)).thenReturn(testTireVendor);
        assertDeepEquals(tireVendorService.findByName(str), testTireVendor);             
    }

    @Test
    public void testFindByIdNotExisting() {
        when(tireVendorDao.findById(Long.MAX_VALUE)).thenReturn(null);
        assertNull(tireVendorService.findById(Long.MAX_VALUE));
    }

    @Test
    public void testFindById() {
        when(tireVendorDao.findById(testTireVendor.getId())).thenReturn(testTireVendor);
        assertDeepEquals(testTireVendor, tireVendorService.findById(testTireVendor.getId()));
    }
    
    private void assertDeepEquals(TireVendor t1, TireVendor t2) {
        assertEquals(t1, t2);
        assertEquals(t1.getId(), t2.getId());
        assertEquals(t1.getName(), t2.getName());
    }

}

