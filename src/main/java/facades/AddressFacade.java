package facades;

import entities.Address;
import interfaces.facades.IFacade;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class AddressFacade implements IFacade<Address> {

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
        public Address getById(Integer id) {
            EntityManager em = getEntityManager();
            Address a = em.find(Address.class, id);
            return  a;
        }

        @Override
        public List<Address> getAll() {
            EntityManager em = getEntityManager();
            TypedQuery<Address> query =em.createQuery("SELECT a FROM Address a", Address.class);
            return query.getResultList();
        }

        @Override
        public Address create(Address address) {
            EntityManager em = getEntityManager();

            try {
                em.getTransaction().begin();
                em.persist(address);
                em.getTransaction().commit();
            } finally {
                em.close();
            }
            return address;
        }

        @Override
        public Address update(Address address) {
            EntityManager em = getEntityManager();

            try {
                em.getTransaction().begin();
                em.merge(address);
                em.getTransaction().commit();
            } finally {
                em.close();
            }
            return address;
        }

        @Override
        public Address delete(Integer id) {
            EntityManager em = getEntityManager();
            Address a = em.find(Address.class, id);

            try{
                em.getTransaction().begin();
                em.remove(a);
                em.getTransaction().commit();
            } finally {
                em.close();
            }
            return a;
        }
    }
