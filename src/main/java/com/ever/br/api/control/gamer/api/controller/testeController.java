package com.ever.br.api.control.gamer.api.controller;


import com.ever.br.api.control.gamer.util.EnvioEmail;
import com.ever.br.api.control.gamer.util.QueueSender;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teste")
public class testeController {

    @Autowired
    private QueueSender queueSender;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${queue.name}")
    private Queue queue;

    @GetMapping
    public ResponseEntity<EnvioEmail> send(){
        EnvioEmail email = new EnvioEmail();
        email.setEmailTo(List.of("everson.linhares@supera.com.br"));
        email.setSubject("testen ever");
        email.setText("teste ever");
        queueSender.send(email);
        return ResponseEntity.ok().build();
    }

}
