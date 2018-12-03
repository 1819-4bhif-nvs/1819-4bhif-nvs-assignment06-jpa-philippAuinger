package at.htl.supermarket.rest;

import at.htl.supermarket.model.Activity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("activity")
public class ActivityEndpoint {
    @PersistenceContext
    EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Activity> getActivities(){
        TypedQuery<Activity> query = em.createNamedQuery("Activity.getAll", Activity.class);
        return query.getResultList();
    }

    @GET
    @Path("/getByBrand/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Activity> getByBrand(@PathParam("name") String name){
        TypedQuery<Activity> query = em.createNamedQuery("Activity.getByBrand", Activity.class);
        query.setParameter("brand",name);
        return query.getResultList();
    }

    @GET
    @Path("/getByCustomerLastname/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Activity> getByCustomerLastname(@PathParam("name") String name){
        TypedQuery<Activity> query = em.createNamedQuery("Activity.getByCustomerLastname", Activity.class);
        query.setParameter("name",name);
        return query.getResultList();
    }

    @POST
    public void postActivity(Activity activity){
        em.persist(activity);
    }

    @DELETE
    @Path("{id}")
    public void deleteActivity(@PathParam("id") long id){
        Activity a = em.find(Activity.class, id);
        if(a != null){
            em.remove(a);
        }
    }
}
