package at.htl.supermarket.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NamedQueries({
        @NamedQuery(name = "Customer.getAll", query = "select c from Customer c")
})
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate accession_date;
    private int loyalty_points;
    private int card_number;
    private String rank;

    @OneToOne
    private Person person;

    public Customer() {
    }

    public Customer(LocalDate accession_date, int loyalty_points, int card_number, String rank, Person person) {
        this.accession_date = accession_date;
        this.loyalty_points = loyalty_points;
        this.card_number = card_number;
        this.rank = rank;
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getAccession_date() {
        return accession_date;
    }

    public void setAccession_date(LocalDate accession_date) {
        this.accession_date = accession_date;
    }

    public int getLoyalty_points() {
        return loyalty_points;
    }

    public void setLoyalty_points(int loyalty_points) {
        this.loyalty_points = loyalty_points;
    }

    public int getCard_number() {
        return card_number;
    }

    public void setCard_number(int card_number) {
        this.card_number = card_number;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
