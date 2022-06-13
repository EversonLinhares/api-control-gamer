package com.ever.br.apicontas.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.net.PasswordAuthentication;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @NotBlank(message = "Preencha o nome.")
    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @CPF(message = "O cpf é invalido!")
    @Column(name = "cpf",unique = true)
    private String cpf;

    @Email(message = "O e-mail é inválido!")
    @NotBlank(message = "Preencha o e-mail.")
    @Column(name = "email")
    private String email;
}
