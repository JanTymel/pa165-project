package cz.muni.fi.pa165.tireservice.dao;

import cz.muni.fi.pa165.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.tireservice.entity.Service;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
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
public class ServiceDaoTestCase extends AbstractTestNGSpringContextTests {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Inject
    private ServiceDao serviceDao;

    private static Service service1;
    private static Service service2;

    @BeforeMethod
    public void setUp() {
        service1 = new Service();
        service1.setName("Engine oil replacement");
        service1.setPrice(new BigDecimal("350.00"));

        service2 = new Service();
        service2.setName("Tire balancing");
        service2.setPrice(new BigDecimal("560.00"));
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testNullName() {
        service1.setName(null);
        serviceDao.create(service1);
    }

    @Test(expectedExceptions = PersistenceException.class)
    public void testTwoServicesWithSameName() {
        service1.setName("Barum");
        service2.setName("Barum");
        serviceDao.create(service1);
        serviceDao.create(service2);
    }

    @Test
    public void testCreateService() {
        assertTrue(serviceDao.findAll().isEmpty());

        serviceDao.create(service1);
        assertEquals(serviceDao.findAll().size(), 1);
    }

    @Test
    public void testUpdateService() {
        serviceDao.create(service1);
        assertEquals("Engine oil replacement", service1.getName());

        service1.setName("Barum");
        service1.setPrice(new BigDecimal("333.00"));
        serviceDao.update(service1);

        Service updatedService = serviceDao.findById(service1.getId());
        assertEquals("Barum", updatedService.getName());
        assertEquals(updatedService.getPrice(), new BigDecimal("333.00"));
    }

    @Test
    public void testRemoveService() {
        serviceDao.create(service1);
        assertEquals(serviceDao.findAll().size(), 1);

        serviceDao.remove(service1);
        assertTrue(serviceDao.findAll().isEmpty());
    }

    @Test
    public void testFindById() {
        serviceDao.create(service1);
        Service found = serviceDao.findById(service1.getId());

        assertEquals(found, service1);
    }

    @Test
    public void testFindByName() {
        serviceDao.create(service1);
        Service found = serviceDao.findByName(service1.getName());

        assertEquals(found, service1);
    }

    @Test
    public void testFindAllServices() {
        serviceDao.create(service1);

        List<Service> foundServices = serviceDao.findAll();
        assertTrue(foundServices.contains(service1));
        assertFalse(foundServices.contains(service2));

        serviceDao.create(service2);

        foundServices = serviceDao.findAll();
        assertTrue(foundServices.contains(service1));
        assertTrue(foundServices.contains(service2));
    }

    @Test
    public void testFindServicesWithPriceBetween() {
        serviceDao.create(service1);
        serviceDao.create(service2);

        BigDecimal thousand = new BigDecimal("1000.00");
        BigDecimal fiveHundred = new BigDecimal("500.00");

        assertEquals(serviceDao.getServicesWithPriceBetween(BigDecimal.ONE, thousand).size(), 2);
        assertEquals(serviceDao.getServicesWithPriceBetween(BigDecimal.ONE, fiveHundred).size(), 1);
        assertEquals(serviceDao.getServicesWithPriceBetween(fiveHundred, thousand).size(), 1);
        assertEquals(serviceDao.getServicesWithPriceBetween(BigDecimal.ONE, BigDecimal.TEN).size(), 0);
    }
}
