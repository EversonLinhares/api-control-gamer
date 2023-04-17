package com.ever.br.api.control.gamer.domain.service;

import com.ever.br.api.control.gamer.domain.exception.ObjectNotFoundException;
import com.ever.br.api.control.gamer.domain.enums.RoleNameEnums;
import com.ever.br.api.control.gamer.api.dto.request.UserRequestDto;
import com.ever.br.api.control.gamer.api.dto.response.UserResponseDto;
import com.ever.br.api.control.gamer.domain.model.entity.Role;
import com.ever.br.api.control.gamer.domain.model.entity.User;
import com.ever.br.api.control.gamer.domain.repository.RoleRepository;
import com.ever.br.api.control.gamer.domain.repository.UserRepository;
import com.ever.br.api.control.gamer.domain.exception.DuplicatedObjectException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;
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

    public UserResponseDto create(UserRequestDto userRequestDto) throws NoSuchAlgorithmException {
        if (verifyExistUser(userRequestDto.getUsername())){
            throw new DuplicatedObjectException("User already exist with username " + userRequestDto.getUsername() + "!!!");
        }
        User u = modelMapper.map(userRequestDto, User.class);
        if (Objects.isNull(u.getRoles())){
            u.setRoles(List.of(getRole()));
        }
        u.setPassword(encoder.encode(u.getPassword()));
        return modelMapper.map(userRepository.save(u), UserResponseDto.class);
    }

    public List<UserResponseDto> findAll() {
        return  userRepository.findAll().stream().map(u -> modelMapper.map(u,UserResponseDto.class)).collect(Collectors.toList());
    }

    public UserResponseDto findById(Long id) {
        User u = userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("User not found "));
        return modelMapper.map(u, UserResponseDto.class);
    }


    public void updateUser(Long id, UserRequestDto user) {
        User u = userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("User not found"));
        u.setEmail(user.getEmail());
        u.setUsername(user.getUsername());
        u.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(u);

    }

    public Boolean verifyExistUser (String name){
        Optional<User> user = userRepository.findByUsername(name);
        if (user.isPresent()){
            return true;
        }
        return false;
    }

    public Role getRole() {
        Long roleId = RoleNameEnums.obterRolePorNome("ROLE_USER");
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ObjectNotFoundException("Role not found !!!"));
        return role;
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("User not found "));
        userRepository.delete(user);
    }
}
