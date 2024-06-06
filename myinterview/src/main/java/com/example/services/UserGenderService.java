package com.example.services;

import com.example.models.DadosUserApi;
import com.example.models.UserGenderValues;
import com.example.proxy.UserGenderProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserGenderService {

    @Autowired
    private UserGenderProxy genderProxy;

    // After receiving the JSON Response, print out how many records exists for each gender

    public List<UserGenderValues> groupByGenderReturnValue(){
        List<DadosUserApi>  dadosUserApiList = genderProxy.getdadosUser();
        var xmap = dadosUserApiList.stream()
                .collect(
                        Collectors.groupingBy(DadosUserApi::getGender));

      List<UserGenderValues> listgender = new ArrayList<>();
      xmap.forEach((chave,valor)->{
          listgender.add(new UserGenderValues(valor.size(),chave));
      });

      return listgender;
    }
}
