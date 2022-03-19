package com.example.chungxe.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestConnection {

    @GetMapping("/")
    public String test() {
        return "ok";
    }
}
