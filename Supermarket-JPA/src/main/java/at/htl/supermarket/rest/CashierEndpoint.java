package at.htl.supermarket.rest;

import at.htl.supermarket.model.Cashier;
import io.swagger.annotations.Api;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("cashier")
@Stateless
@Api(value = "CashierEndpoint")
public class CashierEndpoint {
    @PersistenceContext
    EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCashiers(){
        TypedQuery<Cashier> query = em.createNamedQuery("Cashier.getAll", Cashier.class);
        List<Cashier> cashiers = query.getResultList();
        return Response.ok().entity(cashiers).build();
    }

    @POST
    public Long postCashier(Cashier cashier){
        em.persist(cashier);
        return cashier.getId();
    }

    @DELETE
    @Path("{id}")
    public void deleteCashier(@PathParam("id") long id){
        Cashier c = em.find(Cashier.class, id);
        if(c != null){
            em.remove(c);
        }
    }
}
