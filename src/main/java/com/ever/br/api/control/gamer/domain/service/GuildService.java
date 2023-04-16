package com.ever.br.api.control.gamer.domain.service;

import com.ever.br.api.control.gamer.domain.exception.DuplicatedObjectException;
import com.ever.br.api.control.gamer.domain.exception.ObjectNotFoundException;
import com.ever.br.api.control.gamer.domain.model.dto.request.GuildRequestDto;
import com.ever.br.api.control.gamer.domain.model.dto.response.GuildResponseDto;
import com.ever.br.api.control.gamer.domain.model.entity.Guild;
import com.ever.br.api.control.gamer.domain.repository.GuildRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GuildService {
    @Autowired
    GuildRepository guildRepository;

    @Autowired
    ModelMapper modelMapper;

    public GuildResponseDto create(GuildRequestDto guildRequestDto) {
        Optional<Guild> claBanco = guildRepository.findByName(guildRequestDto.getName());
        if (claBanco.isPresent()){
            throw new DuplicatedObjectException("Guild already exist with username " + guildRequestDto.getName() + "!!!");
        }

        Guild guildSave = modelMapper.map(guildRequestDto, Guild.class);
        return modelMapper.map(guildRepository.save(guildSave), GuildResponseDto.class);
    }

    public GuildResponseDto findById(Long id) {
        Guild getGuild = guildRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Guild does not exists !!!"));
        return modelMapper.map(getGuild, GuildResponseDto.class);
    }

    public List<GuildResponseDto> findAll() {
        return guildRepository.findAll().stream().map(c -> modelMapper.map(c, GuildResponseDto.class)).collect(Collectors.toList());
    }

    public void updateCla(Long id, GuildRequestDto guildRequestDto) {
       Guild guildBanco = guildRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Guild does not exists !!!"));
       guildBanco.setName(guildRequestDto.getName());
       guildBanco.setNivel(guildRequestDto.getNivel());
       guildRepository.save(guildBanco);
    }
}
