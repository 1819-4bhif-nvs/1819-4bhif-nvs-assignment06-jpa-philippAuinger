package at.htl.supermarket.rest;

import at.htl.supermarket.model.Cashier;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("cashier")
public class CashierEndpoint {
    @PersistenceContext
    EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cashier> getCashiers(){
        TypedQuery<Cashier> query = em.createNamedQuery("Cashier.getAll", Cashier.class);
        return query.getResultList();
    }

    @POST
    public void postCashier(Cashier cashier){
        em.persist(cashier);
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
