package de.meetme.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class PersonShootout extends PersistentObject{

    @ManyToOne
    private Person person;
    @ManyToOne
    private Shootout shootout;

    public PersonShootout() {// Needed by Jackson deserialization
        super(0);
    }


    public PersonShootout(Person person, Shootout shootout) {
        super(0);
        this.person = person;
        this.shootout = shootout;
    }

    @JsonProperty
    public Person getPerson() {
        return person;
    }

    @JsonProperty
    public Shootout getShootout() {
        return shootout;
    }
}