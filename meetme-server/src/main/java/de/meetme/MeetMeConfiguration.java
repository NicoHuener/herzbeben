package de.meetme;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.db.DataSourceFactory;
import org.hibernate.validator.constraints.NotEmpty;

public class MeetMeConfiguration extends Configuration {
    private DataSourceFactory dataSourceFactory;
    private String dbPort;

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return dataSourceFactory;
    }

    @JsonProperty("database")
    public void setDataSourceFactory(DataSourceFactory dataSourceFactory) {
        this.dataSourceFactory = dataSourceFactory;
    }

    @JsonProperty
    public String getDbPort() {
        return dbPort;
    }

    @JsonProperty
    public void setDbPort(String dbPort) {
        this.dbPort = dbPort;
    }
}