package com.example.proxy;

import com.example.models.DadosUserApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Component
public class UserGenderProxy {

    @Value("${user.dados.url}")
    private String dadosUserURI;

    public List<DadosUserApi> getdadosUser(){
        RestTemplate restTemplate = new RestTemplate();
        String resourceURLUser
                = dadosUserURI;
        UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromHttpUrl(resourceURLUser);

        try {
            return restTemplate.exchange(
                    urlBuilder.build(false).encode().toUriString(),
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<DadosUserApi>>(){}).getBody();
        }catch (Exception e){
            throw  new RuntimeException("Erro ao realizar requisição");
        }

    }
}
