package com.ever.br.api.control.gamer.api.dto.request;

import com.ever.br.api.control.gamer.domain.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
