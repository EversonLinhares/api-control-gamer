package com.ever.br.api.control.gamer.domain.model.dto.request;

import com.ever.br.api.control.gamer.domain.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserRequestDto {
   private String username;
   private String password;
   private String email;

    public UserRequestDto(User p) {
    }
}
