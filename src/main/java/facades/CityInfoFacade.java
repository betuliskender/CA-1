package facades;

import dtos.CityInfoDto;
import entities.CityInfo;
import errorhandling.CustomException;
import interfaces.facades.IFacade;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;


public class CityInfoFacade implements IFacade<CityInfoDto> {

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
    public CityInfoDto getById(Integer id) throws CustomException {
        EntityManager em = getEntityManager();
        CityInfo cityInfo = em.find(CityInfo.class, id);

        if(cityInfo == null)
            throw new CustomException("Could not find CityInfo with id: " + id);
        return new CityInfoDto(cityInfo);
    }

    @Override
    public List<CityInfoDto> getAll() {
        List<CityInfoDto> cityInfoDtoList = new ArrayList<>();
        EntityManager em = getEntityManager();
        TypedQuery<CityInfo> query = em.createQuery("SELECT c FROM CityInfo  c", CityInfo.class);
        query.getResultList().forEach(cityInfo -> {
            cityInfoDtoList.add(new CityInfoDto(cityInfo));
        });
        return cityInfoDtoList;
    }

    @Override
    public CityInfoDto create(CityInfoDto cityInfo) {
        EntityManager em = getEntityManager();
        CityInfo c = new CityInfo(cityInfo);

        try {
            em.getTransaction().begin();
            em.persist(c);
            if(c.getAddresses().size() > 0)
            {
                c.getAddresses().forEach( address -> {
                    em.persist(address);
                });
            }
            em.getTransaction().commit();
        }finally {
            em.close();
        }
        return new CityInfoDto(c);
    }

    @Override
    public CityInfoDto update(CityInfoDto cityInfoDto) throws CustomException {
        EntityManager em = getEntityManager();
        CityInfo c = em.find(CityInfo.class, cityInfoDto.getId());

        if(c == null)
            throw new CustomException("Could not update CityInfo with id: " + cityInfoDto.getId());

        CityInfo cityInfo = new CityInfo(cityInfoDto);

        try {
            em.getTransaction().begin();
            em.merge(cityInfo);
            em.getTransaction().commit();
        }finally {
            em.close();
        }
        return new CityInfoDto(cityInfo);
    }

    @Override
    public CityInfoDto delete(Integer id) throws CustomException {
        EntityManager em = getEntityManager();
        CityInfo cityInfo = em.find(CityInfo.class, id);

        if(cityInfo == null)
            throw new CustomException("Could not remove CityInfo with id: " + id);

        try {
            em.getTransaction().begin();
            em.remove(cityInfo);
            em.getTransaction().commit();
        }finally {
            em.close();
        }
        return new CityInfoDto(cityInfo);
    }
}
