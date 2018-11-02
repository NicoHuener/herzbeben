package de.meetme.data.Bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.meetme.data.PersistentObject;

public class PhotoUploadBean extends PersistentObject {

    private long userId;
    private String title;
    private String picture;
    private String category;

    public PhotoUploadBean() {// Needed by Jackson deserialization
        super(0);
    }

    public PhotoUploadBean(long userId, String title, String picture, String category) {
        super(0);
        this.userId = userId;
        this.title = title;
        this.picture = picture;
        this.category = category;
    }

    @JsonProperty
    public long getUserId() {
        return userId;
    }

    @JsonProperty
    public String getTitle() {
        return title;
    }

    @JsonProperty
    public String getPicture() {
        return picture;
    }

    @JsonProperty
    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "PhotoUploadBean{" +
                "userId=" + userId +
                ", title='" + title + '\'' +
                ", picture='" + picture + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
