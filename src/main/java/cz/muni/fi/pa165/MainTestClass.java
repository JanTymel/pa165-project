package cz.muni.fi.pa165;

import cz.muni.fi.pa165.tireservice.entity.TireVendor;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.Assert;

/**
 *
 * @author Samuel Baniar
 */
public class MainTestClass{
    private static EntityManagerFactory emf;
    
    public static void main(String[] args) throws SQLException {
		// The following line is here just to start up a in-memory database
		new AnnotationConfigApplicationContext(InMemoryDatabaseSpring.class);

		emf = Persistence.createEntityManagerFactory("default");

		// BEGIN YOUR CODE
		TireVendorCreateTest();
		// END YOUR CODE
		emf.close();
    }
    
    
    public static void TireVendorCreateTest(){
        EntityManager em = emf.createEntityManager();
        
        TireVendor tv = new TireVendor();
        tv.setName("Michelin");
        
        em.getTransaction().begin();
        em.persist(tv);
        em.getTransaction().commit();
        em.close();
        
        em = emf.createEntityManager();
        TireVendor found = em.find(TireVendor.class, tv.getId());
        Assert.assertEquals(found.getName(), "Michelin");
        em.close();       
    }
}
