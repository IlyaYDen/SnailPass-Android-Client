package com.example.snailpasswordmanager.domain.model

import com.google.gson.annotations.SerializedName
import java.util.UUID

data class UserEntity(
    @SerializedName("id")
    var id: UUID = UUID.fromString("0-0-0-0-0"),
    @SerializedName("email")
    var email: String,
    @SerializedName("master_password_hash")
    var password: String,
    @SerializedName("hint")
    var hint : String, //todo send null if empty
    @SerializedName("is_admin")
    var isAdmin : Boolean = false



    ){
    override fun toString(): String {
        return "UserEntity(id=$id, email='$email', password='$password', hint='$hint', isAdmin=$isAdmin)"
    }
}