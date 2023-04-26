package com.ever.br.api.control.gamer.api.controller;

import com.ever.br.api.control.gamer.api.dto.request.ClasseRequestDto;
import com.ever.br.api.control.gamer.api.dto.response.ClasseResponseDto;
import com.ever.br.api.control.gamer.domain.model.entity.Classe;
import com.ever.br.api.control.gamer.domain.service.ClasseService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/classe")
public class ClasseController {

    @Autowired
    ClasseService classeService;

    @GetMapping
    public ResponseEntity<List<ClasseResponseDto>> findAll(){
        List<ClasseResponseDto> listaClasse = classeService.findAll();
        if(listaClasse.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(listaClasse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClasseResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(classeService.findById(id));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Classe cadastrada com sucesso!"),
            @ApiResponse(responseCode = "400",description = "Requisição inválida.")
    })
    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody ClasseRequestDto classeRequestDto){
         Classe classe = classeService.create(classeRequestDto);
        URI headerLocation = UriComponentsBuilder.fromUriString("/classe/{id}")
                .buildAndExpand(classe.getId())
                .toUri();
        return ResponseEntity.created(headerLocation).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteClasse(@PathVariable Long id) {
        classeService.deleteClasse(id);
        return ResponseEntity.ok().build();
    }
}
