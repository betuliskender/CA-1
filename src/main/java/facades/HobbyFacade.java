package facades;

import dtos.HobbyDto;
import entities.Hobby;
import errorhandling.CustomException;
import interfaces.facades.IFacade;
import services.HobbyHandler;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class HobbyFacade implements IFacade<HobbyDto> {

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
    public HobbyDto getById(Integer id) throws CustomException {
        EntityManager em = getEntityManager();
        Hobby hobby = em.find(Hobby.class, id);

        if(hobby == null)
            throw new CustomException("Could not find Hobby with id: " + id);

        return  new HobbyDto(hobby);
    }

    @Override
    public List<HobbyDto> getAll() {
        List<HobbyDto> hobbyDtos = new ArrayList<>();
        EntityManager em = getEntityManager();
        TypedQuery<Hobby> query =em.createQuery("SELECT h FROM Hobby h", Hobby.class);
        query.getResultList().forEach( hobby -> {
            hobbyDtos.add(new HobbyDto(hobby));
        });
        return hobbyDtos;
    }

    @Override
    public HobbyDto create(HobbyDto hobbyDto) {
        EntityManager em = getEntityManager();
        Hobby hobby = new Hobby(hobbyDto);
        try {
            em.getTransaction().begin();
            em.persist(hobby);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new HobbyDto(hobby);
    }

    @Override
    public HobbyDto update(HobbyDto hobbyDto) throws CustomException{
        EntityManager em = getEntityManager();
        Hobby existingHobby = em.find(Hobby.class, hobbyDto.getId());

        if (existingHobby == null)
            throw new CustomException("Could not update Hobby with id: " + hobbyDto.getId());

        Hobby hobby = HobbyHandler.mergeDTOAndEntity(hobbyDto, existingHobby);

        try {
            em.getTransaction().begin();
            em.merge(hobby);
            em.getTransaction().commit();
        } finally {

            em.close();
        }
        return new HobbyDto(hobby);
    }

    @Override
    public HobbyDto delete(Integer id) throws CustomException{
        EntityManager em = getEntityManager();
        Hobby hobby = em.find(Hobby.class, id);

        if (hobby == null)
            throw new CustomException("Could not remove Hobby with id: " + id);

        try{
            em.getTransaction().begin();
            em.remove(hobby);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new HobbyDto(hobby);
    }
}
