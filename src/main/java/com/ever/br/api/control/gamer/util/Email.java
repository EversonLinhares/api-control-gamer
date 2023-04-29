package com.ever.br.api.control.gamer.util;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Email implements Serializable{
    private static final long serialVersionUID = 1L;

    private String ownerRef;
    private String emailFrom;
    private List<String> emailTo;
    private String subject;
    private String text;

    @Override
    public String toString() {
        return "Email{" +
                "ownerRef='" + ownerRef + '\'' +
                ", emailFrom='" + emailFrom + '\'' +
                ", emailTo=" + emailTo +
                ", subject='" + subject + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
