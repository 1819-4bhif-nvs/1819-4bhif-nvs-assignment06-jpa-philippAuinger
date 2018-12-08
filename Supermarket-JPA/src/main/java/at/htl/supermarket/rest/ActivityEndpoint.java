package at.htl.supermarket.rest;

import at.htl.supermarket.model.Activity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("activity")
@Stateless
public class ActivityEndpoint {
    @PersistenceContext
    EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getActivities(){
        TypedQuery<Activity> query = em.createNamedQuery("Activity.getAll", Activity.class);
        List<Activity> list = query.getResultList();
        return Response.ok().entity(list).build();
    }

    @GET
    @Path("/getByBrand/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByBrand(@PathParam("name") String name){
        TypedQuery<Activity> query = em.createNamedQuery("Activity.getByBrand", Activity.class);
        query.setParameter("brand",name);
        List<Activity> list = query.getResultList();
        return Response.ok().entity(list).build();
    }

    @GET
    @Path("/getByCustomerLastname/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByCustomerLastname(@PathParam("name") String name){
        TypedQuery<Activity> query = em.createNamedQuery("Activity.getByCustomerLastname", Activity.class);
        query.setParameter("name",name);
        List<Activity> list = query.getResultList();
        return Response.ok().entity(list).build();
    }

    @POST
    public Long postActivity(Activity activity){
        em.persist(activity);
        return activity.getId();
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
