package com.example;

import com.example.models.DadosUserApi;
import com.example.models.UserGenderValues;
import com.example.services.UserGenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This task expects you to create an implementation of a Rest API client.
 * Your code should call the API endpoint related below.
 * After receiving the JSON Response, print out how many records exists for each gender
 * API endpoint => https://3ospphrepc.execute-api.us-west-2.amazonaws.com/prod/RDSLambda 
 * 
 * >>> Bonus <<<
 * Generate a CSV file containing many records exists for each gender and save this file to AWS S3 Bucket
 * The filename need to contains your entire name, separated by uderscore. Example: john_lennon.csv
 * AWS S3 bucket name => interview-digiage
 * The credentials you can find in Coodesh platform or ask via e-mail for recrutamento@digiage.com.br
 */

@Component
public class RESTAPIClientTask {

    // API endpoint => https://3ospphrepc.execute-api.us-west-2.amazonaws.com/prod/RDSLambda

    @Autowired
    private UserGenderService service;

    public List<UserGenderValues> returnGenders(){
        return service.groupByGenderReturnValue();
    }
}