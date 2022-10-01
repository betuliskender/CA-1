package facades;

import dtos.AddressDto;
import entities.Address;
import interfaces.facades.IFacade;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import java.util.List;

public class AddressFacade implements IFacade<AddressDto> {

        private static facades.AddressFacade instance;
        private static EntityManagerFactory emf;

        //Private Constructor to ensure Singleton
        private AddressFacade() {}

        public static facades.AddressFacade getInstance(EntityManagerFactory _emf) {
            if (instance == null) {
                emf = _emf;
                instance = new facades.AddressFacade();
            }
            return instance;
        }

        private EntityManager getEntityManager() {
            return emf.createEntityManager();
        }

        @Override
        public AddressDto getById(Integer id) {
            EntityManager em = getEntityManager();
            Address a = em.find(Address.class, id);
            return  new AddressDto(a);
        }

        @Override
        public List<AddressDto> getAll() {
            EntityManager em = getEntityManager();
            TypedQuery<Address> query =em.createQuery("SELECT a FROM Address a", Address.class);
            List<Address>addressList = query.getResultList();
            return AddressDto.getDtos(addressList);
        }

        @Override
        public AddressDto create(AddressDto addressDto) {
            EntityManager em = getEntityManager();

            try {
                em.getTransaction().begin();
                em.persist(addressDto);
                em.getTransaction().commit();
            } finally {
                em.close();
            }
            return new AddressDto(addressDto);
        }

        @Override
        public AddressDto update(AddressDto address) {
            EntityManager em = getEntityManager();
            Address fromDB = em.find(Address.class,address.getId());
            if(fromDB == null){
                throw new EntityNotFoundException("Adress not found");
            }
            try {
                em.getTransaction().begin();
                em.merge(fromDB);
                em.getTransaction().commit();
            } finally {
                em.close();
            }
            return new AddressDto(fromDB);
        }

    @Override
        public AddressDto delete(Integer id) {
            EntityManager em = getEntityManager();
            Address a = em.find(Address.class, id);

            try{
                em.getTransaction().begin();
                em.remove(a);
                em.getTransaction().commit();
            } finally {
                em.close();
            }
            return new AddressDto(a);
        }
    }
