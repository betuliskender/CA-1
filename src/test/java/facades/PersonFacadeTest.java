package facades;

import entities.Address;
import entities.CityInfo;
import entities.Person;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

import static io.restassured.RestAssured.given;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class PersonFacadeTest {

    private static EntityManagerFactory emf;
    private static PersonFacade facade;

    private static Person p1, p2, p3;
    private static CityInfo c1, c2, c3;
    private static Address a1, a2, a3;


    public PersonFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = PersonFacade.getInstance(emf);
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
        c1 = new CityInfo("2300","Amager");
        c2 = new CityInfo("2400","Nordvest");
        c3 = new CityInfo("2100","Østerbro");

        a1 = new Address("Amagerbrogade 12","3. TV",c1);
        a2 = new Address("Stærevej 6","1.TH",c2);
        a3 = new Address("Østerbrogade 271","ST, MF",c3);

        p1 = new Person("Morten@B.dk", "Morten", "Bendeke",a1);
        p2 = new Person("Denis@P.dk", "Denis", "Pedersen",a2);
        p3 = new Person("Betül@I.dk", "Betül", "Iskender", a3);
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
            em.persist(c1);
            em.persist(c2);
            em.persist(c3);
            em.persist(a1);
            em.persist(a2);
            em.persist(a3);
            em.persist(p1);
            em.persist(p2);
            em.persist(p3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    @Test
    public void getAllPersons() throws Exception {
        List<Person> personList = facade.getAll();

        int expected = 3;

        assertEquals(expected,personList.size() );
    }

}
