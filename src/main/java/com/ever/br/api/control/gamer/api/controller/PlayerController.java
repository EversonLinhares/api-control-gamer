package com.ever.br.api.control.gamer.api.controller;

import com.ever.br.api.control.gamer.domain.model.dto.request.PlayerRequestDto;
import com.ever.br.api.control.gamer.domain.model.dto.response.PlayerResponseDto;
import com.ever.br.api.control.gamer.domain.service.PlayerService;
import com.ever.br.api.control.gamer.util.GetUser;
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
    public ResponseEntity<PlayerResponseDto> create (@RequestBody PlayerRequestDto player) {
        return ResponseEntity.status(HttpStatus.CREATED).body((playerService.create(player)));
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
