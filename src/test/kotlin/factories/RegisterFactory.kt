package factories

import pojos.RegisterPojo

class RegisterFactory {
    private var registerPojo = RegisterPojo()

    fun registerSuccess() : RegisterPojo {
        registerPojo.email = "eve.holt@reqres.in"
        registerPojo.password = "pistol"
        return registerPojo
    }

    fun registerUnsuccessful() : RegisterPojo {
        registerPojo.email = "eve.holt@reqres.in"
        return registerPojo
    }

    fun registerUserNotFound() : RegisterPojo {
        registerPojo.email = "test"
        registerPojo.password = "pistol"
        return registerPojo
    }

}