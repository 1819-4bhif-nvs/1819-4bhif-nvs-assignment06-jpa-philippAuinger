package at.htl.supermarket.model;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Delivery.getAll", query = "select d from Storage d")
})
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonbTransient
    @OneToMany(mappedBy="storage", cascade = CascadeType.ALL,
            fetch=FetchType.EAGER)
    private List<Product> products = new ArrayList<>();

    public Storage() {
    }

    public Long getId() {
        return id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
