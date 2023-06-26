package com.ever.br.api.control.gamer.domain.service;

import com.ever.br.api.control.gamer.api.dto.request.ClasseRequestDto;
import com.ever.br.api.control.gamer.api.dto.response.ClasseResponseDto;
import com.ever.br.api.control.gamer.config.modelmapper.MapperConvert;
import com.ever.br.api.control.gamer.domain.exception.ObjectNotFoundException;
import com.ever.br.api.control.gamer.domain.model.Classe;
import com.ever.br.api.control.gamer.domain.repository.ClasseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClasseService {

    @Autowired
    MapperConvert mapperConvert;

    @Autowired
    ClasseRepository classeRepository;

    public List<ClasseResponseDto> findAll(){
        return mapperConvert.collectionToDto(classeRepository
                .findAll(),ClasseResponseDto.class);
    }

    public ClasseResponseDto findById(Long id){
        Classe classe = classeRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Does not exist classe "));
        return mapperConvert.mapEntityToDto(classe,ClasseResponseDto.class);
    }

    public Classe create(ClasseRequestDto classe){
        return classeRepository
                .save(mapperConvert.mapDtoToEntity(classe,Classe.class));

    }

    public void deleteClasse(Long id) {
        classeRepository.delete(mapperConvert.mapDtoToEntity(findById(id),Classe.class));
    }
}
