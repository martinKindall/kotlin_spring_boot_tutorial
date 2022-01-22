package com.codigo_morsa.rest_api.repositories

import com.codigo_morsa.rest_api.models.Lenguaje
import org.springframework.data.repository.CrudRepository


interface LenguajeRepository: CrudRepository<Lenguaje, Int> {
}