package com.codigo_morsa.rest_api.controllers

import com.codigo_morsa.rest_api.models.Lenguaje
import com.codigo_morsa.rest_api.repositories.LenguajeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
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

    @GetMapping("/lenguaje")
    fun getLenguajes(): Mono<List<Lenguaje>> {
        return Mono.just(lenguajeRepository.findAll().toMutableList())
    }

    @PostMapping("/lenguaje")
    fun createLenguaje(
        @RequestBody nuevoLenguaje: Lenguaje
    ): Mono<String> {
        return Mono.just(lenguajeRepository.save(nuevoLenguaje).let {
            "Nuevo lenguaje creado, id: ${it.id}"
        })
    }
}