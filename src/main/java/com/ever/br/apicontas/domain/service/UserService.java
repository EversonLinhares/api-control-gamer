package com.ever.br.apicontas.domain.service;

import com.ever.br.apicontas.domain.enums.RoleNameEnums;
import com.ever.br.apicontas.domain.model.dto.request.UserRequestDto;
import com.ever.br.apicontas.domain.model.entity.Role;
import com.ever.br.apicontas.domain.model.entity.User;
import com.ever.br.apicontas.domain.repository.RoleRepository;
import com.ever.br.apicontas.domain.repository.UserRepository;
import com.ever.br.apicontas.domain.exception.DuplicatedObjectException;
import com.ever.br.apicontas.domain.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;


@Service
public class UserService  {
    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    private UserRepository repository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ModelMapper modelMapper;

    public User create(UserRequestDto userRequestDto) throws NoSuchAlgorithmException {
        Optional<User> user = repository.findByUsername(userRequestDto.getUsername());
        Long roleId = RoleNameEnums.obterRolePorNome("ROLE_USER");
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new ObjectNotFoundException("A Role informada não possui cadastrada no banco"));
        if (user.isPresent()){
            throw new DuplicatedObjectException("User already exist with username " + userRequestDto.getUsername());
        }

        User u = User.builder()
                        .username(userRequestDto.getUsername())
                                .password((encoder.encode(userRequestDto.getPassword())))
                                          .email(userRequestDto.getEmail())
                                               .roles(List.of(role)).build();
        return repository.save(u);
    }

    public User findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("User not found "));
    }


}
