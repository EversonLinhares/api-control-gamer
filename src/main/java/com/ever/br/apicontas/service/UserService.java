package com.ever.br.apicontas.service;

import com.ever.br.apicontas.model.dto.request.UserRequestDto;
import com.ever.br.apicontas.model.entity.User;
import com.ever.br.apicontas.repository.UserRepository;
import com.ever.br.apicontas.service.exception.DuplicatedObjectException;
import com.ever.br.apicontas.service.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    ModelMapper modelMapper;

    public User create(UserRequestDto userRequestDto) {
        User obj = repository.findByCpf(userRequestDto.getCpf());
        if (obj != null)
            throw new DuplicatedObjectException("User already exist!");
        User u = modelMapper.map(userRequestDto, User.class);
        return repository.save(u);
    }

    public User findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("User not found "));
    }
}
