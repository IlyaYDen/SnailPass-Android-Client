package com.example.snailpasswordmanager.domain.model

data class RecordEntity(
    var id:Int? = null,
    var service:String,
    var login: String,
    var password: String,
    var timestamp: Long
)

class InvalidRecordException(message: String): Exception(message)