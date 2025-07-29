package com.messias.finsyn.infrastructure.security;

import com.messias.finsyn.domain.models.usuario.Usuario;
import com.messias.finsyn.domain.ports.out.UsuarioRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {
    private final UsuarioRepository repository;

    public SecurityUtils(UsuarioRepository usuarioRepository) {
        this.repository = usuarioRepository;
    }

    public Usuario usuarioAutenticado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return repository.buscarPorEmail(authentication.getName()).get();
    }
}
