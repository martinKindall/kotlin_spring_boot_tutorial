package com.codigo_morsa.rest_api.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Controllers {

    @GetMapping("/")
    fun home(): String {
        return "Hi there!"
    }
}