package com.ever.br.api.control.gamer.api.controller;

import com.ever.br.api.control.gamer.domain.model.dto.request.UserRequestDto;
import com.ever.br.api.control.gamer.domain.model.dto.response.UserResponseDto;
import com.ever.br.api.control.gamer.domain.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController  {
    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> create (@Valid @RequestBody UserRequestDto userRequestDto) throws NoSuchAlgorithmException {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(userRequestDto));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll(){
           return ResponseEntity.ok().body(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(userService.findById(id));
    }
    @PutMapping("/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody UserRequestDto user) {
        userService.updateUser(id,user);
    }
}
