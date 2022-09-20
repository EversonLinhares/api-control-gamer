package com.ever.br.apicontas.domain.model.dto.request;

import com.ever.br.apicontas.domain.enums.RoleNameEnums;
import com.ever.br.apicontas.domain.model.entity.Role;
import com.ever.br.apicontas.domain.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
