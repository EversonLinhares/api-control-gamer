package com.ever.br.api.control.gamer.domain.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GuildResponseDto {

    private Long id;
    private String name;
    private Integer nivel;
}
