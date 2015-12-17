package cz.muni.fi.pa165.tireservice.dao;

import cz.muni.fi.pa165.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.tireservice.entity.TireVendor;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
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
public class TireVendorDaoTestCase extends AbstractTestNGSpringContextTests {

    @Inject
    private TireVendorDao tireVendorDao;

    private static TireVendor vendor1;
    private static TireVendor vendor2;

    @BeforeMethod
    public void setUp() {
        vendor1 = new TireVendor();
        vendor1.setName("Pirelli");

        vendor2 = new TireVendor();
        vendor2.setName("Continental");
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testNullName() {
        vendor1.setName(null);
        tireVendorDao.create(vendor1);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void testTwoVendorsWithSameName() {
        vendor1.setName("Barum");
        vendor2.setName("Barum");
        tireVendorDao.create(vendor1);
        tireVendorDao.create(vendor2);
    }

    @Test
    public void testCreateVendor() {
        assertTrue(tireVendorDao.findAll().isEmpty());

        tireVendorDao.create(vendor1);
        assertEquals(tireVendorDao.findAll().size(), 1);
    }

    @Test
    public void testUpdateVendor() {
        tireVendorDao.create(vendor1);
        assertEquals("Pirelli", vendor1.getName());

        vendor1.setName("Barum");
        tireVendorDao.update(vendor1);

        TireVendor updatedVendor = tireVendorDao.findById(vendor1.getId());
        assertEquals("Barum", updatedVendor.getName());
    }

    @Test
    public void testRemoveVendor() {
        tireVendorDao.create(vendor1);
        assertEquals(tireVendorDao.findAll().size(), 1);

        tireVendorDao.remove(vendor1);
        assertTrue(tireVendorDao.findAll().isEmpty());
    }

    @Test
    public void testFindById() {
        tireVendorDao.create(vendor1);
        TireVendor found = tireVendorDao.findById(vendor1.getId());

        assertEquals(found, vendor1);
    }

    @Test
    public void testFindByName() {
        tireVendorDao.create(vendor1);
        TireVendor found = tireVendorDao.findByName(vendor1.getName());

        assertEquals(found, vendor1);
    }

    @Test
    public void testFindAllTireVendors() {
        tireVendorDao.create(vendor1);

        List<TireVendor> foundTireVendors = tireVendorDao.findAll();
        assertTrue(foundTireVendors.contains(vendor1));
        assertFalse(foundTireVendors.contains(vendor2));

        tireVendorDao.create(vendor2);

        foundTireVendors = tireVendorDao.findAll();
        assertTrue(foundTireVendors.contains(vendor1));
        assertTrue(foundTireVendors.contains(vendor2));
    }

}
