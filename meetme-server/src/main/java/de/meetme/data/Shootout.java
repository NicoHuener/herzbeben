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

    public Shootout() {
        // Needed by Jackson deserialization
        super(0);
    }

    public Shootout(String name,Person person) {
        super(0);
        this.name = name;
        this.person = person;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public Person getPerson() {
        return person;
    }

    @Override
    public String toString() {
        return "Shootout{" +
                "name='" + name + '\'' +
                ", personid=" + getPerson().getEmail() +
                '}';
    }
}
