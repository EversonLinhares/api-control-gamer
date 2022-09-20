package com.ever.br.apicontas.api.controller;

import com.ever.br.apicontas.api.assembler.UserAssembler;
import com.ever.br.apicontas.domain.model.dto.request.UserRequestDto;
import com.ever.br.apicontas.domain.model.dto.response.UserResponseDto;
import com.ever.br.apicontas.domain.repository.UserRepository;
import com.ever.br.apicontas.domain.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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

    UserAssembler ass = new UserAssembler();

    @Autowired
    UserRepository repository;
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserService userService;
    @Autowired
    RabbitTemplate rabbitTemplate;

    @PostMapping()
    public ResponseEntity<UserResponseDto> create (@Valid @RequestBody UserRequestDto userRequestDto) throws NoSuchAlgorithmException {
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper
                .map(userService.create(userRequestDto), UserResponseDto.class));
    }

    @GetMapping
    public List<UserResponseDto> findAll(){
           List<UserResponseDto> list = ass.toCollectionUserResponseDto(repository.findAll());
           return list;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(modelMapper
                .map(userService.findById(id), UserResponseDto.class));
    }

}
