package com.example.unittesting

import com.example.unittesting.util.Registration
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RegistrationTest{

    @Test
    fun `all fields are empty return false`(){
        val input = Registration.validateRegistrationInput(
            "", "", ""
        )
        assertThat(input).isFalse()
    }

    @Test
    fun `empty email returns false`(){
        val input = Registration.validateRegistrationInput("", "123", "123")
        assertThat(input).isFalse()
    }

    @Test
    fun `empty password returns false`(){
        val input = Registration.validateRegistrationInput("xyz@gmail.com", "", "")
        assertThat(input).isFalse()
    }

    @Test
    fun `password == confirmed password returns true`(){
        val input = Registration.validateRegistrationInput("xyz@gmail.com", "1234", "1234")
        assertThat(input).isTrue()
    }

    @Test
    fun `password less than 3 chars returns false`(){
        val input = Registration.validateRegistrationInput("xyz@gmail.com", "12", "12")
        assertThat(input).isFalse()
    }

    @Test
    fun `email has already exist returns false`(){
        val input = Registration.validateRegistrationInput("abc@gmail.com", "1234", "1234")
        assertThat(input).isFalse()
    }
}