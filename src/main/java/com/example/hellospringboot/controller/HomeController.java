package com.example.hellospringboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

        @RequestMapping("/")
        public String index() {
            return "Congratulations to connect to Home!";
        }

    }

