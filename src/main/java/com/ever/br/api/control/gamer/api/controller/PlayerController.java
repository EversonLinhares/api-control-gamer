package com.ever.br.api.control.gamer.api.controller;

import com.ever.br.api.control.gamer.domain.model.dto.request.PlayerRequestDto;
import com.ever.br.api.control.gamer.domain.model.dto.response.PlayerResponseDto;
import com.ever.br.api.control.gamer.domain.service.PlayerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PlayerService playerService;

    @PostMapping
    public ResponseEntity<PlayerResponseDto> create (@Valid @RequestBody PlayerRequestDto player) {
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper
                .map(playerService.create(player), PlayerResponseDto.class));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(modelMapper
                .map(playerService.findById(id), PlayerResponseDto.class));
    }

    @GetMapping
    public ResponseEntity<List<PlayerResponseDto>> findAll() {
        return ResponseEntity.ok().body(playerService.findAll());
    }

    @PutMapping("/{id}")
    public void updatePlayer(@Valid @PathVariable Long id, @RequestBody PlayerRequestDto playerRequestDto) {
        playerService.updatePlayer(id,playerRequestDto);
    }


}
