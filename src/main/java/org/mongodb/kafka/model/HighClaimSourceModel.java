package org.mongodb.kafka.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;

import java.util.Date;

public final class HighClaimSourceModel {

    @JsonProperty("_id")
    private ObjectId _id;

    @JsonProperty("operationType")
    private String operationType;

    @JsonProperty("fullDocument")
    private FullDocument fullDocument;

    @JsonProperty("ns")
    private NS ns;

    @JsonProperty("documentKey")
    private DocumentKey documentKey;

    @JsonProperty("route")
    private String route;

    @JsonProperty("routeProcessDate")
    private java.util.Date routeProcessDate;

    public HighClaimSourceModel() {

    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public FullDocument getFullDocument() {
        return fullDocument;
    }

    public void setFullDocument(FullDocument fullDocument) {
        this.fullDocument = fullDocument;
    }

    public NS getNs() {
        return ns;
    }

    public void setNs(NS ns) {
        this.ns = ns;
    }

    public DocumentKey getDocumentKey() {
        return documentKey;
    }

    public void setDocumentKey(DocumentKey documentKey) {
        this.documentKey = documentKey;
    }

    public Date getRouteProcessDate() {
        return routeProcessDate;
    }

    public void setRouteProcessDate(Date routeProcessDate) {
        this.routeProcessDate = routeProcessDate;
    }

    @Override
    public String toString() {
        return "HighClaimSourceModel{" +
                "_id='" + _id + '\'' +
                ", operationType='" + operationType + '\'' +
                ", fullDocument=" + fullDocument +
                ", ns=" + ns +
                ", documentKey=" + documentKey +
                ", route='" + route + '\'' +
                ", routeProcessDate=" + routeProcessDate +
                '}';
    }
}
