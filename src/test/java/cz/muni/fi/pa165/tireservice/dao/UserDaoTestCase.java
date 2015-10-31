package cz.muni.fi.pa165.tireservice.dao;

import cz.muni.fi.pa165.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.tireservice.entity.User;
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
public class UserDaoTestCase extends AbstractTestNGSpringContextTests {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Inject
    private UserDao userDao;

    @Test
    public void testCreateAndReadUser() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        User user = createUser("user", "Botanická 68a", "608608608", false);

        userDao.create(user);

        User found = userDao.findById(user.getId());
        assertEquals(user, found);
        em.close();
    }

    @Test
    public void testFindAllUsers() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        User admin = createUser("admin", "123 Fake st", "777666555", true);
        User user = createUser("user", "Botanická 68a", "600601602", false);

        userDao.create(user);

        List<User> foundUsers = userDao.findAll();
        assertTrue(foundUsers.contains(user));
        assertFalse(foundUsers.contains(admin));

        userDao.create(admin);

        foundUsers = userDao.findAll();
        assertTrue(foundUsers.contains(user));
        assertTrue(foundUsers.contains(admin));

        em.close();
    }

    @Test
    public void testFindUserByName() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        User user = createUser("user", "Náměstí svobody 25", "600601602", false);
        userDao.create(user);

        List<User> foundUsers = userDao.findUsersByName("user");
        assertTrue(foundUsers.contains(user));

        foundUsers = userDao.findUsersByName("bad name");
        assertEquals(0, foundUsers.size());

        em.close();
    }

    public static User createUser(String name, String address, String phone, boolean admin) {
        User newUser = new User();
        newUser.setName(name);
        newUser.setIsAdmin(admin);
        newUser.setAddress(address);
        newUser.setPhone(phone);

        return newUser;
    }

}
