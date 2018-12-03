package at.htl.supermarket.rest;

import at.htl.supermarket.model.Customer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("customer")
public class CustomerEndpoint {
    @PersistenceContext
    EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> getCustomers(){
        TypedQuery<Customer> query = em.createNamedQuery("Customer.getAll", Customer.class);
        return query.getResultList();
    }

    @POST
    public void postCustomer(Customer customer){
        em.persist(customer);
    }

    @DELETE
    @Path("{id}")
    public void deleteCustomer(@PathParam("id") long id){
        Customer c = em.find(Customer.class, id);
        if(c != null){
            em.remove(c);
        }
    }
}
