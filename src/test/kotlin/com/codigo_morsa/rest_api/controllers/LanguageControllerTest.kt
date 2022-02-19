package com.codigo_morsa.rest_api.controllers

import com.codigo_morsa.rest_api.models.Lenguaje
import com.codigo_morsa.rest_api.services.LenguajeService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.body
import reactor.core.publisher.Mono
import java.sql.Date

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)   // necessary for WebTestClient
@EnableAutoConfiguration(exclude = [
    DataSourceAutoConfiguration::class,
    DataSourceTransactionManagerAutoConfiguration::class,
    HibernateJpaAutoConfiguration::class])
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LanguageControllerTest {

    @Autowired
    private lateinit var client: WebTestClient

    @MockBean
    private lateinit var lenguajeService: LenguajeService

    private lateinit var mockLenguaje1: Lenguaje
    private lateinit var mockLenguaje2: Lenguaje

    @BeforeEach
    fun setup() {
        mockLenguaje1 = Lenguaje(0, "Java", Date(10L), true)
        mockLenguaje2 = Lenguaje(0, "Javascript", Date(10L), false)

        whenever(lenguajeService.getLenguajes()).thenReturn(Mono.just(listOf(mockLenguaje1, mockLenguaje2)))
        whenever(lenguajeService.crearLenguaje(any())).thenReturn(Mono.just("Tudo bem"))
    }

    @Test
    fun getLenguajesTest() {
        client.get()
            .uri("/lenguaje")
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$.[0].nombre").isEqualTo("Java")
            .jsonPath("$.[0].tipado_fuerte").isEqualTo("true")
            .jsonPath("$.[0].lanzamiento").isEqualTo("1970-01-01")
            .jsonPath("$.[1].nombre").isEqualTo("Javascript")
            .jsonPath("$.[1].tipado_fuerte").isEqualTo("false")
            .jsonPath("$.[1].lanzamiento").isEqualTo("1970-01-01")
    }

    @Test
    fun createLenguajeTest() {
        client.post()
            .uri("/lenguaje")
            .body(Mono.just(mockLenguaje1))
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$").isEqualTo("Tudo bem")
    }

    @Test
    fun createLenguajeBadRequestTest() {
        client.post()
            .uri("/lenguaje")
            .body(Mono.just("invalid data"))
            .exchange()
            .expectStatus().is4xxClientError
    }

    @Test
    fun createLenguajeEmptyRequestTest() {
        client.post()
            .uri("/lenguaje")
            .body(Mono.just(""))
            .exchange()
            .expectStatus().is4xxClientError
    }

    @Test
    fun createLenguajeEmpty2RequestTest() {
        client.post()
            .uri("/lenguaje")
            .body(Mono.just(2))
            .exchange()
            .expectStatus().is4xxClientError
    }

    @Test
    fun createLenguajeEmpty3RequestTest() {
        client.post()
            .uri("/lenguaje")
            .body(Mono.empty())
            .exchange()
            .expectStatus().is4xxClientError
    }
}