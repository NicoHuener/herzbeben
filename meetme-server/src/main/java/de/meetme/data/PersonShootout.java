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
    private String date;
    private String category;

    public PersonShootout() {// Needed by Jackson deserialization
        super(0);
    }

    public PersonShootout(Person person, Shootout shootout,String date, String category) {
        super(0);
        this.person = person;
        this.shootout = shootout;
        this.date = date;
        this.category = category;

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
    public String getDate() {
        return date;
    }

    @JsonProperty
    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "PersonShootout{" +
                "person=" + person +
                ", shootout='" + shootout + '\'' +
                ", date='" + date + '\'' +
                ", category='" + category + '\'' +
                '}';
    }


}
