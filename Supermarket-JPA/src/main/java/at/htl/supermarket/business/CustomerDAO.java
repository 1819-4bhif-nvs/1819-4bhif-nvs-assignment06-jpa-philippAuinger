package at.htl.supermarket.business;

import at.htl.supermarket.model.Customer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CustomerDAO {

    @PersistenceContext
    EntityManager em;

    public Customer get(long id){
        Customer entity = em.find(Customer.class, id);
        return entity;
    }

    public void remove(Customer entity) {
        entity = em.merge(entity);
        em.remove(entity);
    }

    public void save(Customer entity) {
        em.persist(entity);
        em.flush();
    }

    public Customer update(Customer entity) {
        entity = em.merge(entity);
        em.flush();
        em.refresh(entity);
        return entity;
    }
}
