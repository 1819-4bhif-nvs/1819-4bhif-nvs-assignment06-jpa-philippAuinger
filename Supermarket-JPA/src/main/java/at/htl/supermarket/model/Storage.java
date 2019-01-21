package at.htl.supermarket.model;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Storage.getAll", query = "select s from Storage s"),
        @NamedQuery(name = "Storage.getById", query = "select s from Storage s where s.id = :id")
        //@NamedQuery(name = "Storage.getProducts", query= "select Storage.products from Storage s where s.id = :id")
})
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonbTransient
    @OneToMany(mappedBy = "storage", cascade = CascadeType.ALL,
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
