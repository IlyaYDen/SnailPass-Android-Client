package com.example.snailpasswordmanager.domain.model

data class PasswordEntity(
    var service:String,
    var login: String,
    var password: String,

    var id:Int = UNDEFINED_ID
) {
    companion object {
        const val UNDEFINED_ID = -1
    }
}