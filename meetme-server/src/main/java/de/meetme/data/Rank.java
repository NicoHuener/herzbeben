package de.meetme.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Rank extends PersistentObject{

    @ManyToOne
    private Shootout shootout;
    @ManyToOne
    private Photo photo;
    private int points;

    public Rank() {
        super(0);
    }

    public Rank(Shootout shootout, int points, Photo photo ) {
        super(0);
        this.shootout = shootout;
        this.points = points;
        this.photo = photo;
    }

    @JsonProperty
    public Shootout getShootout() {
        return shootout;
    }
    @JsonProperty
    public int getPoints() {
        return points;
    }

    @JsonProperty
    public Photo getPhoto() {
        return photo;
    }

    @Override
    public String toString() {
        return "Rank{" +
                "shootout=" + shootout +
                ", points='" + points + '\'' +
                ", photo=" + photo +
                '}';
    }
}
