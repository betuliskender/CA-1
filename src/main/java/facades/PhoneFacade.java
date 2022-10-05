package facades;

import dtos.PersonDto;
import dtos.PhoneDto;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import entities.Phone;
import errorhandling.CustomException;
import interfaces.facades.IFacade;
import services.PhoneHandler;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.swing.undo.CannotUndoException;
import java.util.ArrayList;
import java.util.List;

public class PhoneFacade implements IFacade<PhoneDto> {

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
    public PhoneDto getById(Integer id) throws CustomException {
        EntityManager em = getEntityManager();
        Phone phone = em.find(Phone.class, id);

        if (phone == null)
            throw new CustomException("Could not find Phone with id: " + id);

        if(phone != null)
        {
            return new PhoneDto(phone);
        }

        return null;
    }

    @Override
    public List<PhoneDto> getAll() {
        List<PhoneDto> phoneDtoList = new ArrayList<>();
        EntityManager em = getEntityManager();
        TypedQuery<Phone> query = em.createQuery("SELECT p FROM Phone p", Phone.class);
        query.getResultList().forEach(phone -> {
            phoneDtoList.add(new PhoneDto(phone));
        });
        return phoneDtoList;
    }

    @Override
    public PhoneDto create(PhoneDto phoneDto) {
        EntityManager em = getEntityManager();
        Person person = em.find(Person.class, phoneDto.getPerson().getId());
        Phone phone = new Phone(phoneDto);

        try {
            em.getTransaction().begin();
            if(person != null){
                em.persist(phone);
                em.merge(person);
            }
            else {
                Person newPerson = new Person(phoneDto.getPerson());
                em.persist(phone);
                em.persist(newPerson);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PhoneDto(phone);
    }

    @Override
    public PhoneDto update(PhoneDto phoneDto) throws CustomException {
        EntityManager em = getEntityManager();
        Phone existingPhone = em.find(Phone.class, phoneDto.getId());

        if (existingPhone == null)
            throw new CustomException("Could not update Hobby with id: " + phoneDto.getId());

        Phone phone = PhoneHandler.mergeDTOAndEntity(phoneDto, existingPhone);

        try {
            em.getTransaction().begin();
            em.merge(phone);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PhoneDto(phone);
    }

    @Override
    public PhoneDto delete(Integer id) throws CustomException {
        EntityManager em = getEntityManager();
        Phone phone = em.find(Phone.class, id);

        if (phone == null)
            throw new CustomException("Could not remove Phone with id: " + id);

        try{
            em.getTransaction().begin();
            em.remove(phone);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PhoneDto(phone);
    }
}