package com.example.snailpasswordmanager.domain.model

import java.util.UUID

data class RecordEntity(
    var id:UUID = UUID.fromString("1-1-1-1-1"),
    var name:String = "",
    var login: String = "",
    var userId: String = "",
    var isfavorite: Boolean,
    var isdeleted: Boolean,
    var encrypted_password: String = "",
    var editedTime: String = "",
    val creationTime: String = "",
){
    override fun toString(): String {
        return "RecordEntity(id=$id, name='$name', login='$login', userId='$userId', isfavorite=$isfavorite, encrypted_password='$encrypted_password', editedTime='$editedTime', creationTime='$creationTime')"
    }
}

class InvalidRecordException(message: String): Exception(message)



/*

    val encrypted_password: String,
    val id: String,
    val is_deleted: Boolean,
    val is_favorite: Boolean,
    val login: String,
    val name: String,
    val user_id: String
 */