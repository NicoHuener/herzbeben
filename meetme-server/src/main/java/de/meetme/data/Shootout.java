package de.meetme.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Entity
public class Shootout extends PersistentObject {
    // create table person ( id int, name varchar(256), firstname varchar(256), email varchar(256))

    private String name;
    @ManyToOne
    private Person person;
    private int winner;

    public Shootout() {
        // Needed by Jackson deserialization
        super(0);
    }

    public Shootout(String name,Person person, int winner) {
        super(0);
        this.name = name;
        this.person = person;
        this.winner = winner;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public int getWinner() {
        return winner;
    }

    @JsonProperty
    public void setWinner(int winner) {
        this.winner = winner;
    }

    @JsonProperty
    public Person getPerson() {
        return person;
    }

    @Override
    public String toString() {
        return "Shootout{" +
                "name='" + name + '\'' +
                ", personid=" + person +
                ", winner=" + winner +
                '}';
    }
}
