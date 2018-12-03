package at.htl.supermarket.rest;

import at.htl.supermarket.model.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("person")
public class PersonEndpoint {
    @PersistenceContext
    EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getPersons(){
        TypedQuery<Person> query = em.createNamedQuery("Person.getAll", Person.class);
        return query.getResultList();
    }

    @GET
    @Path("/getByLastname/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getByName(@PathParam("name") String name){
        TypedQuery<Person> query = em.createNamedQuery("Person.getByLastname", Person.class);
        query.setParameter("name",name);
        return query.getResultList();
    }

    @POST
    public void postPerson(Person person){
        em.persist(person);
    }

    @DELETE
    @Path("{id}")
    public void deletePerson(@PathParam("id") long id){
        Person p = em.find(Person.class, id);
        if(p != null){
            em.remove(p);
        }
    }
}
