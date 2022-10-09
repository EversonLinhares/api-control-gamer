package com.ever.br.api.control.gamer.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.Data;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@Data
public class EnvioEmail implements Serializable{
    private static final long serialVersionUID = 1L;

    private String ownerRef = "4Ever Service app";
    private String emailFrom = "smtp_dev@supera.com.br";
    private List<String> emailTo;
    private String subject;
    private String text;



    //        String url = "http://localhost:8080/email";
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
    //        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//        String json = "";
//        try {
//            json = ow.writeValueAsString(email);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        HttpEntity<String> request = new HttpEntity<>(json,headers);
//        ResponseEntity<EnvioEmail> response = restTemplate.postForEntity(url,request, EnvioEmail.class);
    @Override
    public String toString() {
        return "EnvioEmail{" +
                "ownerRef='" + ownerRef + '\'' +
                ", emailFrom='" + emailFrom + '\'' +
                ", emailTo=" + emailTo +
                ", subject='" + subject + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
