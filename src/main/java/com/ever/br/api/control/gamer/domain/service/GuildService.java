package com.ever.br.api.control.gamer.domain.service;

import com.ever.br.api.control.gamer.config.modelmapper.MapperConvert;
import com.ever.br.api.control.gamer.domain.exception.DuplicatedObjectException;
import com.ever.br.api.control.gamer.domain.exception.ObjectNotFoundException;
import com.ever.br.api.control.gamer.api.dto.request.GuildRequestDto;
import com.ever.br.api.control.gamer.api.dto.response.GuildResponseDto;
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
    MapperConvert mapperConvert;

    public Guild create(GuildRequestDto guildRequestDto) {
        Optional<Guild> claBanco = guildRepository.findByName(guildRequestDto.getName());
        if (claBanco.isPresent()){
            throw new DuplicatedObjectException("Clã already exist with username " + guildRequestDto.getName() + "!!!");
        }

        Guild guildSave = mapperConvert.mapDtoToEntity(guildRequestDto, Guild.class);
        return guildRepository.save(guildSave);
    }

    public GuildResponseDto findById(Long id) {
        Guild getGuild = guildRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Clã does not exists !!!"));
        return mapperConvert.mapEntityToDto(getGuild, GuildResponseDto.class);
    }

    public List<GuildResponseDto> findAll() {
        return mapperConvert.collectionToDto(guildRepository.findAll(),GuildResponseDto.class);
    }

    public void updateCla(Long id, GuildRequestDto guildRequestDto) {
       Guild guildBanco = guildRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Clã does not exists !!!"));
       guildBanco.setName(guildRequestDto.getName());
       guildBanco.setNivel(guildRequestDto.getNivel());
       guildRepository.save(guildBanco);
    }

    public void deleteCla(Long id) {
        Guild guild = guildRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Clã does not exists !!!"));
        guildRepository.delete(guild);
    }
}
