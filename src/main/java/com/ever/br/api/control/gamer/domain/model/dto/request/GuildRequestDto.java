package com.ever.br.api.control.gamer.domain.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GuildRequestDto {

    @NotBlank
    private String name;

    private Integer level;
}
