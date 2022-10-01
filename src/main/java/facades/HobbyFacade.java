package facades;

import entities.Hobby;
import interfaces.facades.IFacade;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class HobbyFacade implements IFacade<Hobby> {

    private static HobbyFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private HobbyFacade() {}

    public static HobbyFacade getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new HobbyFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public Hobby getById(Integer id) {
        EntityManager em = getEntityManager();
        Hobby h = em.find(Hobby.class, id);
        return  h;
    }

    @Override
    public List<Hobby> getAll() {
        EntityManager em = getEntityManager();
        TypedQuery<Hobby> query =em.createQuery("SELECT h FROM Hobby h", Hobby.class);
        return query.getResultList();
    }

    @Override
    public Hobby create(Hobby hobby) {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(hobby);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return hobby;
    }

    @Override
    public Hobby update(Hobby hobby) {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();
            em.merge(hobby);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return hobby;
    }

    @Override
    public Hobby delete(Integer id) {
        EntityManager em = getEntityManager();
        Hobby h = em.find(Hobby.class, id);

        try{
            em.getTransaction().begin();
            em.remove(h);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return h;
    }
}
