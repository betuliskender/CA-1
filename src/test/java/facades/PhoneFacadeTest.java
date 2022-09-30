package facades;

import entities.*;
import entities.Phone;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class PhoneFacadeTest
{
    private static EntityManagerFactory emf;
    private static PhoneFacade facade;
//    CityInfo c1, c2, c3;
    Address a1, a2, a3;
    Person person1, person2; 
    Phone phone1, phone2;

    public PhoneFacadeTest() {
    }

    public static void deleteData()
    {
        EntityManager em = emf.createEntityManager();

        try{

            em.getTransaction().begin();
            em.createQuery("DELETE From Phone").executeUpdate();
            em.createNativeQuery("ALTER TABLE Phone AUTO_INCREMENT = 1").executeUpdate();
            em.createQuery("DELETE From Person ").executeUpdate();
            em.createNativeQuery("ALTER TABLE Person AUTO_INCREMENT = 1").executeUpdate();
            em.createQuery("DELETE From Address ").executeUpdate();
            em.createNativeQuery("ALTER TABLE Address AUTO_INCREMENT = 1").executeUpdate();
            em.createQuery("DELETE From CityInfo ").executeUpdate();
//            em.createNativeQuery("ALTER TABLE City_Info AUTO_INCREMENT = 1").executeUpdate();
//            em.getTransaction().commit();
        }
        finally {
            em.close();
        }
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = PhoneFacade.getInstance(emf);
        deleteData();

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
//
//            c1 = new CityInfo("2300","Amager");
//            c2 = new CityInfo("2400","Nordvest");
//            c3 = new CityInfo("2100","Østerbro");

            a1 = new Address("Amagerbrogade 12","3. TV","2100 Østerbro");
            a2 = new Address("Stærevej 6","1.TH", "2400 Nordvest");
            a3 = new Address("Østerbrogade 271","ST, MF", "2300 Amager");
            person1 = new Person("Denis@P.dk", "Denis", "Pedersen", a1);
            person2 = new Person("Betül@I.dk", "Betül", "Iskender", a2);
            phone1 = new Phone(1020304, "Home", person1);
            phone2 = new Phone(5060708, "Work", person2);
//            em.persist(c1);
//            em.persist(c2);
//            em.persist(c3);
            em.persist(a1);
            em.persist(a2);
            em.persist(a3);
            em.persist(person1);
            em.persist(person2);
            em.persist(phone1);
            em.persist(phone2);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
        deleteData();
//        Remove any data after each test was run
    }

    @Test
    void getById() {
        Phone expected = phone1;
        Phone actual = facade.getById(phone1.getId());
        assertEquals(expected, actual);
    }

    @Test
    void getAll(){
        int expected = 2;
        List<Phone> PhoneList = facade.getAll();
        assertEquals(expected, PhoneList.size());
    }

    @Test
    void create() {
        Phone phone = new Phone(12233445, "Work", person1);
        Phone expected = phone;
        Phone actual = facade.create(phone);
        assertEquals(expected, actual);
        assertNotNull(actual.getId());
    }

    @Test
    void update() {
        phone1.setDescription("Mobile");
        Phone expected = phone1;
        Phone actual = facade.update(phone1);
        assertEquals(expected, actual);
    }

    @Test
    void delete(){
        facade.delete(phone1);
        int expected = 1;
        int actual = facade.getAll().size();
        assertEquals(expected, actual);
    }
}
