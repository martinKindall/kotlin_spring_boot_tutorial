package com.codigo_morsa.rest_api.controllers

import com.codigo_morsa.rest_api.models.Lenguaje
import com.codigo_morsa.rest_api.services.LenguajeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono


@RestController
class Controllers {

    lateinit var lenguajeService: LenguajeService

    @Autowired
    constructor(lenguajeService: LenguajeService) {
        this.lenguajeService = lenguajeService
    }

    @GetMapping("/")
    fun home(): String {
        return "Hi there!"
    }

    @GetMapping("/lenguaje")
    fun getLenguajes(): Mono<List<Lenguaje>> {
        return lenguajeService.getLenguajes()
    }

    @PostMapping("/lenguaje")
    fun createLenguaje(
        @RequestBody nuevoLenguaje: Lenguaje
    ): Mono<String> {
        return lenguajeService.crearLenguaje(nuevoLenguaje)
    }
}