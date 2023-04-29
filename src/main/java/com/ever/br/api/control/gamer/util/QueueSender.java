package com.ever.br.api.control.gamer.util;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class QueueSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queue;

    @Value("${owner.ref: 4Ever Service app }")
    private String ownerRef;

    @Value("${email.from: api-control-gamer@gmail.com.br}")
    private String emailFrom;

    public Email montarEmailEnvio(String emailTo, String titulo, String texto) {
        Email email = new Email();
        email.setOwnerRef(ownerRef);
        email.setEmailFrom(emailFrom);
        email.setEmailTo(List.of(emailTo));
        email.setText(texto);
        email.setSubject(titulo);
        return email;
    }

    public void send(Object order) {
        rabbitTemplate.convertAndSend(this.queue.getName(),order);
    }
}
