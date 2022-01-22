package com.codigo_morsa.rest_api.models

import java.sql.Date
import javax.persistence.*

@Entity
@Table(name = "mis_lenguajes")
data class Lenguaje(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Int?,
    val nombre: String,
    val lanzamiento: Date,
    val tipado_fuerte: Boolean
)
