package com.ever.br.apicontas.api.assembler;
import com.ever.br.apicontas.domain.model.dto.response.UserResponseDto;
import com.ever.br.apicontas.domain.model.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserAssembler {

    ModelMapper mapper = new ModelMapper();

    public UserResponseDto UserToUserResponseDTO(User user) {
        return mapper.map(user, UserResponseDto.class);
    }

    public List<UserResponseDto> toCollectionUserResponseDto(List<User> list) {
        return list.stream().map(p -> UserToUserResponseDTO(p)).collect(Collectors.toList());
    }

//    exemplo:
//    ModelMapper mapper = new ModelMapper();
//
//    public BancoOutputPesquisaDTO BancoToBancoOutputPesquisaDTO(Banco banco) {
//        return mapper.map(banco, BancoOutputPesquisaDTO.class);
//    }
//
//    public List<BancoOutputPesquisaDTO> toCollectionBancoOutputPesquisaDTO(List<Banco> list) {
//        return list.stream().map(p -> BancoToBancoOutputPesquisaDTO(p)).collect(Collectors.toList());
//    }
}
