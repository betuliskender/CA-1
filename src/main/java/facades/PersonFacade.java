package facades;

import dtos.PersonDto;
import entities.*;
import interfaces.facades.IFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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
        EntityManager em = emf.createEntityManager();
        Person p = em.find(Person.class, id);
        if(p ==null)
            throw new EntityNotFoundException("Not found");
        return new PersonDto(p);
    }

    //ham her skal omskrives så det er et set af telefon-numre...
    /*public Person getByPhone(int phone) {
        EntityManager em = getEntityManager();
        Person p = em.find(Person.class, phone);
        return p;
    }*/

    @Override
    public List<PersonDto> getAll() {
        List<PersonDto> personDtoList = new ArrayList<>();
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
        query.getResultList().forEach(person -> {
            personDtoList.add(new PersonDto(person));
        });
        return personDtoList;
    }

    @Override
    public PersonDto create(PersonDto personDto) {
        EntityManager em = getEntityManager();
        Set<Hobby> hobbySet = new LinkedHashSet<>();
        personDto.getHobbies().forEach(innerHobbyDto -> {
            hobbySet.add(em.find(Hobby.class, innerHobbyDto.getId()));
        });

        Set<Phone> phoneSet = new LinkedHashSet<>();
        personDto.getPhones().forEach(innerPhoneDto -> {
            phoneSet.add(em.find(Phone.class, innerPhoneDto.getId()));
        });

        Address address = em.find(Address.class, personDto.getAddress().getId());

        Person person = new Person(personDto);


        try {
            em.getTransaction().begin();

            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return null;
    }

    public PersonDto update(PersonDto personDto) {

        EntityManager em = getEntityManager();
        Person fromDb = em.find(Person.class, personDto.getId());
        if(fromDb == null){
            throw new EntityNotFoundException("No such person with that id: "+ personDto.getId());
        }
        PersonDto.InnerAddressDto addressDto = new PersonDto.InnerAddressDto(1,"Her", "der");
        PersonDto.InnerAddressDto.InnerCityInfoDto cityInfoDto = new PersonDto.InnerAddressDto.InnerCityInfoDto(3,"3300", "Kjøbenhavnstrup");
        Address address = new Address(addressDto.getStreet(), addressDto.getAdditionalInfo(), new CityInfo("2300","Bagsværd"));

        fromDb = new Person(personDto.getEmail(),personDto.getFirstName(), personDto.getLastName(), address);
        System.out.println(fromDb.getId());

        try {
            em.getTransaction().begin();
            em.merge(fromDb);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PersonDto(fromDb);
    }

    @Override
    public PersonDto delete(Integer Id) {
        EntityManager em = getEntityManager();
        Person p = em.find(Person.class, Id);
        try {

            em.getTransaction().begin();
            em.remove(p);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PersonDto(p);
    }

    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        PersonFacade personFacade = getInstance(emf);
        personFacade.getAll().forEach(personDto -> System.out.println(personDto));
        //personFacade.update(new PersonDto(3,"John@klklklklklk.com","Betül", "Iskender",new PersonDto.InnerAddressDto(3, "Paradisæblevej", "113")));
        personFacade.create(new PersonDto(9,"anders@klklklklklk.com","Anders", "And",new PersonDto.InnerAddressDto(3, "Paradisæblevej", "113")));

    }

}
