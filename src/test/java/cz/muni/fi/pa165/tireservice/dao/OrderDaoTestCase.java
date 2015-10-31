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
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

/**
 *
 * @author Jan Tymel
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
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
    TireDao tireDao;

    @Test
    public void testCreateAndReadOrder() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        TireVendor vendor = new TireVendor();
        vendor.setName("Vredestein");
        tireVendorDao.create(vendor);

        Tire tire = createTire("Brillantis", 13, 130, new BigDecimal("1480.00"), "70T", vendor, CarType.PASSENGER);
        tireDao.create(tire);

        User user = createUser("user", "Božetěchova 1/2", "600601602", false);
        userDao.create(user);

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        Date created = new Date();

        Order order = createOrder(tire, null, OrderState.NEW, user, cal.getTime(), CarType.PASSENGER);

        orderDao.create(order);

        Order found = orderDao.findById(tire.getId());
        compareOrders(order, found);
        em.close();
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
