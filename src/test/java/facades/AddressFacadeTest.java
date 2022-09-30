package facades;

import entities.Address;
import entities.CityInfo;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class AddressFacadeTest {
        private static EntityManagerFactory emf;
        private static AddressFacade facade;
//        CityInfo c1, c2, c3;
        Address a1, a2, a3;

        public AddressFacadeTest() {
        }

        public static void deleteData()
        {
            EntityManager em = emf.createEntityManager();

            try{

                em.getTransaction().begin();
                em.createQuery("DELETE From Address ").executeUpdate();
                em.createNativeQuery("ALTER TABLE Address AUTO_INCREMENT = 1").executeUpdate();
//                em.createQuery("DELETE From CityInfo ").executeUpdate();
//                em.createNativeQuery("ALTER TABLE City_Info AUTO_INCREMENT = 1").executeUpdate();
//                em.getTransaction().commit();
            }
            finally {
                em.close();
            }
        }

        @BeforeAll
        public static void setUpClass() {
            emf = EMF_Creator.createEntityManagerFactoryForTest();
            facade = AddressFacade.getInstance(emf);
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

//                c1 = new CityInfo("2300","Amager");
//                c2 = new CityInfo("2400","Nordvest");
//                c3 = new CityInfo("2100","Østerbro");

                a1 = new Address("Amagerbrogade 12","3. TV", "1233 København K");
                a2 = new Address("Stærevej 6","1.TH", "3660 Stenløse");
                a3 = new Address("Østerbrogade 271","ST, MF","2740 Skovlunde");

//                em.persist(c1);
//                em.persist(c2);
//                em.persist(c3);
                em.persist(a1);
                em.persist(a2);
                em.persist(a3);

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
            Address expected = a1;
            Address actual = facade.getById(a1.getId());
            assertEquals(expected, actual);
        }

        @Test
        void getAll(){
            int expected = 3;
            List<Address> AddressList = facade.getAll();
            assertEquals(expected, AddressList.size());
        }

        @Test
        void create() {
            Address address = new Address("Hagbartsvej", "5", "3000 Helsingør");
            Address expected = address;
            Address actual = facade.create(address);
            assertEquals(expected, actual);
            assertNotNull(actual.getId());
        }

        @Test
        void update() {
            a1.setStreet("Bygaden");
            Address expected = a1;
            Address actual = facade.update(a1);
            assertEquals(expected, actual);
        }

        @Test
        void delete(){
            facade.delete(a1);
            int expected = 2;
            int actual = facade.getAll().size();
            assertEquals(expected, actual);
        }

}
