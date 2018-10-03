package de.meetme.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import java.util.List;
import java.util.Queue;
import java.util.Random;

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

    public String getName() {
        return name;
    }

    public List getTeilnehmer() {
        return teilnehmer;
    }

    public List getRanking() {
        return ranking;
    }

    public int getWinner() {
        return winner;
    }

    public int compareGeneral (){
        Queue compare = getQueue();
        int pic1 = compare.element();
        compare.remove();
        while (!compare.isEmpty()) {
            int pic2 = compare.element();
            compare.remove();
            //*Die ersten beiden Bilder werden angezeigt* --> HTML?
            // Der Benutzer wählt ein Bild aus (pic 1 oder pic 2)
            //Verlierer wird in raking gepackt
            //gewinnerbild wird zu pic 1
            // neuer vergleich
        }
        return gewinner;
    }

    private Queue getQueue () {
        Queue q1 = new Queue();
        List l1 = teilnehmer;
        while (!l1.isEmpty()) {
            int random = l1.get(new Random().nextInt(l1.size()));
            q1.add(random);
            l1.remove(random);
        }
        return q1;
    }

    public Photo compareSpecific (Photo p1, Photo p2){

        if (Warteschlange isEmpty()){
            return Gewinnerbild
        }
else{
	*Benutzer wählt ein Foto aus*
                    Verlierer wird in Verliererarry gepackt
            return compare(Gewinnerbild, nächstes Bild aus der Warteschlange)
        }
}


