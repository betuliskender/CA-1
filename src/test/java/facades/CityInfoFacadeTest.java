package facades;


import entities.CityInfo;
import entities.CityInfo;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class CityInfoFacadeTest  {
    private static EntityManagerFactory emf;
    private static CityInfoFacade facade;
    
    CityInfo c1, c2;

    public CityInfoFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = CityInfoFacade.getInstance(emf);
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
            em.createQuery("DELETE From CityInfo ").executeUpdate();
            em.createNativeQuery("ALTER TABLE city_info AUTO_INCREMENT = 1").executeUpdate();
            c1 = new CityInfo("2730", "Herlev");
            c2 = new CityInfo("2700", "Brønshøj");
            em.persist(c1);
            em.persist(c2);

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
    void getById() {
        CityInfo expected = c1;
        CityInfo actual = facade.getById(c1.getId());
        assertEquals(expected, actual);
    }

    @Test
    void getAll(){
        int expected = 2;
        List<CityInfo> cityInfoList = facade.getAll();
        assertEquals(expected, cityInfoList.size());
    }

    @Test
    void create() {
        CityInfo c = new CityInfo("2400", "Nordvest");
        CityInfo expected = c;
        CityInfo actual = facade.create(c);
        assertEquals(expected, actual);
        assertNotNull(actual.getId());
    }

    @Test
    void update() {
        c1.setZipcode("2200");
        CityInfo expected = c1;
        CityInfo actual = facade.update(c1);
        assertEquals(expected, actual);
    }

    @Test
    void delete(){
        facade.delete(c1);
        int expected = 1;
        int actual = facade.getAll().size();
        assertEquals(expected, actual);
    }
}