package com.ever.br.api.control.gamer.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PlayerRequestDto {

    @NotEmpty(message = "{campo.nick.obrigatorio}")
    private String nick;

    @NotNull(message = "{campo.nivel.obrigatorio}")
    private Integer nivel;

    @NotNull(message = "{campo.power.obrigatorio}")
    private BigDecimal power;

    @NotNull(message = "{campo.codex.obrigatorio}")
    private Integer qtdCodex;

    @NotNull(message = "{campo.guild.obrigatorio}")
    private Long guild;

    @NotNull(message = "{campo.classe.obrigatorio}")
    private Long classe;

    @NotNull(message = "{campo.alt.obrigatorio}")
    private Boolean alt;

    @NotNull(message = "{campo.principal.obrigatorio}")
    private Boolean principal;

}
