package com.ever.br.api.control.gamer.api.controller;

import com.ever.br.api.control.gamer.api.dto.request.UserRequestDto;
import com.ever.br.api.control.gamer.api.dto.response.UserResponseDto;
import com.ever.br.api.control.gamer.domain.model.entity.User;
import com.ever.br.api.control.gamer.domain.service.UserService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController  {
    @Autowired
    UserService userService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Usuario cadastrado com sucesso!"),
            @ApiResponse(responseCode = "400",description = "requisição inválida!")
    })
    @PostMapping
    public ResponseEntity<Void> create (
            @Valid @RequestBody UserRequestDto userRequestDto) throws NoSuchAlgorithmException {
        User user = userService.create(userRequestDto);
        URI headerLocation = UriComponentsBuilder.fromUriString("/users/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(headerLocation).build();
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

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
