package com.codigo_morsa.rest_api.services

import com.codigo_morsa.rest_api.models.Lenguaje
import com.codigo_morsa.rest_api.repositories.LenguajeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class LenguajeService {

    lateinit var lenguajeRepository: LenguajeRepository

    @Autowired
    constructor(lenguajeRepository: LenguajeRepository) {
        this.lenguajeRepository = lenguajeRepository
    }

    fun getLenguajes(): Mono<List<Lenguaje>> {
        return Mono.just(lenguajeRepository.findAll().toMutableList())
    }

    fun crearLenguaje(nuevoLenguaje: Lenguaje): Mono<String> {
        return Mono.just(lenguajeRepository.save(nuevoLenguaje).let {
            "Nuevo lenguaje creado, id: ${it.id}"
        })
    }
}