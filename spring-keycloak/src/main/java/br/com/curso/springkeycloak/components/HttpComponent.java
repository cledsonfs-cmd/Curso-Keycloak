package br.com.curso.springkeycloak.components;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

@Component
public class HttpComponent {

    @Bean
    public HttpHeaders httpHeaders(){
        return new HttpHeaders();
    }

    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
