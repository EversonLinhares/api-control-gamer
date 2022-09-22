package com.ever.br.api.control.gamer.domain.service;

import com.ever.br.api.control.gamer.domain.exception.DuplicatedObjectException;
import com.ever.br.api.control.gamer.domain.exception.ObjectNotFoundException;
import com.ever.br.api.control.gamer.domain.model.dto.request.ClaRequestDto;
import com.ever.br.api.control.gamer.domain.model.dto.response.ClaResponseDto;
import com.ever.br.api.control.gamer.domain.model.entity.Cla;
import com.ever.br.api.control.gamer.domain.repository.ClaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClaService {

    @Autowired
    ClaRepository claRepository;

    @Autowired
    ModelMapper modelMapper;

    public Cla create(ClaRequestDto claRequestDto) {
        Optional<Cla> claBanco = claRepository.findByName(claRequestDto.getName());
        if (claBanco.isPresent()){
            throw new DuplicatedObjectException("Cla already exist with username " + claRequestDto.getName() + "!!!");
        }

        Cla claSave = modelMapper.map(claRequestDto,Cla.class);
        return claRepository.save(claSave);
    }

    public Cla findById(Long id) {
        return claRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Cla does not exists !!!"));
    }

    public List<ClaResponseDto> findAll() {
        return claRepository.findAll().stream().map(c -> modelMapper.map(c, ClaResponseDto.class)).collect(Collectors.toList());
    }

    public void updateCla(Long id, ClaRequestDto claRequestDto) {
       Cla claBanco = claRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Cla does not exists !!!"));
       claBanco.setName(claRequestDto.getName());
       claBanco.setLevel(claRequestDto.getLevel());
       claRepository.save(claBanco);
    }
}
