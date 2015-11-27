/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.tireservice.sevice;


import cz.muni.fi.pa165.tireservice.dao.UserDao;
import cz.muni.fi.pa165.tireservice.entity.User;
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
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

@ContextConfiguration(classes = ServiceConfiguration.class)
public class UserServiceTest extends AbstractTestNGSpringContextTests {
    @Mock
    private UserDao userDao;

    @Autowired
    @InjectMocks
    private UserService userService;

    private User testUser;

    @BeforeMethod
    public void createTires() {
        testUser = new User(1L);
        testUser.setName("Kamil Pochop");
        testUser.setPhone("+420333666999");
        testUser.setAddress("1. Maje 1334, Praha, Ceska Republika");
        testUser.setIsAdmin(false);  
    }

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testCreateUser() {
        userService.create(testUser);
        verify(userDao).create(testUser);
    }
    
    @Test
    public void testDeleteUser() {
        userService.remove(testUser);
        verify(userDao).remove(testUser);
    }
    
    @Test
    public void testUpdateUser() {
        userService.update(testUser);
        verify(userDao).update(testUser);     
    }
    

    @Test
    public void testFindAll() {
        when(userDao.findAll()).thenReturn(new ArrayList<>());
        assertEquals(userService.findAll().size(), 0);
        when(userDao.findAll()).thenReturn(Arrays.asList(testUser));
        assertEquals(userService.findAll().size(), 1);
        User u = new User(2L);
        when(userDao.findAll()).thenReturn(Arrays.asList(testUser, u));
        assertEquals(userService.findAll().size(), 2);
    }
    
    @Test 
    public void testFindByName() {
        String str = testUser.getName();
        findUserByString(str);            
    }

    @Test
    public void testFindByIdNotExisting() {
        when(userDao.findById(Long.MAX_VALUE)).thenReturn(null);
        assertNull(userService.findById(Long.MAX_VALUE));
    }

    @Test
    public void testFindById() {
        when(userDao.findById(testUser.getId())).thenReturn(testUser);
        assertDeepEquals(testUser, userService.findById(testUser.getId()));
    }
    
    @Test 
    public void testFindByPhone() {
        String str = testUser.getPhone();
        findUserByString(str);            
    }

    @Test 
    public void testFindByAddress() {
        String str = testUser.getAddress();
        findUserByString(str);            
    } 
    
    @Test 
    public void testFindAllAdmins() {
        when(userDao.findAllAdmins()).thenReturn(Arrays.asList(testUser));
        List<User> l = userService.findAllAdmins();
        assertEquals(l.size(), 1);
        assertDeepEquals(l.get(0), testUser);
    }
    
    @Test 
    public void testFindAllCustomers() {
        when(userDao.findAllCustomers()).thenReturn(Arrays.asList(testUser));
        List<User> l = userService.findAllCustomers();
        assertEquals(l.size(), 1);
        assertDeepEquals(l.get(0), testUser);    
    }
    
    private void assertDeepEquals(User u1, User u2) {
        assertEquals(u1, u2);
        assertEquals(u1.getId(), u2.getId());
        assertEquals(u1.getName(), u2.getName());
        assertEquals(u1.getPhone(), u2.getPhone());
        assertEquals(u1.getAddress(), u2.getAddress());
        assertEquals(u1.getIsAdmin(), u2.getIsAdmin());
    }
    
    public void findUserByString(String str) {
        when(userDao.findUsersByName(str)).thenReturn(Arrays.asList(testUser));
        List<User> l = userService.findByName(str);
        assertEquals(l.size(), 1);
        assertDeepEquals(l.get(0), testUser);
    }

}

