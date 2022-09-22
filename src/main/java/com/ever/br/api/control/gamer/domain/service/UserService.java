package com.ever.br.api.control.gamer.domain.service;

import com.ever.br.api.control.gamer.domain.exception.ObjectNotFoundException;
import com.ever.br.api.control.gamer.domain.enums.RoleNameEnums;
import com.ever.br.api.control.gamer.domain.model.dto.request.UserRequestDto;
import com.ever.br.api.control.gamer.domain.model.dto.response.UserResponseDto;
import com.ever.br.api.control.gamer.domain.model.entity.Role;
import com.ever.br.api.control.gamer.domain.model.entity.User;
import com.ever.br.api.control.gamer.domain.repository.RoleRepository;
import com.ever.br.api.control.gamer.domain.repository.UserRepository;
import com.ever.br.api.control.gamer.domain.exception.DuplicatedObjectException;
import com.ever.br.api.control.gamer.util.ValidPassword;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserService  {
    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ModelMapper modelMapper;

    public List<UserResponseDto> findAll() {
       return  userRepository.findAll().stream().map(u -> modelMapper.map(u,UserResponseDto.class)).collect(Collectors.toList());
    }

    public User create(UserRequestDto userRequestDto) throws NoSuchAlgorithmException {
        Optional<User> user = userRepository.findByUsername(userRequestDto.getUsername());
        if (user.isPresent()){
            throw new DuplicatedObjectException("User already exist with username " + userRequestDto.getUsername() + "!!!");
        }
        Long roleId = RoleNameEnums.obterRolePorNome("ROLE_USER");
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new ObjectNotFoundException("Role not found !!!"));

        User u = modelMapper.map(userRequestDto, User.class);
        u.setRoles(List.of(role));
        u.setPassword(encoder.encode(u.getPassword()));
        return userRepository.save(u);
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("User not found "));
    }


    public void updateUser(Long id, UserRequestDto user) {
        User u = userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("User not found"));
        u.setEmail(user.getEmail());
        u.setUsername(user.getUsername());
        u.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(u);

    }
}
