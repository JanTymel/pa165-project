package cz.muni.fi.pa165.tireservice.dao;

import cz.muni.fi.pa165.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.tireservice.entity.Tire;
import cz.muni.fi.pa165.tireservice.entity.TireVendor;
import cz.muni.fi.pa165.tireservice.enums.CarType;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;

/**
 *
 * @author Jan Tymel
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
public class TireDaoTestCase extends AbstractTestNGSpringContextTests {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Inject
    private TireDao tireDao;

    @Inject
    private TireVendorDao tireVendorDao;

    @Test
    public void testCreateAndReadTire() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        TireVendor vendor = new TireVendor();
        vendor.setName("Barum");
        tireVendorDao.create(vendor);

        Tire tire = createTire("Polaris", 13, 130, new BigDecimal("880.00"), "70T", vendor, CarType.PASSENGER);

        tireDao.create(tire);

        Tire found = tireDao.findById(tire.getId());
        assertEquals(tire, found);
        em.close();
    }

    @Test
    public void testFindAllTires() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        TireVendor vendor = new TireVendor();
        vendor.setName("Barum");
        tireVendorDao.create(vendor);

        Tire tire = createTire("Bravuris", 13, 130, new BigDecimal("880.00"), "70T", vendor, CarType.PASSENGER);
        Tire anotherTire = createTire("Brillantis", 13, 130, new BigDecimal("1480.00"), "70T", vendor, CarType.PASSENGER);

        tireDao.create(tire);

        List<Tire> foundTires = tireDao.findAll();
        assertTrue(foundTires.contains(tire));
        assertFalse(foundTires.contains(anotherTire));

        tireDao.create(anotherTire);

        foundTires = tireDao.findAll();
        assertTrue(foundTires.contains(tire));
        assertTrue(foundTires.contains(anotherTire));

        em.close();
    }

    public static Tire createTire(String name, int diameter, int width, BigDecimal price, String speedIndex, TireVendor vendor, CarType type) {
        Tire newTire = new Tire();

        newTire.setName(name);
        newTire.setWidth(width);
        newTire.setPrice(price);
        newTire.setSpeedIndex(speedIndex);
        newTire.setTireVendor(vendor);
        newTire.setCarType(type);
        newTire.setDiameter(diameter);

        return newTire;
    }

}
