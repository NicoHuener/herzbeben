package de.meetme.data.Bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.meetme.data.PersistentObject;
import de.meetme.data.Photo;

public class BestPhotoBean extends PersistentObject {

    private Photo photo;
    private int gesamtPunktzahl;

    public BestPhotoBean() {// Needed by Jackson deserialization
        super(0);
    }

    public BestPhotoBean(Photo photo, int gesamtPunktzahl) {
        super(0);
        this.photo = photo;
        this.gesamtPunktzahl = gesamtPunktzahl;
    }

    @JsonProperty
    public Photo getPhoto() {
        return photo;
    }

    @JsonProperty
    public int getGesamtPunktzahl() {
        return gesamtPunktzahl;
    }
}

