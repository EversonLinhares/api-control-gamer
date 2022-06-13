package com.ever.br.apicontas.service;

import com.ever.br.apicontas.model.dto.request.UserRequestDto;
import com.ever.br.apicontas.model.entity.User;
import com.ever.br.apicontas.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    ModelMapper modelMapper;

    public User create (UserRequestDto userRequestDto){
        User u = modelMapper.map(userRequestDto, User.class);
        return repository.save(u);
    }
}
