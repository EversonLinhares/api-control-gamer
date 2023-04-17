package com.ever.br.api.control.gamer.api.controller;

import com.ever.br.api.control.gamer.api.dto.request.ClasseRequestDto;
import com.ever.br.api.control.gamer.api.dto.response.ClasseResponseDto;
import com.ever.br.api.control.gamer.domain.model.entity.Classe;
import com.ever.br.api.control.gamer.domain.service.ClasseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/classe")
public class ClasseController {

    @Autowired
    ClasseService classeService;

    @GetMapping
    public ResponseEntity<List<ClasseResponseDto>> findAll(){
        return ResponseEntity.ok().body(classeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Classe> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(classeService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ClasseResponseDto> create(@Valid @RequestBody ClasseRequestDto classe){
      return ResponseEntity.status(HttpStatus.CREATED).body(classeService.create(classe));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteClasse(@PathVariable Long id) {
        classeService.deleteClasse(id);
        return ResponseEntity.ok().build();
    }
}
