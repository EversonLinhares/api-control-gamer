package com.ever.br.api.control.gamer.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.Data;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class EnvioEmail implements Serializable {

    private String ownerRef = "4Ever Service app";
    private String emailFrom;
    private List<String> emailTo;
    private String subject;
    private String text;


    public static ResponseEntity<ResponseEntity<EnvioEmail>> enviar (String emailTo, String text, String subject){
        List<String> emailsTo = new ArrayList<>();
        String url = "http://localhost:8080/email";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        EnvioEmail email = new EnvioEmail();
        emailsTo.add(emailTo);
        email.setEmailTo(emailsTo);
        email.setOwnerRef(email.getOwnerRef());
        email.setText(text);
        email.setSubject("teste ever");
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = "";
        try {
            json = ow.writeValueAsString(email);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        HttpEntity<String> request = new HttpEntity<>(json,headers);
        ResponseEntity<EnvioEmail> response = restTemplate.postForEntity(url,request, EnvioEmail.class);
        return ResponseEntity.ok(response);
    }

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
