package cz.muni.fi.pa165.tireservice.dao;

import cz.muni.fi.pa165.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.tireservice.entity.Service;
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
public class ServiceDaoTestCase extends AbstractTestNGSpringContextTests {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Inject
    private ServiceDao serviceDao;

    @Test
    public void testCreateAndReadService() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Service service = new Service();
        service.setName("kontrola sbihavosti");
        service.setPrice(new BigDecimal("700.00"));

        serviceDao.create(service);

        Service found = serviceDao.findById(service.getId());
        assertEquals(service, found);
        em.close();
    }

    @Test
    public void testFindAllServices() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Service service = new Service();
        service.setName("vycentrovani kol");
        service.setPrice(new BigDecimal("550.00"));

        Service anotherService = new Service();
        anotherService.setName("ekologicka likvidace pneumatik");
        anotherService.setPrice(new BigDecimal("160.00"));

        serviceDao.create(service);

        List<Service> foundServices = serviceDao.findAll();
        assertTrue(foundServices.contains(service));
        assertFalse(foundServices.contains(anotherService));

        serviceDao.create(anotherService);

        foundServices = serviceDao.findAll();
        assertTrue(foundServices.contains(service));
        assertTrue(foundServices.contains(anotherService));

        em.close();
    }

}
