package com.ever.br.api.control.gamer.config.security;

import com.ever.br.api.control.gamer.domain.model.entity.User;
import com.ever.br.api.control.gamer.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = repository.findByUsername(username).
                orElseThrow(() -> new UsernameNotFoundException("User not found with username " + username));
        return User.builder().username(u.getUsername()).password(u.getPassword()).roles(u.getRoles()).build();
    }
}
