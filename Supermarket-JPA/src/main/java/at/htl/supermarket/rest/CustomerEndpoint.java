package at.htl.supermarket.rest;

import at.htl.supermarket.business.CustomerDAO;
import at.htl.supermarket.model.Customer;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("customer")
@Stateless
public class CustomerEndpoint {
    @PersistenceContext
    EntityManager em;

    @Inject
    CustomerDAO customerDAO;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomers(){
        TypedQuery<Customer> query = em.createNamedQuery("Customer.getAll", Customer.class);
        List<Customer> list = query.getResultList();
        return Response.ok().entity(list).build();
    }
    /*
    * {
     "birthdate":"2000-12-13",
     "email":"philipp-email.com",
     "firstname":"Philipp",
     "lastname":"Auinger",
     "mobilePhone":"+4306804423123",
     "accession_date":"2014-01-10",
     "card_number":45352,
     "loyalty_points":940,
     "rank":"Friend"
     }
    */

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postCustomer(Customer customer){
        customerDAO.save(customer);
        return Response.created(URI.create("http://localhost:8085/supermarket/rest/customer/" + customer.getId())).build();
    }

    @DELETE
    @Path("{id}")
    public void deleteCustomer(@PathParam("id") long id){
        Customer c = em.find(Customer.class, id);
        if(c != null){
            customerDAO.remove(c);
        }
    }
}
