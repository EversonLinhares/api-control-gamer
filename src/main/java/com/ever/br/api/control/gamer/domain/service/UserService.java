package com.ever.br.api.control.gamer.domain.service;

import com.ever.br.api.control.gamer.api.dto.request.UserRequestDto;
import com.ever.br.api.control.gamer.api.dto.response.UserResponseDto;
import com.ever.br.api.control.gamer.config.modelmapper.MapperConvert;
import com.ever.br.api.control.gamer.domain.enums.RoleNameEnums;
import com.ever.br.api.control.gamer.domain.exception.DuplicatedObjectException;
import com.ever.br.api.control.gamer.domain.exception.ObjectNotFoundException;
import com.ever.br.api.control.gamer.domain.model.entity.Role;
import com.ever.br.api.control.gamer.domain.model.entity.User;
import com.ever.br.api.control.gamer.domain.repository.RoleRepository;
import com.ever.br.api.control.gamer.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class UserService  {
    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    MapperConvert mapperConvert;

    public User create(UserRequestDto userRequestDto) throws NoSuchAlgorithmException {
        verifyExistUser(userRequestDto.getUsername());

        User u = mapperConvert.mapDtoToEntity(userRequestDto, User.class);
        if (Objects.isNull(u.getRoles())){
            u.setRoles(List.of(getRole()));
        }
        u.setPassword(encoder.encode(u.getPassword()));
        return userRepository.save(u);
    }

    public List<UserResponseDto> findAll() {
        return mapperConvert.collectionToDto(userRepository.findAll(),UserResponseDto.class);
    }

    public UserResponseDto findById(Long id) {
        User u = userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("User not found "));
        return mapperConvert.mapEntityToDto(u, UserResponseDto.class);
    }


    public void updateUser(Long id, UserRequestDto user) {
        User u = userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("User not found"));
        u.setEmail(user.getEmail());
        u.setUsername(user.getUsername());
        u.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(u);

    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("User not found "));
        for(Role role : user.getRoles()){
            user.setRoles(new ArrayList<>());
        }
        userRepository.delete(user);
    }
    public User verifyExistUser (String name){
        User user = userRepository.findByUsername(name)
                .orElseThrow(() -> new ObjectNotFoundException("User not exist"));
        return user;
    }

    public Role getRole() {
        Long roleId = RoleNameEnums.obterRolePorNome("ROLE_USER");
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ObjectNotFoundException("Role not found !!!"));
        return role;
    }
}
