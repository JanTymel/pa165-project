package cz.muni.fi.pa165.tireservice.dao;

import cz.muni.fi.pa165.PersistenceSampleApplicationContext;
import static cz.muni.fi.pa165.tireservice.dao.TireDaoTestCase.createTire;
import static cz.muni.fi.pa165.tireservice.dao.UserDaoTestCase.createUser;
import cz.muni.fi.pa165.tireservice.entity.Order;
import cz.muni.fi.pa165.tireservice.entity.Service;
import cz.muni.fi.pa165.tireservice.entity.Tire;
import cz.muni.fi.pa165.tireservice.entity.TireVendor;
import cz.muni.fi.pa165.tireservice.entity.User;
import cz.muni.fi.pa165.tireservice.enums.CarType;
import cz.muni.fi.pa165.tireservice.enums.OrderState;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Jan Tymel
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class OrderDaoTestCase extends AbstractTestNGSpringContextTests {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Inject
    private OrderDao orderDao;

    @Inject
    private TireVendorDao tireVendorDao;

    @Inject
    private UserDao userDao;

    @Inject
    private ServiceDao serviceDao;

    @Inject
    TireDao tireDao;

    private static User user1;
    private static User user2;
    private static TireVendor vendor1;
    private static TireVendor vendor2;
    private static Tire tire1;
    private static Tire tire2;
    private static Order order1;
    private static Order order2;
    private static Service service1;

    @BeforeMethod
    public void setUp() {
        user1 = createUser("user", "Botanická 68a", "608608608", false);
        user2 = createUser("admin", "123 Fake st", "777666555", false);
        userDao.create(user1);
        userDao.create(user2);

        service1 = new Service();
        service1.setName("Engine oil replacement");
        service1.setPrice(new BigDecimal("350.00"));
        serviceDao.create(service1);

        vendor1 = new TireVendor();
        vendor1.setName("Barum");
        tireVendorDao.create(vendor1);

        vendor2 = new TireVendor();
        vendor2.setName("Continental");
        tireVendorDao.create(vendor2);

        tire1 = createTire("Polaris", 13, 130, new BigDecimal("880.00"), "70T", vendor1, CarType.PASSENGER);
        tire2 = createTire("Brillantis", 14, 120, new BigDecimal("1480.00"), "73T", vendor2, CarType.VAN);
        tireDao.create(tire1);
        tireDao.create(tire2);

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(2015, 1, 1);
        Date date1 = cal.getTime();
        cal.set(2015, 4, 6);
        Date date2 = cal.getTime();

        order1 = createOrder(tire1, service1, OrderState.NEW, user1, date1, CarType.PASSENGER);
        order2 = createOrder(tire2, null, OrderState.NEW, user2, date2, CarType.VAN);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testNullCustomer() {
        order1.setCustomer(null);
        orderDao.create(order1);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testNullCreated() {
        order1.setCreated(null);
        orderDao.create(order1);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testNullOrderState() {
        order1.setState(null);
        orderDao.create(order1);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testNullCarType() {
        order1.setCarType(null);
        orderDao.create(order1);
    }

    @Test
    public void testCreate() {
        assertTrue(orderDao.findAll().isEmpty());

        orderDao.create(order1);
        assertEquals(orderDao.findAll().size(), 1);
    }

    @Test
    public void testRemove() {
        orderDao.create(order1);
        assertEquals(orderDao.findAll().size(), 1);

        orderDao.remove(order1);
        assertTrue(orderDao.findAll().isEmpty());
    }

    @Test
    public void testUpdate() {
        orderDao.create(order1);

        order1.setCarType(CarType.VAN);
        order1.setCustomer(user2);
        order1.setState(OrderState.CANCELLED);

        orderDao.update(order1);

        Order updated = orderDao.findById(order1.getId());
        assertEquals(updated.getCustomer(), order1.getCustomer());
        assertEquals(updated.getCarType(), order1.getCarType());
        assertEquals(updated.getState(), order1.getState());
    }

    @Test
    public void testFindById() {
        orderDao.create(order1);
        Order found = orderDao.findById(order1.getId());

        assertEquals(found, order1);
    }

    @Test
    public void testFindByCarType() {
        orderDao.create(order1);
        List<Order> found = orderDao.findByCarType(order1.getCarType());

        assertEquals(found.size(), 1);
        assertEquals(found.get(0), order1);
    }

    @Test
    public void testFindByState() {
        orderDao.create(order1);
        List<Order> found = orderDao.findByState(order1.getState());

        assertEquals(found.size(), 1);
        assertEquals(found.get(0), order1);
    }

    @Test
    public void testFindByCustomer() {
        orderDao.create(order1);
        List<Order> found = orderDao.findByUser(order1.getCustomer());

        assertEquals(found.size(), 1);
        assertEquals(found.get(0), order1);
    }

    @Test
    public void testGetOrdersBetweenDate() {
        orderDao.create(order1);
        orderDao.create(order2);

        Calendar cal = Calendar.getInstance();
        cal.set(2016, 1, 1);
        Date date1 = cal.getTime();
        cal.set(2016, 4, 5);
        Date date2 = cal.getTime();

        List<Order> found = orderDao.getOrdersCreatedBetween(date1, date2);

        assertEquals(found.size(), 0);

        cal.set(2015, 1, 1, 0, 0, 0);
        Date date3 = cal.getTime();
        cal.set(2015, 5, 7, 0, 0, 0);
        Date date4 = cal.getTime();

        found = orderDao.getOrdersCreatedBetween(date3, date4);

        assertEquals(found.size(), 2);
        assertEquals(found.get(0), order1);
        assertEquals(found.get(1), order2);
    }

    private static Order createOrder(Tire tire, Service service, OrderState state, User customer, Date created, CarType type) {
        Order newOrder = new Order();

        newOrder.setCarType(type);
        newOrder.setCreated(created);
        newOrder.setCustomer(customer);
        newOrder.setState(state);

        newOrder.addTire(tire);

        if (service != null) {
            newOrder.addService(service);
        }

        return newOrder;
    }

    // because of Date bullshit
    private static void compareOrders(Order o1, Order o2) {
        assertEquals(o1.getCarType(), o2.getCarType());
        assertEquals(o1.getCustomer(), o2.getCustomer());
        assertEquals(o1.getServices(), o2.getServices());
        assertEquals(o1.getState(), o2.getState());
        assertEquals(o1.getTires(), o2.getTires());
    }
}
