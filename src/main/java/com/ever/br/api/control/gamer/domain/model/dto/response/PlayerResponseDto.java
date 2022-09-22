package com.ever.br.api.control.gamer.domain.model.dto.response;

import com.ever.br.api.control.gamer.domain.model.entity.Cla;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PlayerResponseDto {


    private String nick;

    private Integer level;

    private BigDecimal power;

    private Integer qtdCodex;

    private ClaResponseDto cla;
}
