package org.mongodb.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.mongodb.kafka.model.HighClaimSourceModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class HighClaimConsumer {

    private static final Logger log = LoggerFactory.getLogger(HighClaimConsumer.class);

    @Autowired
    private PostHighClaimRestServer restServer;

    @KafkaListener( topics = "${spring.kafka.template.default-topic}",
                    groupId = "${spring.kafka.consumer.group-id}",
                    containerFactory = "highClaimKafkaListenerContainerFactory")
    public void consumeHighClaimDocs(@Payload HighClaimSourceModel highClaimSourceModel) {
        log.info("==> Consume high claim document");

        log.info(highClaimSourceModel.toString());

        log.info("Prepare to post document to rest server.");
        this.restServer.postHighClaimDocument(highClaimSourceModel);
    }
}
