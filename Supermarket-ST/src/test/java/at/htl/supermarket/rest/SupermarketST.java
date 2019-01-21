package at.htl.supermarket.rest;

import org.junit.Before;
import org.junit.Test;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SupermarketST {
    Client client;
    WebTarget target;

    @Before
    public void Setup() {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8085/supermarket/rest");
    }

    @Test
    public void t01_productCrudTesting(){
        //json object
        JsonObject product  = Json.createObjectBuilder()
                .
        add("best_before_date", "2018-12-19")
                .add("brand", "neue marke")
                .add("price", 1.50)
                .add("quantity", 10)
                .add("name","Gulasch")
                .build();
        //post
        Response productPut = target
                .path("product")
                .request(MediaType.TEXT_PLAIN)
                .post(Entity.json(product));

        assertThat(productPut.getStatus(), is(200));
        long productId = productPut.readEntity(long.class);

        Response productGet = target
                .path("product")
                .path(Long.toString(productId))
                .request(MediaType.APPLICATION_JSON)
                .get();

        JsonObject resultJson = productGet.readEntity(JsonObject.class);

        assertThat(productGet.getStatus(), is(200));
        assertThat(resultJson.getString("name"), is("Gulasch"));

        //DELETE
        /*target
                .path("product")
                .path(Long.toString(productId))
                .request()
                .delete();*/
    }
}
