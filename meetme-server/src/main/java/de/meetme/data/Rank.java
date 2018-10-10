package de.meetme.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Rank extends PersistentObject{

    @ManyToOne
    private Shootout shootout;
    private int points;

    public Rank() {
        super(0);
    }

    public Rank(Shootout shootout, int points ) {
        super(0);
        this.shootout = shootout;
        this.points = points;
    }

    @JsonProperty
    public Shootout getShootout() {
        return shootout;
    }
    @JsonProperty
    public int getPoints() {
        return points;
    }
}
