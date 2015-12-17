package cz.muni.fi.pa165.tireservice.dao;

import cz.muni.fi.pa165.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.tireservice.entity.User;
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
public class UserDaoTestCase extends AbstractTestNGSpringContextTests {

    @Inject
    private UserDao userDao;

    private static User user1;
    private static User user2;

    @BeforeMethod
    public void setUp() {
        user1 = createUser("user", "Botanická 68a", "608608608", false);
        user2 = createUser("admin", "123 Fake st", "777666555", true);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testNullName() {
        user1.setName(null);
        userDao.create(user1);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testNullAddress() {
        user1.setAddress(null);
        userDao.create(user1);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testNullPhone() {
        user1.setPhone(null);
        userDao.create(user1);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testNullAdmin() {
        user1.setIsAdmin(null);
        userDao.create(user1);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void testTwoUsersWithSamePhone() {
        user1.setPhone("603563981");
        user2.setPhone("603563981");
        userDao.create(user1);
        userDao.create(user2);
    }

    @Test
    public void testCreateUser() {
        assertTrue(userDao.findAll().isEmpty());

        userDao.create(user1);
        assertEquals(userDao.findAll().size(), 1);
    }

    @Test
    public void testUpdateUser() {
        userDao.create(user1);
        assertEquals("user", user1.getName());

        user1.setName("new user");
        user1.setPhone("605606607");
        user1.setIsAdmin(true);
        user1.setAddress("123 Fake street");
        userDao.update(user1);

        User updatedUser = userDao.findById(user1.getId());
        assertEquals(updatedUser.getName(), "new user");
        assertTrue(updatedUser.getIsAdmin());
        assertEquals(updatedUser.getPhone(), "605606607");
        assertEquals(updatedUser.getAddress(), "123 Fake street");
    }

    @Test
    public void testRemoveUser() {
        userDao.create(user1);
        assertEquals(userDao.findAll().size(), 1);

        userDao.remove(user1);
        assertTrue(userDao.findAll().isEmpty());
    }

    @Test
    public void testFindById() {
        userDao.create(user1);
        User found = userDao.findById(user1.getId());

        assertEquals(found, user1);
    }

    @Test
    public void testFindByPhone() {
        userDao.create(user1);
        User found = userDao.findByPhone(user1.getPhone());

        assertEquals(found, user1);
    }

    @Test
    public void testFindByName() {
        userDao.create(user1);
        List<User> found = userDao.findByName(user1.getName());

        assertEquals(found.get(0), user1);
    }

    @Test
    public void testFindByAddress() {
        userDao.create(user1);
        List<User> found = userDao.findByAddress(user1.getAddress());

        assertEquals(found.get(0), user1);
    }

    @Test
    public void testFindCustomers() {
        userDao.create(user1);
        userDao.create(user2);

        List<User> found = userDao.findAllCustomers();
        assertEquals(found.size(), 1);
        assertEquals(found.get(0), user1);
    }

    @Test
    public void testFindAdmins() {
        userDao.create(user1);
        userDao.create(user2);

        List<User> found = userDao.findAllAdmins();
        assertEquals(found.size(), 1);
        assertEquals(found.get(0), user2);
    }

    @Test
    public void testFindAllUsers() {
        userDao.create(user1);

        List<User> foundUsers = userDao.findAll();
        assertTrue(foundUsers.contains(user1));
        assertFalse(foundUsers.contains(user2));

        userDao.create(user2);

        foundUsers = userDao.findAll();
        assertTrue(foundUsers.contains(user1));
        assertTrue(foundUsers.contains(user2));
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
