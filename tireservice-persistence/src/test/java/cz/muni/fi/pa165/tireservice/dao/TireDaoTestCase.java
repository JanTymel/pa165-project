package cz.muni.fi.pa165.tireservice.dao;

import cz.muni.fi.pa165.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.tireservice.entity.Tire;
import cz.muni.fi.pa165.tireservice.entity.TireVendor;
import cz.muni.fi.pa165.tireservice.enums.CarType;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;
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
public class TireDaoTestCase extends AbstractTestNGSpringContextTests {

    @Inject
    private TireDao tireDao;

    @Inject
    private TireVendorDao tireVendorDao;

    private static TireVendor vendor1;
    private static TireVendor vendor2;
    private static Tire tire1;
    private static Tire tire2;

    @BeforeMethod
    public void setUp() {
        vendor1 = new TireVendor();
        vendor1.setName("Barum");
        tireVendorDao.create(vendor1);

        vendor2 = new TireVendor();
        vendor2.setName("Continental");
        tireVendorDao.create(vendor2);

        tire1 = createTire("Polaris", 13, 130, new BigDecimal("880.00"), "70T", vendor1, CarType.PASSENGER);
        tire2 = createTire("Brillantis", 14, 120, new BigDecimal("1480.00"), "73T", vendor2, CarType.VAN);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testNullName() {
        tire1.setName(null);
        tireDao.create(tire1);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testNullCarType() {
        tire1.setCarType(null);
        tireDao.create(tire1);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testNullSpeedIndex() {
        tire1.setSpeedIndex(null);
        tireDao.create(tire1);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testNullPrice() {
        tire1.setPrice(null);
        tireDao.create(tire1);
    }

    @Test
    public void testCreate() {
        assertTrue(tireDao.findAll().isEmpty());

        tireDao.create(tire1);
        assertEquals(tireDao.findAll().size(), 1);
    }

    @Test
    public void testUpdate() {
        tireDao.create(tire1);
        assertEquals("Polaris", tire1.getName());

        tire1.setName("Brillantis");
        tire1.setCarType(CarType.VAN);
        tire1.setDiameter(15);
        tire1.setWidth(140);
        tire1.setPrice(new BigDecimal("1330.00"));
        tire1.setSpeedIndex("75T");
        tireDao.update(tire1);

        Tire updated = tireDao.findById(tire1.getId());
        assertEquals(updated.getName(), tire1.getName());
        assertEquals(updated.getCarType(), tire1.getCarType());
        assertEquals(updated.getDiameter(), tire1.getDiameter());
        assertEquals(updated.getWidth(), tire1.getWidth());
        assertEquals(updated.getPrice(), tire1.getPrice());
        assertEquals(updated.getSpeedIndex(), tire1.getSpeedIndex());

    }

    @Test
    public void testRemove() {
        tireDao.create(tire1);
        assertEquals(tireDao.findAll().size(), 1);

        tireDao.remove(tire1);
        assertTrue(tireDao.findAll().isEmpty());
    }

    @Test
    public void testFindById() {
        tireDao.create(tire1);
        Tire found = tireDao.findById(tire1.getId());

        assertEquals(found, tire1);
    }

    @Test
    public void testFindByName() {
        tireDao.create(tire1);
        List<Tire> found = tireDao.findByName(tire1.getName());

        assertEquals(found.get(0), tire1);
    }

    @Test
    public void testFindByCarType() {
        tireDao.create(tire1);
        List<Tire> found = tireDao.findByCarType(CarType.PASSENGER);

        assertEquals(found.get(0), tire1);
    }

    @Test
    public void testFindByDiameter() {
        tireDao.create(tire1);
        List<Tire> found = tireDao.findByDiameter(13);

        assertEquals(found.get(0), tire1);
    }

    @Test
    public void testFindByWidth() {
        tireDao.create(tire1);
        List<Tire> found = tireDao.findByWidth(130);

        assertEquals(found.get(0), tire1);
    }

    @Test
    public void testFindByTireVendor() {
        tireDao.create(tire1);
        List<Tire> found = tireDao.findByTireVendor(vendor1);

        assertEquals(found.get(0), tire1);
    }

    @Test
    public void testFindBySpeedIndex() {
        tireDao.create(tire1);
        List<Tire> found = tireDao.findBySpeedIndex("70T");

        assertEquals(found.get(0), tire1);
    }

    @Test
    public void testFindServicesWithPriceBetween() {
        tireDao.create(tire1);
        tireDao.create(tire2);

        BigDecimal thousand = new BigDecimal("1000.00");
        BigDecimal twoThousand = new BigDecimal("2000.00");

        assertEquals(tireDao.getTiresWithPriceBetween(BigDecimal.ONE, twoThousand).size(), 2);
        assertEquals(tireDao.getTiresWithPriceBetween(BigDecimal.ONE, thousand).size(), 1);
        assertEquals(tireDao.getTiresWithPriceBetween(thousand, twoThousand).size(), 1);
        assertEquals(tireDao.getTiresWithPriceBetween(BigDecimal.ONE, BigDecimal.TEN).size(), 0);
    }

    @Test
    public void testFindAllTires() {
        tireDao.create(tire1);

        List<Tire> foundTires = tireDao.findAll();
        assertTrue(foundTires.contains(tire1));
        assertFalse(foundTires.contains(tire2));

        tireDao.create(tire2);

        foundTires = tireDao.findAll();
        assertTrue(foundTires.contains(tire1));
        assertTrue(foundTires.contains(tire2));
    }

    public static Tire createTire(String name, int diameter, int width, BigDecimal price, String speedIndex, TireVendor tireVendor, CarType type) {
        Tire newTire = new Tire();

        newTire.setName(name);
        newTire.setWidth(width);
        newTire.setPrice(price);
        newTire.setSpeedIndex(speedIndex);
        newTire.setTireVendor(tireVendor);
        newTire.setCarType(type);
        newTire.setDiameter(diameter);

        return newTire;
    }

}
