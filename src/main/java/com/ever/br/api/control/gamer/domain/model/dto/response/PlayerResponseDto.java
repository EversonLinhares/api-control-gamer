package com.ever.br.api.control.gamer.domain.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PlayerResponseDto {

    private Long id;

    private String nick;

    private Integer nivel;

    private BigDecimal power;

    private Integer qtdCodex;

    private GuildResponseDto guild;

    private ClasseResponseDto classe;

    private Boolean alt;

    private Boolean principal;

    private Boolean ativo;
}
