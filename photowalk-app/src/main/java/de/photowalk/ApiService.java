package de.photowalk;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

public interface ApiService {

    @GET("/api/getDummieContent")
    public void getDummieContent(Callback<Person> callback);

    // "/api/person/42"
    @GET("/api/person/{id}")
    public Person getPerson(@Path("id") Integer id);


}

