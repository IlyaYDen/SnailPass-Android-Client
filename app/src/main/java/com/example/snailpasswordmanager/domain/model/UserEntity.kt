package com.example.snailpasswordmanager.domain.model

import java.util.UUID

data class UserEntity(
    var id: UUID = UUID.fromString("0-0-0-0-0"),
    var email: String,
    var password: String,
    var hint : String



    )