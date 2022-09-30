package facades;

import dtos.PersonDto;
import entities.Person;
import interfaces.facades.IFacade;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class PersonFacade implements IFacade<PersonDto> {

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
    public PersonDto getById(Integer id) {
        EntityManager em = getEntityManager();
        PersonDto p = em.find(PersonDto.class, id);
        return p;
    }

    //ham her skal omskrives så det er et set af telefon-numre...
    /*public Person getByPhone(int phone) {
        EntityManager em = getEntityManager();
        Person p = em.find(Person.class, phone);
        return p;
    }*/

    @Override
    public List<PersonDto> getAll() {
        EntityManager em = getEntityManager();
        TypedQuery<PersonDto> query = em.createQuery("SELECT p FROM Person P", PersonDto.class);
        return query.getResultList();
    }

    @Override
    public PersonDto create(PersonDto person) {
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
    public PersonDto update(PersonDto person) {
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
    public PersonDto delete(PersonDto person) {
        EntityManager em = getEntityManager();
        PersonDto p = em.find(PersonDto.class, person.getId());
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
