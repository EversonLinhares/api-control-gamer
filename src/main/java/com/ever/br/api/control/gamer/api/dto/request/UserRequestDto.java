package com.ever.br.api.control.gamer.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserRequestDto {
   @NotEmpty(message = "{campo.login.obrigatorio}")
   private String username;
   @NotEmpty(message = "{campo.senha.obrigatorio}")
   private String password;
   @NotEmpty(message = "{campo.email.obrigatorio}")
   private String email;


}
