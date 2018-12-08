package at.htl.supermarket.rest;

import org.junit.Before;
import org.junit.Test;

import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ProductEndpointIT {
    Client client;
    WebTarget target;

    @Before
    public void Setup() {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8085/supermarket/rest/product");
    }

    @Test
    public void t01_basicGetProducts() {
        Response response = target
                .request(MediaType.APPLICATION_JSON)
                .get();

        assertThat(response.getStatus(), is(200));
    }

    @Test
    public void t02_getByBrand() {
        Response response = target
                .path("getByBrand/Clever")
                .request(MediaType.APPLICATION_JSON)
                .get();

        //JsonObject result = response.readEntity(JsonObject.class);

        assertThat(response.getStatus(), is(200));
        //assertThat(result.getString("name"), is("Clever Wasser"));
    }

    @Test
    public void t03_getSpecificProduct() {
        Response response = target
                .path("1")
                .request(MediaType.APPLICATION_JSON)
                .get();

        JsonObject result = response.readEntity(JsonObject.class);

        assertThat(response.getStatus(), is(200));
        assertThat(result.getString("name"), is("Green Smoothie"));
    }
}
