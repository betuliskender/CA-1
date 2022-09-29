package facades;

import entities.CityInfo;
import interfaces.facades.IFacade;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;


public class CityInfoFacade implements IFacade<CityInfo> {

    private static CityInfoFacade instance;
    private static EntityManagerFactory emf;


    public CityInfoFacade() {
    }

    public static CityInfoFacade getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CityInfoFacade();
        }
        return instance;
    }
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public CityInfo getById(Integer id) {
        EntityManager em = getEntityManager();
        CityInfo cityInfo = em.find(CityInfo.class, id);
        return cityInfo;
    }

    @Override
    public List<CityInfo> getAll() {
        EntityManager em = getEntityManager();
        TypedQuery<CityInfo> query = em.createQuery("SELECT c FROM CityInfo  c", CityInfo.class);
        return query.getResultList();
    }

    @Override
    public CityInfo create(CityInfo cityInfo) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cityInfo);
            em.getTransaction().commit();
        }finally {
            em.close();
        }return cityInfo;
    }

    @Override
    public CityInfo update(CityInfo cityInfo) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(cityInfo);
            em.getTransaction().commit();
        }finally {
            em.close();
        }
        return cityInfo;
    }

    @Override
    public CityInfo delete(CityInfo cityInfo) {
        EntityManager em = getEntityManager();
        CityInfo c = em.find(CityInfo.class, cityInfo.getId());
        try {
            em.getTransaction().begin();
            em.remove(c);
            em.getTransaction().commit();
        }finally {
            em.close();
        }
        return c;
    }
}
