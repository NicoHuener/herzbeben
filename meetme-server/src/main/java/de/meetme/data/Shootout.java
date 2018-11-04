package de.meetme.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Entity
public class Shootout extends PersistentObject {
    // create table person ( id int, name varchar(256), firstname varchar(256), email varchar(256))

    @Column(unique = true, nullable = false)
    private String name;
    @ManyToOne
    private Person person;
    private String category;


    public Shootout() {
        // Needed by Jackson deserialization
        super(0);
    }

    public Shootout(String name,Person person, String category) {
        super(0);
        this.name = name;
        this.person = person;
        this.category = category;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public Person getPerson() {
        return person;
    }

    @JsonProperty
    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Shootout{" +
                "name='" + name + '\'' +
                ", personid=" + getPerson().getEmail() +
                ", category=" + category +
                '}';
    }
}
