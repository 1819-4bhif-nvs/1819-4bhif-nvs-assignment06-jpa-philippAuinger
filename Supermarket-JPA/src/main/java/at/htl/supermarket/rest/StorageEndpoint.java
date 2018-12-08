package at.htl.supermarket.rest;

import at.htl.supermarket.model.Storage;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("storage")
@Stateless
public class StorageEndpoint {
    @PersistenceContext
    EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStorage(){
        TypedQuery<Storage> query = em.createNamedQuery("Delivery.getAll", Storage.class);
        List<Storage> list = query.getResultList();
        return Response.ok().entity(list).build();
    }

}
