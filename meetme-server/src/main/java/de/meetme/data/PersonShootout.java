package de.meetme.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.security.Timestamp;

@Entity
public class PersonShootout extends PersistentObject{

    @ManyToOne
    private Person person;
    @ManyToOne
    private Shootout shootout;
    //private String timestamp;
    private String date;
    private String category;

    public PersonShootout() {// Needed by Jackson deserialization
        super(0);
    }

    public PersonShootout(Person person, Shootout shootout, String category, String date) {
        super(0);
        this.person = person;
        this.shootout = shootout;
        this.category = category;
        this.date = date;
    }

    @JsonProperty
    public Person getPerson() {
        return person;
    }

    @JsonProperty
    public Shootout getShootout() {
        return shootout;
    }

    @JsonProperty
    public String getCategory() {
        return category;
    }

    @JsonProperty
    public String getDate() {
        return date;
    }
    @Override
    public String toString() {
        return "PersonShootout{" +
                "person=" + person +
                ", shootout='" + shootout + '\'' +
                ", category='" + category + '\'' +
                ", date='" + date + '\'' +
                '}';
    }


}
