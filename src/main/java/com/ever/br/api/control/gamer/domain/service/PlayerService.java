package com.ever.br.api.control.gamer.domain.service;

import com.ever.br.api.control.gamer.api.dto.request.PlayerRequestDto;
import com.ever.br.api.control.gamer.api.dto.response.PlayerResponseDto;
import com.ever.br.api.control.gamer.config.modelmapper.MapperConvert;
import com.ever.br.api.control.gamer.domain.exception.DuplicatedObjectException;
import com.ever.br.api.control.gamer.domain.exception.ObjectNotFoundException;
import com.ever.br.api.control.gamer.domain.model.entity.Classe;
import com.ever.br.api.control.gamer.domain.model.entity.Guild;
import com.ever.br.api.control.gamer.domain.model.entity.Player;
import com.ever.br.api.control.gamer.domain.model.entity.User;
import com.ever.br.api.control.gamer.domain.repository.PlayerRepository;
import com.ever.br.api.control.gamer.domain.repository.UserRepository;
import com.ever.br.api.control.gamer.util.GetUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PlayerService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    MapperConvert mapperConvert;

    @Autowired
    PlayerRepository playerRepository;
    
    @Autowired
    GuildService guildService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ClasseService classeService;

    public Player create(PlayerRequestDto playerRequestDto) {
        if(verifyExistsPlayerWithNick(playerRequestDto.getNick())){
            throw new DuplicatedObjectException("Player already exist with username " + playerRequestDto.getNick() + "!!!");
        }
        Classe classe = verifyExistsClasseWithId(playerRequestDto.getClasse());
        Guild guild = verifyExistsGuild(playerRequestDto.getGuild());
        User user = getUser();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        Player player = modelMapper.map(playerRequestDto, Player.class);
        player.setUser(user);
        player.setGuild(guild);
        player.setClasse(classe);
        return playerRepository.save(player);
    }

    public PlayerResponseDto findById(Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("player not exists !!!"));
        return mapperConvert.mapEntityToDto(player, PlayerResponseDto.class);
    }

    public List<PlayerResponseDto> findAll(String nick,Long level,Long power,Long qtdCodex){
        return mapperConvert.collectionToDto(playerRepository
                .findFilter(nick,level,power,qtdCodex),PlayerResponseDto.class);
    }

    public List<PlayerResponseDto> findAllPersonToUser(){
        User user = getUser();
        return mapperConvert.collectionToDto(playerRepository
                .findAllPersonToUser(user.getId()),PlayerResponseDto.class);
    }

    public void updatePlayer(Long id, PlayerRequestDto playerRequestDto) {
        Guild guildBanco = mapperConvert.mapDtoToEntity(guildService.findById(playerRequestDto.getGuild()),Guild.class);
        Player playerBanco = mapperConvert.mapDtoToEntity(findById(id),Player.class);
        playerBanco.setNick(playerRequestDto.getNick());
        playerBanco.setNivel(playerRequestDto.getNivel());
        playerBanco.setPower(playerRequestDto.getPower());
        playerBanco.setQtdCodex(playerRequestDto.getQtdCodex());
        playerBanco.setGuild(guildBanco);
        playerRepository.save(playerBanco);

    }

    public void deletePlayer(Long id){
        Player player = mapperConvert.mapDtoToEntity(findById(id),Player.class);
        playerRepository.delete(player);
    }

    public Guild verifyExistsGuild(Long id){
        return mapperConvert.mapDtoToEntity(guildService.findById(id),Guild.class);
    }

    public Boolean verifyExistsPlayerWithNick(String nick) {
        if(playerRepository.findByNick(nick).isPresent()){
            return true;
        }
        return false;
    }

    public Classe verifyExistsClasseWithId(Long id) {
       return mapperConvert.mapDtoToEntity(classeService.findById(id),Classe.class);
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
