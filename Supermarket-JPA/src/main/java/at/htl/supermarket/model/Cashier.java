package at.htl.supermarket.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NamedQueries({
        @NamedQuery(name = "Cashier.getAll", query = "select c from Cashier c")
})
public class Cashier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate begin_date;
    private double salary;


    @OneToOne
    private Person person;

    public Cashier() {
    }

    public Cashier(LocalDate begin_date, double salary, Person person) {
        this.begin_date = begin_date;
        this.salary = salary;
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getBegin_date() {
        return begin_date;
    }

    public void setBegin_date(LocalDate begin_date) {
        this.begin_date = begin_date;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
