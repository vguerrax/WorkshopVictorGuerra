package requests

import Constants
import factories.LoginFactory
import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import io.restassured.response.Response
import org.junit.jupiter.api.Test
import pojos.RegisterPojo
import kotlin.test.assertEquals
import kotlin.test.assertNotNull


class PostLogin {

    private val loginFactory = LoginFactory()

    private fun login(registerPojo: RegisterPojo) : Response {

        return Given {
            contentType(ContentType.JSON)
            body(registerPojo)
        } When {
            baseUri(Constants.BASE_URL)
            post("/api/login")
        } Then {
            log().all()
        }Extract {
            response()
        }
    }

    @Test
    fun loginSuccess(){
        val response = login(loginFactory.loginSuccess())

        assertNotNull(response)
        assertEquals(200, response.statusCode)
    }

    @Test
    fun loginInvalidPassword(){
        val response = login(loginFactory.loginInvalidPassword())

        assertNotNull(response)
        assertEquals(200, response.statusCode)
    }

    @Test
    fun loginUserNotFound(){
        val response = login(loginFactory.loginUserNotFound())

        assertNotNull(response)
        assertEquals(400, response.statusCode)
        assertEquals("user not found", response.jsonPath().getString("error"))
    }

    @Test
    fun loginPasswordNull(){
        val response = login(loginFactory.loginPasswordNull())

        assertNotNull(response)
        assertEquals(400, response.statusCode)
        assertEquals("Missing password", response.jsonPath().getString("error"))
    }

}