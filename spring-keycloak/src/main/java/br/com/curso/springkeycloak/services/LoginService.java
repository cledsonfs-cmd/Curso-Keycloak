package br.com.curso.springkeycloak.services;

import br.com.curso.springkeycloak.models.User;
import org.springframework.http.ResponseEntity;

public interface LoginService<T> {
    ResponseEntity<T> login(User user);
    ResponseEntity<T> refreshToken(String refreshToken);

}
