package de.meetme.data;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Person extends PersistentObject {
    // create table person ( id int, name varchar(256), firstname varchar(256), email varchar(256))

    @Column (nullable = false)
    private String name;
    @Column (nullable = false)
    private String firstName;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true, nullable = false)
    private String username;
    @Column (nullable = false)
    private String password;
    @Column(name = "treffer", columnDefinition = "int default 0")
    private int treffer = 0;
    @Column(name = "keintreffer", columnDefinition = "int default 0")
    private int keinTreffer = 0;

    public Person() {// Needed by Jackson deserialization
        super(0);
    }

    public Person(String name, String firstName, String email, String username, String password) {
        super(0);
        this.name = name;
        this.firstName = firstName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.treffer = 0;
        this.keinTreffer = 0;
    }

    public Person(String name, String firstName, String email, String username, String password, int treffer, int keinTreffer) {
        super(0);
        this.name = name;
        this.firstName = firstName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.treffer = treffer;
        this.keinTreffer = keinTreffer;
    }

    @JsonProperty
    public String getUsername() {
        return username;
    }

    @JsonProperty
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public String getEmail() {
        return email;
    }

    @JsonProperty
    public int getTreffer() {
        return treffer;
    }

    @JsonProperty
    public int getKeinTreffer() {
        return keinTreffer;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
