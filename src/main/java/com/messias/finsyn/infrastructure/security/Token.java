package com.messias.finsyn.infrastructure.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token {
    private String email;
    private Boolean authenticated;
    private Date created;
    private Date expiration;
    private String token;
}
