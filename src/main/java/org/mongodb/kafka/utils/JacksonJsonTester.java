package org.mongodb.kafka.utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.types.ObjectId;
import org.mongodb.kafka.model.DocumentKey;
import org.mongodb.kafka.model.FullDocument;
import org.mongodb.kafka.model.HighClaimSourceModel;
import org.mongodb.kafka.model.NS;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JacksonJsonTester {
    HexStringConverter hexStringConverter = new HexStringConverter();

    public JacksonJsonTester() {
        this.init();
    }

    private void init() {
        System.out.println("Init Jackson Json Tester.");
        try {
            byte[] jsonFile =
                    Files.readAllBytes(Paths.get("/Users/bmincey/IdeaProjects/kafka-consumer/sample/sample.json"));

            this.deserialize(jsonFile);
        } catch (IOException ioe) {
            System.out.println(ioe.toString());
        }
    }

    private void deserialize(byte[] json) throws IOException, JsonProcessingException{
        System.out.println("Begin deserialization.");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.readTree(json);

        HighClaimSourceModel highClaimSourceModel = new HighClaimSourceModel();

        // operation type
        final String operationType = node.get("operationType").toString();
        highClaimSourceModel.setOperationType(operationType.replace("\"", ""));

        // Full Document
        FullDocument fullDocument = new FullDocument();

        // need to convert to objectid and make sure it is valid
        String _fdId = node.at("/fullDocument/_id/$oid").toString();

        // get rid of the quotes in the string
        _fdId = _fdId.replace("\"", "");
        byte[] _fdIdBytes = hexStringConverter.decodeHexString(_fdId);
        String _fdIdHexString = hexStringConverter.encodeHexString(_fdIdBytes);
        if(ObjectId.isValid(_fdIdHexString)) {
            fullDocument.set_id(new ObjectId(_fdIdHexString));
        } else {
            System.out.println("Invalid ObjectId for Full Document: " + _fdIdHexString);
        }

        String city = node.at("/fullDocument/city").toString();
        fullDocument.setCity(city.replace("\"", ""));

        Double claimAmount = node.at("/fullDocument/claimAmount/$numberDecimal").asDouble();
        fullDocument.setClaimAmount(claimAmount);

        Long dateClaimSubmittedLong = node.at("/fullDocument/dateClaimSubmitted/$date").asLong();
        java.util.Date dateClaimSubmitted = new java.util.Date(dateClaimSubmittedLong);
        fullDocument.setDateClaimSubmitted(dateClaimSubmitted);

        String email = node.at("/fullDocument/email").toString();
        fullDocument.setEmail(email.replace("\"", ""));

        String employer = node.at("/fullDocument/employer").toString();
        fullDocument.setEmployer(employer.replace("\"", ""));

        String gender = node.at("/fullDocument/gender").toString();
        fullDocument.setGender(gender.replace("\"", ""));

        String healthProvider = node.at("/fullDocument/healthProvider").toString();
        fullDocument.setHealthProvider(healthProvider.replace("\"", ""));

        String maritalStatus = node.at("/fullDocument/maritalStatus").toString();
        fullDocument.setMaritalStatus(maritalStatus.replace("\"", ""));

        String name = node.at("/fullDocument/name").toString();
        fullDocument.setName(name.replace("\"", ""));

        String phoneNumber = node.at("/fullDocument/phoneNumber").toString();
        fullDocument.setPhoneNumber(phoneNumber.replace("\"", ""));

        String state = node.at("/fullDocument/state").toString();
        fullDocument.setState(state.replace("\"", ""));

        String streetAddress = node.at("/fullDocument/streetAddress").toString();
        fullDocument.setStreetAddress(streetAddress.replace("\"", ""));

        String title = node.at("/fullDocument/title").toString();
        fullDocument.setTitle(title.replace("\"", ""));

        String zip = node.at("/fullDocument/zip").toString();
        fullDocument.setZip(zip.replace("\"", ""));

        String claimType = node.at("/fullDocument/claimType").toString();
        fullDocument.setClaimType(claimType.replace("\"", ""));

        // Set fullDocument into model
        highClaimSourceModel.setFullDocument(fullDocument);

        // create ns object
        NS ns = new NS();

        String db = node.at("/ns/db").toString();
        ns.setDb(db.replace("\"", ""));

        String coll = node.at("/ns/coll").toString();
        ns.setColl(coll.replace("\"", ""));

        // set ns into high claim model
        highClaimSourceModel.setNs(ns);

        // document key object
        DocumentKey documentKey = new DocumentKey();

        String _dcId = node.at("/documentKey/_id/$oid").toString();
        // get rid of the quotes in the string
        _dcId = _dcId.replace("\"", "");
        byte[] _dcIdBytes = hexStringConverter.decodeHexString(_dcId);
        String _dcIdHexString = hexStringConverter.encodeHexString(_dcIdBytes);
        if(ObjectId.isValid(_dcIdHexString)) {
            fullDocument.set_id(new ObjectId(_dcIdHexString));
        } else {
            System.out.println("Invalid ObjectId for Document Key: " + _dcIdHexString);
        }
        documentKey.set_id(new ObjectId(_dcIdHexString));

        highClaimSourceModel.setDocumentKey(documentKey);

        System.out.println(highClaimSourceModel.toString());
    }

    public static void main(String[] args) {
        JacksonJsonTester jacksonJsonTester = new JacksonJsonTester();
    }
}
