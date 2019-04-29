package at.htl.supermarket.rest;

import at.htl.supermarket.model.Product;
import at.htl.supermarket.model.Storage;
import io.swagger.annotations.Api;

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
@Api(value = "StorageEndpoint")
public class StorageEndpoint {
    @PersistenceContext
    EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        TypedQuery<Storage> query = em.createNamedQuery("Storage.getAll", Storage.class);
        List<Storage> list = query.getResultList();
        return Response.ok().entity(list).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{Id}")
    public Response getById(@PathParam("Id") long id){
        TypedQuery<Storage> query = em.createNamedQuery("Storage.getById", Storage.class);
        query.setParameter("id",id);
        List<Storage> list = query.getResultList();
        return Response.ok().entity(list).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{Id}/getProducts")
    public Response getProducts(@PathParam("Id") long id){
        TypedQuery<Product> query = em.createNamedQuery("Product." +
                "", Product.class);
        query.setParameter("id" , id);
        List<Product> list = query.getResultList();
        return Response.ok().entity(list).build();
    }

}
