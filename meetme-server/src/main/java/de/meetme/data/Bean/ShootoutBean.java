package de.meetme.data.Bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.meetme.data.PersistentObject;
import de.meetme.data.Person;

public class ShootoutBean extends PersistentObject {


    private Person person;
    private int anzahl;

    public ShootoutBean() {
        super(0);
    }

    public ShootoutBean(Person person, int anzahl) {
        super(0);
       this.person = person;
        this.anzahl = anzahl;
    }


    @JsonProperty
    public Person getPerson() {
        return person;
    }
    @JsonProperty
    public int getAnzahl() {
        return anzahl;
    }
}
