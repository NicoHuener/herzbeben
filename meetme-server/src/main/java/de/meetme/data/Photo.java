package de.meetme.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Photo extends PersistentObject {

    @ManyToOne
    private Person person;
    private String title;
    private int clicks;

    public Photo() {
        super(0);
    }

    public Photo(long id, Person person, String title, int clicks) {
        super(id);
        this.person = person;
        this.title = title;
        this.clicks = clicks;
    }

    @JsonProperty
    public Person getPerson() {
        return person;
    }

    @JsonProperty
    public String getTitle() {
        return title;
    }

    @JsonProperty
    public int getClicks() {
        return clicks;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "person=" + person +
                ", title='" + title + '\'' +
                ", clicks=" + clicks +
                '}';
    }
}
