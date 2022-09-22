package com.ever.br.api.control.gamer.api.controller;

import com.ever.br.api.control.gamer.domain.model.dto.request.ClaRequestDto;
import com.ever.br.api.control.gamer.domain.model.dto.response.ClaResponseDto;
import com.ever.br.api.control.gamer.domain.service.ClaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cla")
public class ClaController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ClaService claService;

    @PostMapping
    public ResponseEntity<ClaResponseDto> create (@Valid @RequestBody ClaRequestDto cla) {
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(claService.create(cla), ClaResponseDto.class));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClaResponseDto> findById (@PathVariable Long id) {
       return ResponseEntity.ok().body(modelMapper.map(claService.findById(id), ClaResponseDto.class));
    }

    @GetMapping
    public List<ClaResponseDto> findAll() {
        return claService.findAll();
    }

    @PutMapping("/{id}")
    public void updateCla (@PathVariable Long id ,@RequestBody ClaRequestDto claRequestDto) {
         claService.updateCla(id,claRequestDto);
    }


}
