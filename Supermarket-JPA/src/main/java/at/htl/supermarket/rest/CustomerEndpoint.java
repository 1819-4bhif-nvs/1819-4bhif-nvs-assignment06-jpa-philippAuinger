package at.htl.supermarket.rest;

import at.htl.supermarket.model.Customer;
import at.htl.supermarket.model.Product;
import io.swagger.annotations.Api;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("customer")
@Stateless
@Api(value = "CustomerEndpoint")
public class CustomerEndpoint {
    @PersistenceContext
    EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomers(){
        TypedQuery<Customer> query = em.createNamedQuery("Customer.getAll", Customer.class);
        List<Customer> list = query.getResultList();
        return Response.ok().entity(list).build();
    }

    @POST
    public Long postCustomer(Customer customer){
        em.persist(customer);
        return customer.getId();
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
