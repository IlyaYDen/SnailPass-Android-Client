package com.example.snailpasswordmanager.domain.model

import java.util.*

data class RecordAddFieldEntity(
    val id: UUID = UUID.fromString("1-1-1-1-1"),
    var field_name: String,
    var value: String,
    //var nonce: String,
    var record_id: UUID,

)