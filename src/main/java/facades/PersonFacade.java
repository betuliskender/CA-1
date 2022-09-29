package facades;

import entities.Person;
import interfaces.facades.IFacade;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class PersonFacade implements IFacade<Person> {

    private static PersonFacade instance;
    private static EntityManagerFactory emf;

    public static PersonFacade getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public Person getById(Integer id) {
        EntityManager em = getEntityManager();
        Person p = em.find(Person.class, id);
        return p;
    }

    public Person getByPhone(int phone) {
        EntityManager em = getEntityManager();
        Person p = em.find(Person.class, phone);
        return p;
    }

    @Override
    public List<Person> getAll() {
        EntityManager em = getEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person P", Person.class);
        return query.getResultList();
    }

    @Override
    public Person create(Person person) {
        EntityManager em = getEntityManager();
        try
        {
            em.getTransaction().begin();;
            em.persist(person);
            em.getTransaction().commit();;
        }finally {
            {
                em.close();;
            }
        }
        return person;
    }

    @Override
    public Person update(Person person) {
        EntityManager em = getEntityManager();
        try {

                em.getTransaction().begin();
                em.merge(person);
                em.getTransaction().commit();

        }finally {
            em.close();
        }return person;
    }

    @Override
    public Person delete(Person person) {
        EntityManager em = getEntityManager();
        Person p = em.find(Person.class, person.getId());
        try{
            em.getTransaction().begin();
            em.remove(p);
            em.getTransaction().commit();
        }finally {
            em.close();
        }
        return p;
    }


}
