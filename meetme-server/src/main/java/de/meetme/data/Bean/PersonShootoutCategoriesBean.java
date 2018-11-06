package de.meetme.data.Bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.meetme.data.PersistentObject;

public class PersonShootoutCategoriesBean extends PersistentObject {

    private String category;
    private int anzahl;

    public PersonShootoutCategoriesBean() {
        super(0);
    }

    public PersonShootoutCategoriesBean(String category, int anzahl) {
        super(0);
        this.category = category;
        this.anzahl = anzahl;
    }

    @JsonProperty
    public String getCategory() {
        return category;
    }

    @JsonProperty
    public int getAnzahl() {
        return anzahl;
    }
}
