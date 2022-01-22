package com.codigo_morsa.rest_api.models

import java.sql.Date
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "mis_lenguajes")
data class Lenguaje(
    @Id val id: Int,
    val nombre: String,
    val lanzamiento: Date,
    val tipado_fuerte: Boolean
)
