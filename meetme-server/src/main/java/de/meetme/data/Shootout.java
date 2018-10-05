package de.meetme.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import java.util.List;


@Entity
public class Shootout extends PersistentObject {
    // create table person ( id int, name varchar(256), firstname varchar(256), email varchar(256))

    private String name;
    private List teilnehmer;
    private List ranking;
    private int winner;

    public Shootout() {
        // Needed by Jackson deserialization
        super(0);
    }


    public Shootout(long id, String name, List teilnehmer, List ranking, int winner) {
        super(id);
        this.name = name;
        this.teilnehmer = teilnehmer;
        this.ranking = ranking;
        this.winner = winner;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public List getTeilnehmer() {
        return teilnehmer;
    }

    @JsonProperty
    public List getRanking() {
        return ranking;
    }

    @JsonProperty
    public int getWinner() {
        return winner;
    }

    @JsonProperty
    public void setWinner(int winner) {
        this.winner = winner;
    }


    @Override
    public String toString() {
        return "Shootout{" +
                "name='" + name + '\'' +
                ", teilnehmer=" + teilnehmer +
                ", ranking=" + ranking +
                ", winner=" + winner +
                '}';
    }
}




