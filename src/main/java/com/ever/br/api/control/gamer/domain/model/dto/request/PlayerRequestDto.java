package com.ever.br.api.control.gamer.domain.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PlayerRequestDto {

    @NotBlank
    private String nick;

    @NotNull
    private Integer level;

    @NotNull
    private BigDecimal power;

    @NotNull
    private Integer qtdCodex;

    @NotNull
    private Long claId;
}
