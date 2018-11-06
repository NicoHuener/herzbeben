package de.meetme.data.Bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.meetme.data.PersistentObject;
import de.meetme.data.Shootout;

public class PersonShootoutShootoutBean extends PersistentObject {


    private Shootout shootout;
    private int anzahl;

    public PersonShootoutShootoutBean() {
        super(0);
    }

    public PersonShootoutShootoutBean(Shootout shootout, int anzahl) {
        super(0);
        this.shootout = shootout;
        this.anzahl = anzahl;
    }
   @JsonProperty
    public Shootout getShootout() {
        return shootout;
    }

    @JsonProperty
    public int getAnzahl() {
        return anzahl;
    }
}
