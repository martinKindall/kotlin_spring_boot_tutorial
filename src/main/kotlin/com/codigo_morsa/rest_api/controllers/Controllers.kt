package com.codigo_morsa.rest_api.controllers

import com.codigo_morsa.rest_api.models.Lenguaje
import com.codigo_morsa.rest_api.repositories.LenguajeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono


@RestController
class Controllers {

    lateinit var lenguajeRepository: LenguajeRepository

    @Autowired
    constructor(lenguajeRepository: LenguajeRepository) {
        this.lenguajeRepository = lenguajeRepository
    }

    @GetMapping("/")
    fun home(): String {
        return "Hi there!"
    }

    @GetMapping("/lenguajes")
    fun getLenguajes(): Mono<List<Lenguaje>> {
        return Mono.just(lenguajeRepository.findAll().toMutableList())
    }
}