package com.ever.br.apicontas.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class UserResponseDto {

    private String username;
    private String password;
    private String cpf;
    private String email;
}
