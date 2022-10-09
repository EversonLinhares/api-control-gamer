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
@CrossOrigin("*")
public class PlayerController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PlayerService playerService;

    @PostMapping
    public ResponseEntity<PlayerResponseDto> create (@Valid @RequestBody PlayerRequestDto player) {
        return ResponseEntity.status(HttpStatus.CREATED).body((playerService.create(player)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body((playerService.findById(id)));
    }

    @GetMapping("/all")
    public ResponseEntity<List<PlayerResponseDto>> findAll(){
        return ResponseEntity.ok().body(playerService.findAll());
    }

    @GetMapping
    public ResponseEntity<List<PlayerResponseDto>> findAllPersonToUser(){
        return ResponseEntity.ok().body(playerService.findAllPersonToUser());
    }

//    @GetMapping
//    public ResponseEntity<Page<PlayerResponseDto>> findAll(
//            @RequestParam(value = "nick",required = false) String nick,
//            @RequestParam(name = "page", defaultValue = "0") int page,
//            @RequestParam(name = "size", defaultValue = "30") int size,
//            @RequestParam(name = "sort", defaultValue = "descricao") String sort
//    ) {
//        Pageable pagina = PageRequest.of(page,size, Sort.by(sort));
//        List<PlayerResponseDto> players = playerService.findAll(nick);
//        Page<PlayerResponseDto> pagePlayers = new PageImpl(players,pagina,players.size());
//        if (players.isEmpty()){
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//        }
//        return ResponseEntity.ok().body(pagePlayers);
//    }

    @PutMapping("/{id}")
    public void updatePlayer(@Valid @PathVariable Long id, @RequestBody PlayerRequestDto playerRequestDto) {
        playerService.updatePlayer(id,playerRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable Long id){
        playerService.deletePlayer(id);
    }
}
