package org.mongodb.kafka.utils;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.apache.kafka.common.serialization.Deserializer;
import org.mongodb.kafka.model.HighClaimSourceModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class KafkaJsonDeserializer<T> implements Deserializer {

    private static final Logger log = LoggerFactory.getLogger(KafkaJsonDeserializer.class);

    private Class <T> type;

    public KafkaJsonDeserializer(Class type) {
        this.type = type;
    }

    @Override
    public void configure(Map map, boolean b) {

    }

    @Override
    public Object deserialize(String s, byte[] bytes) {

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(HighClaimSourceModel.class, new HighClaimSourceDeserializer());
        mapper.registerModule(simpleModule);

        T obj = null;
        try {
            obj = mapper.readValue(bytes, type);

        } catch (Exception e) {
            log.error("===DESERIALIZATION ERROR=====");
            log.error(new String(bytes));
            log.error(e.getMessage());
        }
        return obj;
    }

    @Override
    public void close() {

    }
}
