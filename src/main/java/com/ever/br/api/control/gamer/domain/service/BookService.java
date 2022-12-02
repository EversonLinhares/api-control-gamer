package com.ever.br.api.control.gamer.domain.service;

import com.ever.br.api.control.gamer.api.consumer.Consumer;
import com.ever.br.api.control.gamer.domain.model.dto.response.BookResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    Consumer consumer;

    public List<BookResponseDto> getBooks () {
      return consumer.getBooks();
    }
}
