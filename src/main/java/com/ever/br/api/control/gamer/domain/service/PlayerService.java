package com.ever.br.api.control.gamer.domain.service;

import com.ever.br.api.control.gamer.domain.exception.DuplicatedObjectException;
import com.ever.br.api.control.gamer.domain.exception.ObjectNotFoundException;
import com.ever.br.api.control.gamer.domain.model.dto.request.PlayerRequestDto;
import com.ever.br.api.control.gamer.domain.model.dto.response.PlayerResponseDto;
import com.ever.br.api.control.gamer.domain.model.entity.Guild;
import com.ever.br.api.control.gamer.domain.model.entity.Classe;
import com.ever.br.api.control.gamer.domain.model.entity.Player;
import com.ever.br.api.control.gamer.domain.model.entity.User;
import com.ever.br.api.control.gamer.domain.repository.GuildRepository;
import com.ever.br.api.control.gamer.domain.repository.ClasseRepository;
import com.ever.br.api.control.gamer.domain.repository.PlayerRepository;
import com.ever.br.api.control.gamer.domain.repository.UserRepository;
import com.ever.br.api.control.gamer.util.GetUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PlayerService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    GuildRepository guildRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ClasseRepository classeRepository;

    public PlayerResponseDto create(PlayerRequestDto player) {
        if(verifyExistsPlayerWithNick(player.getNick())){
            throw new DuplicatedObjectException("Player already exist with username " + player.getNick() + "!!!");
        }
        if(!verifyExistsClasseWithId(player.getClasse())){
            throw new ObjectNotFoundException("Classe does not exist");
        }
        Guild guild = verifyExistsGuild(player.getGuild());
//        User user = getUser();
        User user = userRepository.findById(23L).orElseThrow(() -> new ObjectNotFoundException("a"));
        Classe classe = classeRepository.findById(player.getClasse()).orElseThrow(() -> new ObjectNotFoundException("Does not exist classe"));
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        Player p = modelMapper.map(player, Player.class);
        p.setUser(user);
        p.setGuild(guild);
        p.setClasse(classe);
        p = playerRepository.save(p);
        return modelMapper.map(p, PlayerResponseDto.class);
    }

    public PlayerResponseDto findById(Long id) {
        Player getPlayer = playerRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("player not exists !!!"));
        return modelMapper.map(getPlayer, PlayerResponseDto.class);
    }

    public List<PlayerResponseDto> findAll(){
        return playerRepository.findAll().stream().map(p -> modelMapper
                .map(p, PlayerResponseDto.class)).collect(Collectors.toList());
    }

    public List<PlayerResponseDto> findAllPersonToUser(){
        User user = getUser();
        return playerRepository.findAllPersonToUser(user.getId()).stream().map(p -> modelMapper
                .map(p, PlayerResponseDto.class)).collect(Collectors.toList());
    }

//    public List<PlayerResponseDto> findAll(String nick) {
//        return playerRepository.findAll(nick).stream().map(p -> modelMapper
//                .map(p, PlayerResponseDto.class)).collect(Collectors.toList());
//    }

    public void updatePlayer(Long id, PlayerRequestDto playerRequestDto) {
        Guild guildBanco = guildRepository.findById(playerRequestDto.getGuild()).orElseThrow(()-> new ObjectNotFoundException("Guild does not exists !!!"));
        Player playerBanco = playerRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Player does not exists !!!"));
        playerBanco.setNick(playerRequestDto.getNick());
        playerBanco.setLevel(playerRequestDto.getLevel());
        playerBanco.setPower(playerRequestDto.getPower());
        playerBanco.setQtdCodex(playerRequestDto.getQtdCodex());
        playerBanco.setGuild(guildBanco);
        playerRepository.save(playerBanco);

    }

    public void deletePlayer(Long id){
        playerRepository.deletePlayer(id);
    }

    public Guild verifyExistsGuild(Long id){
        Guild guild = guildRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("does not exist cla"));
        return guild;
    }

    public Boolean verifyExistsPlayerWithNick(String nick) {
        if(playerRepository.findByNick(nick).isPresent()){
            return true;
        }
        return false;
    }

    public Boolean verifyExistsClasseWithId(Long id) {
        if(classeRepository.findById(id).isPresent()){
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
