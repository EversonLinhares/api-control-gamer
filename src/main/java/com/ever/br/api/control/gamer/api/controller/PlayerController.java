package com.ever.br.api.control.gamer.api.controller;

import com.ever.br.api.control.gamer.api.dto.request.PlayerRequestDto;
import com.ever.br.api.control.gamer.api.dto.response.PlayerResponseDto;
import com.ever.br.api.control.gamer.domain.model.Player;
import com.ever.br.api.control.gamer.domain.service.PlayerService;
import com.ever.br.api.control.gamer.util.GetUser;
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
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Player cadastrado com sucesso!"),
            @ApiResponse(responseCode = "400",description = "requisição inválida!")
    })
    @PostMapping
    public ResponseEntity<Void> create (@RequestBody PlayerRequestDto playerRequestDto) {
        Player player = playerService.create(playerRequestDto);
        URI headerLocation = UriComponentsBuilder.fromUriString("/player/{id}")
                .buildAndExpand(player.getId())
                .toUri();
        return ResponseEntity.created(headerLocation).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body((playerService.findById(id)));
    }

    @GetMapping("/all")
    public ResponseEntity<List<PlayerResponseDto>> findAll(
            @RequestParam(value = "nick",required = false) String nick,
            @RequestParam(value = "level",required = false) Long level,
            @RequestParam(value = "power",required = false) Long power,
            @RequestParam(value = "qtdCodex",required = false) Long qtdCodex) {
        GetUser userRole = new GetUser();
        if(userRole.getCurrentUserRole().equalsIgnoreCase("ROLE_USER")){
            return ResponseEntity.ok().body(playerService.findAllPersonToUser());
        }
        return ResponseEntity.ok().body(playerService.findAll(nick,level,power,qtdCodex));
    }

    @PutMapping("/{id}")
    public void updatePlayer(@Valid @PathVariable Long id, @RequestBody PlayerRequestDto playerRequestDto) {
        playerService.updatePlayer(id,playerRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable Long id){
        playerService.deletePlayer(id);
    }
}
