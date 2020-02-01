package org.mongodb.kafka.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;

public class DocumentKey {

    @JsonProperty("_id")
    private ObjectId _id;

    public DocumentKey() {

    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    @Override
    public String toString() {
        return "DocumentKey{" +
                "_id=" + _id +
                '}';
    }
}
