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
    //private byte [] picture;
    private String picture;
    private String category;


    public Photo() {
        super(0);
    }

   // public Photo(Person person, String title, int clicks, int wins, byte[] picture) {
    public Photo(Person person, String title, int clicks, int wins, String picture,String category) {
        super(0);
        this.person = person;
        this.title = title;
        this.clicks = clicks;
        this.wins = wins;
        this.picture = picture;
        this.category = category;
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
    public String getCategory() {
        return category;
    }

   /* @JsonProperty
    public byte[] getPicture() {
        return picture;
    }*/

    @JsonProperty
    public String getPicture() {
        return picture;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "person=" + person +
                ", title='" + title + '\'' +
                ", clicks=" + clicks + '\''+
                ", wins=" + wins +'\''+
                ", category='" + category + '\'' +
                '}';
    }
}