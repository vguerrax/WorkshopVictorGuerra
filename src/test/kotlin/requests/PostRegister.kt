package requests

import Constants
import factories.RegisterFactory
import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.junit.jupiter.api.Test
import pojos.RegisterPojo

class PostRegister {

    private fun register01(){
        Given {
            contentType(ContentType.JSON)
            body("{\n" +
                    "    \"email\": \"eve.holt@reqres.in\",\n" +
                    "    \"password\": \"pistol\"\n" +
                    "}"
            )
        }When {
            baseUri(Constants.BASE_URL)
            post("/api/register")
        } Then {
            log().all()
        }
    }

    @Test
    fun register01Test (){
        register01()
    }

    private fun register02(){
        val hashMap: HashMap<String, String> = HashMap()
        hashMap.put("email", "eve.holt@reqres.in")
        hashMap.put("password", "pistol")

        Given {
            contentType(ContentType.JSON)
            body(hashMap)
        }When {
            baseUri("https://reqres.in")
            post("/api/register")
        } Then {
            log().all()
        }
    }

    @Test
    fun register02Test(){
        register02()
    }

    private fun register03(){

        Given {
            val registerPojo = RegisterPojo()
            registerPojo.email = "eve.holt@reqres.in"
            registerPojo.password = "pistol"

            contentType(ContentType.JSON)
            body(registerPojo)
        }When {
            baseUri("https://reqres.in")
            post("/api/register")
        } Then {
            log().all()
        }
    }

    @Test
    fun register03Test(){
        register03()
    }

    private fun register04(registerPojo: RegisterPojo){

        Given {
            contentType(ContentType.JSON)
            body(registerPojo)
        }When {
            baseUri("https://reqres.in")
            post("/api/register")
        } Then {
            log().all()
        }
    }

    private val registerFactory = RegisterFactory()

    @Test
    fun registerSuccess(){
        register04(registerFactory.registerSuccess())
    }

    @Test
    fun registerUnsuccessful(){
        register04(registerFactory.registerUnsuccessful())
    }

    @Test
    fun registerNotFound(){
        register04(registerFactory.registerUserNotFound())
    }
}
