package com.messias.finsyn.infrastructure.security;

import com.messias.finsyn.adapters.outbounds.entities.JpaUsuarioEntity;
import com.messias.finsyn.adapters.outbounds.repositories.usuario.JpaUsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final JpaUsuarioRepository jpaUsuarioRepository;

    public CustomUserDetailsService(JpaUsuarioRepository jpaUsuarioRepository) {
        this.jpaUsuarioRepository = jpaUsuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        JpaUsuarioEntity jpaUsuarioEntity = jpaUsuarioRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        return new org.springframework.security.core.userdetails.User(jpaUsuarioEntity.getEmail(), jpaUsuarioEntity.getSenha(), new ArrayList<>());
    }
}
