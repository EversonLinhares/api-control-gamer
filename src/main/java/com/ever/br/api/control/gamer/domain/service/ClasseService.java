package com.ever.br.api.control.gamer.domain.service;

import com.ever.br.api.control.gamer.domain.exception.ObjectNotFoundException;
import com.ever.br.api.control.gamer.api.dto.request.ClasseRequestDto;
import com.ever.br.api.control.gamer.api.dto.response.ClasseResponseDto;
import com.ever.br.api.control.gamer.domain.model.entity.Classe;
import com.ever.br.api.control.gamer.domain.repository.ClasseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClasseService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ClasseRepository classeRepository;

    public List<ClasseResponseDto> findAll(){
        return classeRepository.findAll().stream()
                .map(c -> modelMapper.map(c, ClasseResponseDto.class)).collect(Collectors.toList());
    }

    public Classe findById(Long id){
        return classeRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Does not exist classe "));
    }

    public ClasseResponseDto create(ClasseRequestDto classe){
        Classe classeSave = modelMapper.map(classe,Classe.class);
        return modelMapper.map(classeRepository.save(classeSave),ClasseResponseDto.class);
    }

    public void deleteClasse(Long id) {
        Classe classe = findById(id);
        classeRepository.delete(classe);
    }
}
