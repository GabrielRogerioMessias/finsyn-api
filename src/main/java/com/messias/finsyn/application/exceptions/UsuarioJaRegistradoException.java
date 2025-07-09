package com.messias.finsyn.application.exceptions;

public class UsuarioJaRegistradoException extends RuntimeException {
    public UsuarioJaRegistradoException(String message) {
        super(message + " já registrado.");
    }
}
