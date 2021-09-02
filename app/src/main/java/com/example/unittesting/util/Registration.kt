package com.example.unittesting.util

/** This validates the registration process */
object Registration {

    val existingUserMail = listOf(
        "abc@gmail.com",
        "def@gmail.com"
    )

    fun validateRegistrationInput(
        email: String,
        password: String,
        confirmedPassword: String
    ): Boolean{
        if(email.isEmpty() || password.isEmpty() || confirmedPassword.isEmpty()) return false
        if(email in existingUserMail) return false
        if(password.count() < 3) return false
        if(password != confirmedPassword) return false
        return true
    }
}