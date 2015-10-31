package cz.muni.fi.pa165.tireservice.dao;

import cz.muni.fi.pa165.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.tireservice.entity.TireVendor;
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
public class TireVendorDaoTestCase extends AbstractTestNGSpringContextTests {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Inject
    private TireVendorDao tireVendorDao;

    @Test
    public void testCreateAndReadTireVendor() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        TireVendor tireVendor = new TireVendor();
        tireVendor.setName("Barum");
        tireVendorDao.create(tireVendor);

        TireVendor found = tireVendorDao.findById(tireVendor.getId());

        assertEquals(tireVendor, found);
        em.close();
    }

    @Test
    public void testFindAllTireVendors() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        TireVendor tireVendor = new TireVendor();
        tireVendor.setName("Continental");

        TireVendor anotherVendor = new TireVendor();
        anotherVendor.setName("Dunlop");

        tireVendorDao.create(tireVendor);

        List<TireVendor> foundTireVendors = tireVendorDao.findAll();
        assertTrue(foundTireVendors.contains(tireVendor));
        assertFalse(foundTireVendors.contains(anotherVendor));

        tireVendorDao.create(anotherVendor);

        foundTireVendors = tireVendorDao.findAll();
        assertTrue(foundTireVendors.contains(tireVendor));
        assertTrue(foundTireVendors.contains(anotherVendor));

        em.close();
    }

}
