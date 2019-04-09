package at.htl.supermarket.rest;

import at.htl.supermarket.model.Activity;
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

@Path("product")
@Stateless
@Api(value = "ProductEndpoint")
public class ProductEndpoint {
    @PersistenceContext
    EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts(){
        TypedQuery<Product> query = em.createNamedQuery("Product.getAll", Product.class);
        List<Product> list = query.getResultList();
        return Response.ok().entity(list).build();
    }

    @GET
    @Path("/getByBrand/{brand}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByBrand(@PathParam("brand") String brand){
        TypedQuery<Product> query = em.createNamedQuery("Product.getByBrand", Product.class);
        query.setParameter("brand",brand);
        List<Product> list = query.getResultList();
        return Response.ok().entity(list).build();
    }

    @GET
    @Path("{id}")
    public Product getProduct(@PathParam("id") long id) {
        return em.find(Product.class, id);
    }

    @POST
    public Long postProduct(Product product){
        em.persist(product);
        return product.getId();
    }

    @DELETE
    @Path("{id}")
    public void deleteProduct(@PathParam("id") long id){
        Product p = em.find(Product.class, id);
        if(p != null){
            em.remove(em.contains(p) ? p : em.merge(p));
        }
    }
}