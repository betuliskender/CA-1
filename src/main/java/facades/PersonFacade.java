package facades;

import entities.Person;
import interfaces.facades.IFacade;

import javax.persistence.EntityManagerFactory;
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

    @Override
    public Person getById(Integer id) {
        return null;
    }

    @Override
    public List<Person> getAll() {
        return null;
    }

    @Override
    public Person create(Person person) {
        return null;
    }

    @Override
    public Person update(Person person) {
        return null;
    }

    @Override
    public Person delete(Person person) {
        return null;
    }
}
