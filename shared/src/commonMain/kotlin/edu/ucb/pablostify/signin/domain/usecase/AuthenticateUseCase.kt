package edu.ucb.pablostify.signin.domain.usecase


import edu.ucb.pablostify.signin.domain.model.Email
import edu.ucb.pablostify.signin.domain.model.Password

class AuthenticateUseCase {
    suspend fun invoke(email: Email, password: Password) : Result<Boolean> {
        return if (email.value == "calyr.software@gmail.com" && password.value == "123456") {
            Result.success(true)
        } else {
            Result.failure(Exception("Invalid credential"))
        }
    }
}