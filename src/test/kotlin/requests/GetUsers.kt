package requests

import Constants
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.When
import io.restassured.module.kotlin.extensions.Extract
import io.restassured.response.Response
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull


class GetUsers {

    fun getUsers(page: Int, per_page: Int) :Response {
        return Given {
            baseUri(Constants.BASE_URL)
            queryParam("page", page)
            queryParam("per_page", per_page)
        } When {
            get("/users")
        } Extract {
            response()
        }
    }

    fun getUser(id: Int): Response {
        return Given {
            baseUri(Constants.BASE_URL)
            pathParam("id", id)
        } When {
            get("/users/{id}")
        } Extract {
            response()
        }
    }

    @Test
    @DisplayName("Listar 1 usuário")
    fun getUsersTest() {
        val response = getUsers(1 ,1)
        assertNotNull(response)
        assertEquals(200, response.statusCode())
        assertEquals(1, response.jsonPath().getInt("page"))
        assertEquals("George",
        response.jsonPath().getString("data[0].first_name"))
    }

    @Test
    @DisplayName("Listar 5 usuários")
    fun getFiveUsersTest() {
        val response = getUsers(1, 5)

        val size = response.jsonPath().getInt("data.size()") - 1
        for (i in 0 .. size) {
            assertEquals(i+1, response.jsonPath().getInt("data[$i].id"))
        }
    }

    @Test
    @DisplayName("Recuperar usuário")
    fun getSingleUser() {
        val response = getUser(1)
        assertNotNull(response)
        assertEquals(200, response.statusCode())
        assertEquals(1, response.jsonPath().getInt("data.id"))
        assertEquals("George",
            response.jsonPath().getString("data.first_name"))
    }

    @Test
    @DisplayName("Recuperar usuário inexistente")
    fun getSingleUserNotFound() {
        val response = getUser(25)
        assertNotNull(response)
        assertEquals(404, response.statusCode())
    }
}