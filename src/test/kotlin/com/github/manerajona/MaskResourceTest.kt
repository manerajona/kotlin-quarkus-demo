package com.github.manerajona

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
class MaskResourceTest {

    @Test
    fun testHelloEndpoint() {
        given()
            .`when`().get("/mask/5555555555554444")
            .then()
            .statusCode(200)
            .body(`is`("XXXXXXXXXXXX4444"))
    }

}