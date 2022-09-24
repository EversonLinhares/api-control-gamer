package com.ever.br.api.control.gamer.domain.service;

import com.ever.br.api.control.gamer.domain.exception.DuplicatedObjectException;
import com.ever.br.api.control.gamer.domain.exception.ObjectNotFoundException;
import com.ever.br.api.control.gamer.domain.model.dto.request.PlayerRequestDto;
import com.ever.br.api.control.gamer.domain.model.dto.response.PlayerResponseDto;
import com.ever.br.api.control.gamer.domain.model.entity.Cla;
import com.ever.br.api.control.gamer.domain.model.entity.Player;
import com.ever.br.api.control.gamer.domain.model.entity.User;
import com.ever.br.api.control.gamer.domain.repository.ClaRepository;
import com.ever.br.api.control.gamer.domain.repository.PlayerRepository;
import com.ever.br.api.control.gamer.domain.repository.UserRepository;
import com.ever.br.api.control.gamer.util.GetUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    ClaRepository claRepository;

    @Autowired
    UserRepository userRepository;

    public PlayerResponseDto create(PlayerRequestDto player) {
        if(verifyExistsPlayerWithNick(player.getNick())){
            throw new DuplicatedObjectException("Player already exist with username " + player.getNick() + "!!!");
        }
        if (!verifyExistsCla(player.getClaId())){
            throw new ObjectNotFoundException("Cla does not exist!!");
        }
        User user = getUser();
        Player p = modelMapper.map(player, Player.class);
        p.setUser(user);
        return modelMapper.map(playerRepository.save(p), PlayerResponseDto.class);
    }

    public PlayerResponseDto findById(Long id) {
        Player getPlayer = playerRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("player not exists !!!"));
        return modelMapper.map(getPlayer, PlayerResponseDto.class);
    }

    public List<PlayerResponseDto> findAll(String nick) {
        return playerRepository.findAll(nick).stream().map(p -> modelMapper
                .map(p, PlayerResponseDto.class)).collect(Collectors.toList());
    }

    public void updatePlayer(Long id, PlayerRequestDto playerRequestDto) {
        Cla claBanco = claRepository.findById(playerRequestDto.getClaId()).orElseThrow(()-> new ObjectNotFoundException("Cla does not exists !!!"));
        Player playerBanco = playerRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Player does not exists !!!"));
        playerBanco.setNick(playerRequestDto.getNick());
        playerBanco.setLevel(playerRequestDto.getLevel());
        playerBanco.setPower(playerRequestDto.getPower());
        playerBanco.setQtdCodex(playerRequestDto.getQtdCodex());
        playerBanco.setCla(claBanco);
        playerRepository.save(playerBanco);

    }

    public Boolean verifyExistsCla(Long id){
        if(claRepository.findById(id).isPresent()){
            return true;
        }
        return false;
    }

    public Boolean verifyExistsPlayerWithNick(String nick) {
        if(playerRepository.findByNick(nick).isPresent()){
            return true;
        }
        return false;
    }

    public User getUser(){
        GetUser get = new GetUser();
        String username = get.getCurrentUser();
        if(username.isEmpty()){
            throw new ObjectNotFoundException("Does not have a user authenticated !!!");
        }
        User user = userRepository.findByUsername(username).orElseThrow(() -> new ObjectNotFoundException("User does not exist"));
        return user;
    }

}
