package com.ever.br.api.control.gamer.domain.service;

import com.ever.br.api.control.gamer.api.dto.request.GuildRequestDto;
import com.ever.br.api.control.gamer.api.dto.response.GuildResponseDto;
import com.ever.br.api.control.gamer.config.modelmapper.MapperConvert;
import com.ever.br.api.control.gamer.domain.exception.DuplicatedObjectException;
import com.ever.br.api.control.gamer.domain.exception.ObjectNotFoundException;
import com.ever.br.api.control.gamer.domain.model.entity.Guild;
import com.ever.br.api.control.gamer.domain.repository.GuildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuildService {
    @Autowired
    GuildRepository guildRepository;

    @Autowired
    MapperConvert mapperConvert;

    public Guild create(GuildRequestDto guildRequestDto) {
        Optional<Guild> claBanco = guildRepository.findByName(guildRequestDto.getName());
        if (claBanco.isPresent()){
            throw new DuplicatedObjectException("Existing clan with name " + guildRequestDto.getName() + "!!!");
        }
        return guildRepository.save(mapperConvert.mapDtoToEntity(guildRequestDto, Guild.class));
    }

    public GuildResponseDto findById(Long id) {
        Guild getGuild = guildRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Clã not exists with id: " + id + "!!!"));
        return mapperConvert.mapEntityToDto(getGuild, GuildResponseDto.class);
    }

    public List<GuildResponseDto> findAll() {
        return mapperConvert.collectionToDto(guildRepository.findAll(),GuildResponseDto.class);
    }

    public void updateCla(Long id, GuildRequestDto guildRequestDto) {
       Guild guildBanco = mapperConvert.mapDtoToEntity(findById(id),Guild.class);
       guildBanco.setName(guildRequestDto.getName());
       guildBanco.setNivel(guildRequestDto.getNivel());
       guildRepository.save(guildBanco);
    }

    public void deleteCla(Long id) {
        Guild guild = mapperConvert.mapDtoToEntity(findById(id),Guild.class);
        guildRepository.delete(guild);
    }
}
