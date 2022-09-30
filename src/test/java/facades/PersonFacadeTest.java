package facades;

import entities.Address;
import entities.CityInfo;
import entities.Person;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class PersonFacadeTest {

    private static EntityManagerFactory emf;
    private static PersonFacade facade;

    private static Person p1, p2, p3;
//    private static CityInfo c1, c2, c3;
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

        try {
            em.getTransaction().begin();

            em.createQuery("DELETE From Person").executeUpdate();
            em.createNativeQuery("ALTER TABLE Person AUTO_INCREMENT = 1").executeUpdate();

//            c1 = new CityInfo("2300","Amager");
//            c2 = new CityInfo("2400","Nordvest");
//            c3 = new CityInfo("2100","Østerbro");

            a1 = new Address("Amagerbrogade 12","3. TV", "3000 Helsingør");
            a2 = new Address("Stærevej 6","1.TH", "2740 Skovlunde");
            a3 = new Address("Østerbrogade 271","ST, MF", "3660 Stenløse");

            p1 = new Person("Morten@B.dk", "Morten", "Bendeke",a1);
            p2 = new Person("Denis@P.dk", "Denis", "Pedersen",a2);
            p3 = new Person("Betül@I.dk", "Betül", "Iskender", a3);
            //em.createNamedQuery("Person.deleteAllRows").executeUpdate();
//            em.persist(c1);
//            em.persist(c2);
//            em.persist(c3);
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
    public void getById() {
        Person expected = p1;
       Person actual = facade.getById(p1.getId());
       assertEquals(expected, actual);
    }


    @Test
    public void create() {
        Person newPerson = new Person("stiickish@yelong.dk", "Yelong","Hartl-He", a1);
        Person actual = facade.create(newPerson);
        assertEquals(newPerson, actual);
        assertNotNull(actual.getId());
    }




    @Test
    void update() {
        p1.setEmail("Bjergkøbing@email.com");
        Person updatedPerson = p1;
        Person actual = facade.update(p1);
        System.out.println(actual);
        assertEquals(updatedPerson, actual);
    }


   @Test
    public void getAllPersons() throws Exception {
        List<Person> personList = facade.getAll();

        int expected = 3;

        assertEquals(expected,personList.size() );
    }



    @Test
    void delete() {
        facade.delete(p1);
        int expected = 2;
        int actual = facade.getAll().size();
        assertEquals(expected,actual);
    }




}
