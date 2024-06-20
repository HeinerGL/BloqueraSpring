package com.project.bloquera.controllers;

import com.project.bloquera.dtos.response.TokenResponseDto;
import com.project.bloquera.services.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final TokenService tokenService;

    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/token")
    public ResponseEntity<TokenResponseDto> token(Authentication authentication) {
        TokenResponseDto token = tokenService.generateToken(authentication);

        return ResponseEntity.ok(token);
    }
}
