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
    private int wins;

    public Photo() {
        super(0);
    }

    public Photo(long id, Person person, String title, int clicks, int wins) {
        super(id);
        this.person = person;
        this.title = title;
        this.clicks = clicks;
        this.wins = wins;
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

    @JsonProperty
    public int getWins() {
        return wins;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "person=" + person +
                ", title='" + title + '\'' +
                ", clicks=" + clicks +
                ", wins=" + wins +
                '}';
    }
}
