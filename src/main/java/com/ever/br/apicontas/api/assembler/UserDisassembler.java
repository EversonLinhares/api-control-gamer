package com.ever.br.apicontas.api.assembler;
import com.ever.br.apicontas.domain.model.dto.response.UserResponseDto;
import com.ever.br.apicontas.domain.model.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

public class UserDisassembler {

    @Autowired
    private ModelMapper mapper;

    public User toModel(UserResponseDto user) {
        return mapper.map(user, User.class);
    }

    public List<User> toCollectionDTO(List<UserResponseDto> list) {
        return list.stream().map(this::toModel).collect(Collectors.toList());
    }

//    exemplo service convert
//BancoAssembler bancoAssembler = new BancoAssembler();
//
//    @Autowired
//    BancoRepository bancoRepository;
//
//    public List<BancoOutputPesquisaDTO> findAll () {
//        List<BancoOutputPesquisaDTO> bancos = bancoAssembler.toCollectionBancoOutputPesquisaDTO(bancoRepository.findAll());
//        return bancos;
//    }
//
//    public BancoOutputPesquisaDTO findById(Long id) {
//        return  bancoAssembler.BancoToBancoOutputPesquisaDTO(bancoRepository.findById(id).
//                orElseThrow(() -> new BancoNaoEncontradoException(id)));
//    }
}
