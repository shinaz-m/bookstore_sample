package com.shinaz.bookstore.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
public class DefaultController {

    @GetMapping(path = "/")
    public ResponseEntity<String> home() {
        return ResponseEntity.status(OK).body("welcome to Book Store app");
    }
}
