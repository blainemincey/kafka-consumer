package org.mongodb.kafka;

import org.apache.commons.codec.binary.Base64;
import org.mongodb.kafka.model.HighClaimSourceModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;


@Component
public class PostHighClaimRestServer {

    private static final Logger log = LoggerFactory.getLogger(PostHighClaimRestServer.class);

    @Value(value = "${spring.rest.post.url}")
    private String restUrl;

    @Value(value = "${spring.security.user.name}")
    private String restUserName;

    @Value(value = "${spring.security.user.password}")
    private String restPassword;

    /**
     *
     * @param highClaimSourceModel
     */
    public void postHighClaimDocument(HighClaimSourceModel highClaimSourceModel) {

        log.info("Post High Claim Document to Server.");

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity httpRequest =
                new HttpEntity(highClaimSourceModel,this.createHeaders(this.restUserName,this.restPassword));
        ResponseEntity<String> response =
                restTemplate.exchange(this.restUrl, HttpMethod.POST, httpRequest, String.class);

        log.info("Http Status Code  : " + response.getStatusCode());
        log.info("Http Status Value : " + response.getStatusCodeValue());

        String result = response.getBody();
        log.info("Post result: " + result);

    }

    /**
     *
     * @return
     */
    private HttpHeaders createHeaders(String username, String password){
        return new HttpHeaders() {{
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(Charset.forName("US-ASCII")) );
            String authHeader = "Basic " + new String( encodedAuth );
            set( "Authorization", authHeader );
            setContentType(MediaType.APPLICATION_JSON);
        }};
    }
}
