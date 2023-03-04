package com.ever.br.api.control.gamer.util;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class EnvioEmail implements Serializable{
    private static final long serialVersionUID = 1L;

    private String ownerRef = "4Ever Service app";
    private String emailFrom = "api-control-gamer@gmail.com.br";
    private List<String> emailTo;
    private String subject;
    private String text;

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
