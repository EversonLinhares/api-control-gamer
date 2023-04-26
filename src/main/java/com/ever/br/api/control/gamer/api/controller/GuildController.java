package com.ever.br.api.control.gamer.api.controller;

import com.ever.br.api.control.gamer.api.dto.request.GuildRequestDto;
import com.ever.br.api.control.gamer.api.dto.response.GuildResponseDto;
import com.ever.br.api.control.gamer.domain.model.entity.Guild;
import com.ever.br.api.control.gamer.domain.service.GuildService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/guild")
public class GuildController {

    @Autowired
    GuildService guildService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Clã salvo com sucesso!"),
            @ApiResponse(responseCode = "400",description = "requisição inválida!")
    })
    @PostMapping
    public ResponseEntity<Void> create (@Valid @RequestBody GuildRequestDto claRequestDto) {
        Guild guild = guildService.create(claRequestDto);
        URI headerLocation = UriComponentsBuilder.fromUriString("/guild/{id}")
                .buildAndExpand(guild.getId())
                .toUri();
        return ResponseEntity.created(headerLocation).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GuildResponseDto> findById (@PathVariable Long id) {
       return ResponseEntity.ok().body(guildService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<GuildResponseDto>> findAll() {
        List<GuildResponseDto> listGuild = guildService.findAll();
        if(listGuild.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(guildService.findAll());
    }

    @PutMapping("/{id}")
    public void updateCla (@PathVariable Long id ,@RequestBody GuildRequestDto guildRequestDto) {
         guildService.updateCla(id, guildRequestDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCla(@PathVariable Long id) {
        guildService.deleteCla(id);
        return ResponseEntity.ok().build();
    }
}
