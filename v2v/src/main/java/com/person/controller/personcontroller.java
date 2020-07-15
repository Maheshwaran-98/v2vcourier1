package com.person.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.model.person;


//@RestController @CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class personcontroller {
    @Autowired
   // private personrepository employeeRepository;

    @GetMapping("/employees")
    public void getAllEmployees() {
        
    }
    
   
    }
