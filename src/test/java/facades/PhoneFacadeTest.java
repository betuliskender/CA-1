package facades;

import entities.Hobby;
import entities.Phone;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class PhoneFacadeTest
{
    private static EntityManagerFactory emf;
    private static HobbyFacade facade;

    Phone h1, h2;

    public PhoneFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = PhoneFacade.getInstance(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the code below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            //em.createNamedQuery("RenameMe.deleteAllRows").executeUpdate();
            em.createQuery("DELETE From Hobby").executeUpdate();
            em.createNativeQuery("ALTER TABLE Hobby AUTO_INCREMENT = 1").executeUpdate();
            h1 = new Hobby("Strikning", "Kun gamle damer strikker");
            h2 = new Hobby("Gaming", "Spil WOW med Morten");
            em.persist(h1);
            em.persist(h2);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }
}
