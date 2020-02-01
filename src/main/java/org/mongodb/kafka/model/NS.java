package org.mongodb.kafka.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NS {


    @JsonProperty("db")
    private String db;

    @JsonProperty("coll")
    private String coll;

    public NS() {

    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getColl() {
        return coll;
    }

    public void setColl(String coll) {
        this.coll = coll;
    }

    @Override
    public String toString() {
        return "NS{" +
                "db='" + db + '\'' +
                ", coll='" + coll + '\'' +
                '}';
    }
}
