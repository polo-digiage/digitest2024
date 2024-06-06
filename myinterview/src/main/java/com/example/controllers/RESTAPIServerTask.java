package com.example.controllers;

import com.example.RESTAPIClientTask;
import com.example.models.DadosUserApi;
import com.example.models.UserGenderValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This task expects you to create an implementation of a REST API Server.
 * Your code should expose a REST API. 
 * Feel free to explore possibilities/functionalities/capabilities following Rest standard (best practices) 
 * and any framework or library to help you in this journey.
 * We suggest that your implementation have at least a CRUD scenario about any subject.
 * Be creative!
 *
 */
@RestController
@RequestMapping("/api")
public class RESTAPIServerTask {

    @Autowired
    private RESTAPIClientTask restapiClientTask;

    @GetMapping
    public ResponseEntity<List<UserGenderValues>> returnGender(){
        return ResponseEntity.ok(restapiClientTask.returnGenders());
    }
}