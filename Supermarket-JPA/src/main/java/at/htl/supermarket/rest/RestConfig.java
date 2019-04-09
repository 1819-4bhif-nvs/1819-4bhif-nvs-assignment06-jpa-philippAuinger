package at.htl.supermarket.rest;

import at.htl.supermarket.model.Storage;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("rest")
public class RestConfig extends Application {
    @Override

    public Set<Class<?>> getClasses() {

        Set<Class<?>> classes = new HashSet<>();

        classes.add(ActivityEndpoint.class);
        classes.add(CashierEndpoint.class);
        classes.add(CustomerEndpoint.class);
        classes.add(ProductEndpoint.class);
        classes.add(StorageEndpoint.class);

        classes.add(com.github.phillipkruger.apiee.ApieeService.class);

        return classes;

    }

}
