package at.htl.supermarket.rest;

import at.htl.supermarket.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("product")
public class ProductEndpoint {
    @PersistenceContext
    EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getProducts(){
        TypedQuery<Product> query = em.createNamedQuery("Product.getAll", Product.class);
        return query.getResultList();
    }

    @GET
    @Path("/getByBrand/{brand}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getByBrand(@PathParam("brand") String brand){
        TypedQuery<Product> query = em.createNamedQuery("Product.getByBrand", Product.class);
        query.setParameter("brand",brand);
        return query.getResultList();
    }

    @POST
    public void postProduct(Product product){
        em.persist(product);
    }

    @DELETE
    @Path("{id}")
    public void deleteProduct(@PathParam("id") long id){
        Product p = em.find(Product.class, id);
        if(p != null){
            em.remove(p);
        }
    }
}