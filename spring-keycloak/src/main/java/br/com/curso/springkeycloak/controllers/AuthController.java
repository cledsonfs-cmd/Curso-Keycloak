package br.com.curso.springkeycloak.controllers;

import br.com.curso.springkeycloak.models.User;

import br.com.curso.springkeycloak.services.LoginService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {

    @Autowired
    private LoginService<String> loginService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody User user) {
        return loginService.login(user);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestParam("refresh_token") String refreshToken){
        return loginService.refreshToken(refreshToken);
    }


}
