package com.ever.br.api.control.gamer.api.dto.response;

import com.ever.br.api.control.gamer.domain.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {

    private Long id;
    private String username;
    private String email;
    private List<Role> roles;
    private Boolean ativo;

}
