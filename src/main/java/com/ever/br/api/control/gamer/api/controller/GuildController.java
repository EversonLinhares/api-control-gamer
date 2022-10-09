package com.ever.br.api.control.gamer.api.controller;

import com.ever.br.api.control.gamer.domain.model.dto.request.GuildRequestDto;
import com.ever.br.api.control.gamer.domain.model.dto.response.GuildResponseDto;
import com.ever.br.api.control.gamer.domain.service.GuildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/guild")
@CrossOrigin("*")
public class GuildController {

    @Autowired
    GuildService guildService;

    @PostMapping
    public ResponseEntity<GuildResponseDto> create (@Valid @RequestBody GuildRequestDto cla) {
        return ResponseEntity.status(HttpStatus.CREATED).body(guildService.create(cla));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GuildResponseDto> findById (@PathVariable Long id) {
       return ResponseEntity.ok().body(guildService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<GuildResponseDto>> findAll() {
        return ResponseEntity.ok().body(guildService.findAll());
    }

    @PutMapping("/{id}")
    public void updateCla (@PathVariable Long id ,@RequestBody GuildRequestDto guildRequestDto) {
         guildService.updateCla(id, guildRequestDto);
    }
}
