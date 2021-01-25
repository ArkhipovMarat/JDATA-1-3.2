package com.example.demo.controller;

import com.example.demo.repository.DataBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    private final DataBaseRepository dataBaseRepository;

    public Controller(DataBaseRepository dataBaseRepository) {
        this.dataBaseRepository = dataBaseRepository;
    }

    @GetMapping("/products/fetch-product")
    public String getProductName(@RequestParam String name){
        return dataBaseRepository.getProductName(name);
    }
}
