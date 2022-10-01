package facades;

import entities.Hobby;
import entities.Phone;
import entities.Phone;
import interfaces.facades.IFacade;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class PhoneFacade implements IFacade<Phone> {

    private static PhoneFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private PhoneFacade() {}

    public static PhoneFacade getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PhoneFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    @Override
    public Phone getById(Integer id) {
        EntityManager em = getEntityManager();
       Phone phone = em.find(Phone.class, id);

        if(phone != null)
        {
            return phone;
        }

        return null;
    }

    @Override
    public List<Phone> getAll() {
        EntityManager em = getEntityManager();
        TypedQuery<Phone> query = em.createQuery("SELECT p FROM Phone p", Phone.class);
        return query.getResultList();
    }

    @Override
    public Phone create(Phone phone) {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(phone);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return phone;
    }

    @Override
    public Phone update(Phone phone) {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();
            em.merge(phone);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return phone;
    }

    @Override
    public Phone delete(Integer id) {
        EntityManager em = getEntityManager();
        Phone p = em.find(Phone.class, id);

        try{
            em.getTransaction().begin();
            em.remove(p);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return p;
    }
}