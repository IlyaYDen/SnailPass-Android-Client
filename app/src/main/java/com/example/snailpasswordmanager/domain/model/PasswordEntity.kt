package com.example.snailpasswordmanager.domain.model

data class PasswordEntity(
    var id:Int? = null,
    var service:String,
    var login: String,
    var password: String,
    var timestamp: Long
)

class InvalidPasswordException(message: String): Exception(message)