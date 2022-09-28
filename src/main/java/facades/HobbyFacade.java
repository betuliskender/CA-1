package facades;

import entities.Hobby;
import interfaces.facades.IFacade;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
        return null;
    }

    @Override
    public List<Hobby> getAll() {
        return null;
    }

    @Override
    public Hobby create(Hobby hobby) {
        return null;
    }

    @Override
    public Hobby update(Hobby hobby) {
        return null;
    }

    @Override
    public Hobby delete(Hobby hobby) {
        return null;
    }
}
