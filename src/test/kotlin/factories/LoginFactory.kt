package factories

import pojos.RegisterPojo

class LoginFactory {
    private val registerPojo = RegisterPojo()

    fun loginSuccess() : RegisterPojo {
        registerPojo.email = "eve.holt@reqres.in"
        registerPojo.password = "cityslicka"
        return registerPojo
    }

    fun loginInvalidPassword() : RegisterPojo {
        registerPojo.email = "eve.holt@reqres.in"
        registerPojo.password = "test123"
        return registerPojo
    }

    fun loginUserNotFound() : RegisterPojo {
        registerPojo.email = "test"
        registerPojo.password = "cityslicka"
        return registerPojo
    }

    fun loginPasswordNull() : RegisterPojo {
        registerPojo.email = "eve.holt@reqres.in"
        return registerPojo
    }
}