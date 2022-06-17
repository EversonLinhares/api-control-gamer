package com.ever.br.apicontas.controller;

import com.ever.br.apicontas.model.dto.request.UserRequestDto;
import com.ever.br.apicontas.model.dto.response.UserResponseDto;
import com.ever.br.apicontas.model.entity.User;
import com.ever.br.apicontas.repository.UserRepository;
import com.ever.br.apicontas.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository repository;
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> create (@Valid @RequestBody UserRequestDto userRequestDto){
        return ResponseEntity.ok().body(modelMapper
                .map(userService.create(userRequestDto), UserResponseDto.class));
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
          List<User> list = repository.findAll();
           return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(modelMapper
                .map(userService.findById(id), UserResponseDto.class));
    }

}
