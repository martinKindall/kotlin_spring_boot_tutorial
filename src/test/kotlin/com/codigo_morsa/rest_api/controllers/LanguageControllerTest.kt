package com.codigo_morsa.rest_api.controllers

import com.codigo_morsa.rest_api.RestApiApplication
import com.codigo_morsa.rest_api.config.WebFilters
import com.codigo_morsa.rest_api.models.Lenguaje
import com.codigo_morsa.rest_api.services.LenguajeService
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Mono
import java.sql.Date

@SpringBootTest
@EnableAutoConfiguration(exclude = [
    DataSourceAutoConfiguration::class,
    DataSourceTransactionManagerAutoConfiguration::class,
    HibernateJpaAutoConfiguration::class])
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LanguageControllerTest {

//    @Autowired
//    private lateinit var client: WebTestClient

    @MockBean
    private lateinit var lenguajeService: LenguajeService

    private lateinit var mockLenguaje: Lenguaje

    @BeforeAll
    fun setup() {
        mockLenguaje = Lenguaje(0, "test", Date(10L), false)

        whenever(lenguajeService.getLenguajes()).thenReturn(Mono.just(listOf(mockLenguaje)))
    }

    @Test
    fun getLenguajesTest() {
//        client.get()
//            .uri("/lenguaje")
//            .exchange()
//            .expectStatus().isOk()
//            .expectBody()
//            .jsonPath("$.[0].nombre").isEqualTo("test")
    }
}