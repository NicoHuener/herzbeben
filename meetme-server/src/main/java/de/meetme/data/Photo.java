package de.meetme.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Photo extends PersistentObject {

    @ManyToOne
    private Person person;
    @Column(nullable = false)
    private String title;
    private int clicks;
    private int wins;
    @Lob
    private byte[] picture;

    public Photo() {
        super(0);
    }

    public Photo(Person person, String title, int clicks, int wins, byte[] picture) {
        super(0);
        this.person = person;
        this.title = title;
        this.clicks = clicks;
        this.wins = wins;
        this.picture = picture;
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

    @JsonProperty
    public byte[] getPicture() {
        return picture;
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