/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.tireservice.sevice;

import cz.muni.fi.pa165.tireservice.dao.OrderDao;
import cz.muni.fi.pa165.tireservice.entity.Order;
import cz.muni.fi.pa165.tireservice.entity.Service;
import cz.muni.fi.pa165.tireservice.entity.Tire;
import cz.muni.fi.pa165.tireservice.entity.User;
import cz.muni.fi.pa165.tireservice.enums.CarType;
import cz.muni.fi.pa165.tireservice.enums.OrderState;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

@ContextConfiguration(classes = ServiceConfiguration.class)
public class OrderServiceTest extends AbstractTestNGSpringContextTests {
    @Mock
    private OrderDao orderDao;

    @Autowired
    @InjectMocks
    private OrderService orderService;

    private Order testOrder;

    @BeforeMethod
    public void createOrders() {
        testOrder = new Order(1L);
        testOrder.setCarType(CarType.TRUCK);
        Date date = new Date();
        testOrder.setCreated(date);
        User customer = new User();
        customer.setName("Tomas Matny");
        testOrder.setCustomer(customer);
        testOrder.setState(OrderState.NEW);
    }

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testCreateOrder() {
        orderService.createOrder(testOrder);
        verify(orderDao).create(testOrder);
    }

    @Test
    public void testFindAll() {
        when(orderDao.findAll()).thenReturn(new ArrayList<>());
        assertEquals(orderService.findAll().size(), 0);
        when(orderDao.findAll()).thenReturn(Arrays.asList(testOrder));
        assertEquals(orderService.findAll().size(), 1);
        Order o = new Order(2L);
        o.setCarType(CarType.TRUCK);
        Date date = new Date();
        o.setCreated(date);
        User customer = new User();
        customer.setName("Karel Vojacek");
        o.setCustomer(customer);
        o.setState(OrderState.NEW);
        when(orderDao.findAll()).thenReturn(Arrays.asList(testOrder, o));
        assertEquals(orderService.findAll().size(), 2);
    }
    
    @Test
    public void testFindByUser(){
        User customer = testOrder.getCustomer();
        when(orderDao.findByUser(customer)).thenReturn(Arrays.asList(testOrder));
        List<Order> l = orderService.findByUser(customer);
        assertEquals(l.size(), 1);
        assertDeepEquals(l.get(0), testOrder);         
    }
    
    @Test 
    public void testFindByState() {
        OrderState os = testOrder.getState();
        when(orderDao.findByState(os)).thenReturn(Arrays.asList(testOrder));
        List<Order> l = orderService.findByState(os);
        assertEquals(l.size(), 1);
        assertDeepEquals(l.get(0), testOrder);        
        
    }
    
    @Test
    public void testFindByIdNotExisting() {
        when(orderDao.findById(Long.MAX_VALUE)).thenReturn(null);
        assertNull(orderService.findById(Long.MAX_VALUE));
    }

    @Test
    public void testFindById() {
        when(orderDao.findById(testOrder.getId())).thenReturn(testOrder);
        assertDeepEquals(testOrder, orderService.findById(testOrder.getId()));
    }
    
    @Test 
    public void testFindByCarType() {
        CarType ct = testOrder.getCarType();
        when(orderDao.findByCarType(ct)).thenReturn(Arrays.asList(testOrder));
        List<Order> l = orderService.findByCarType(ct);
        assertEquals(l.size(), 1);
        assertDeepEquals(l.get(0), testOrder);        
        
    }
    
    @Test
    public void testGetOrdersCreatedBetween() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date from_date = format.parse("2015/01/01 00:00:00"); 
        Date to_date = format.parse("2016/01/01 00:00:00"); 
        
        when(orderDao.getOrdersCreatedBetween(from_date, to_date)).thenReturn(Arrays.asList(testOrder));
        List<Order> l = orderService.getOrdersCreatedBetween(from_date, to_date);
        assertEquals(l.size(), 1);
        assertDeepEquals(l.get(0), testOrder);  
    }

    @Test
    public void testStartProcessingOrder(){
        orderService.startProcessingOrder(testOrder);
        assertEquals(testOrder.getState(), OrderState.IN_PROGRESS);
    }
    
    @Test
    public void testFinishOrder(){
        orderService.finishOrder(testOrder);
        assertEquals(testOrder.getState(), OrderState.COMPLETED);
    }
    
    @Test
    public void testCancelOrder(){
        orderService.cancelOrder(testOrder);
        assertEquals(testOrder.getState(), OrderState.CANCELLED);
    }
    
    @Test
    public void testGetOrderTotalPrice() {       
        Tire tire = new Tire();
        int tirePrice = 1000;
        tire.setPrice(new BigDecimal(tirePrice));
        
        
        Service s = new Service();
        int servicePrice = 600;
        s.setPrice(new BigDecimal(servicePrice));
        
        Order order = new Order(10L);
        order.addTire(tire);
        order.addTire(tire);
        order.addTire(tire);
        order.addTire(tire);
        order.addService(s);
        
        when(orderDao.findById(order.getId())).thenReturn(order);
        BigDecimal totalPrice = orderService.getOrderTotalPrice(order.getId());
        BigDecimal expectedPrice = new BigDecimal(4*tirePrice + servicePrice);
        
        assertEquals(totalPrice, expectedPrice);  
    }

    private void assertDeepEquals(Order o1, Order o2) {
        assertEquals(o1, o2);
        assertEquals(o1.getId(), o2.getId());
        assertEquals(o1.getCarType(), o2.getCarType());
        assertEquals(o1.getCustomer().getName(), o2.getCustomer().getName());
        assertEquals(o1.getState(), o2.getState());
        assertEquals(o1.getCreated(), o2.getCreated());
    }
}

