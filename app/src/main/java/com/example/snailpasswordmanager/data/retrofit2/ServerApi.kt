package com.example.snailpasswordmanager.data.retrofit2

import com.example.snailpasswordmanager.domain.model.UserEntity
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import retrofit2.http.*
import java.util.*

interface ServerApi {

    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET("/login")
    suspend fun getLogin(@Header("Authorization") s:String): Token

    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET("/users")
    suspend fun getUser(): UserEntity

    @POST("/users")
    suspend fun registration(@Body body: Registration): Gson

    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST("/records")
    suspend fun addRecord(@Body data: AddRecord)

    @Headers("Content-Type: application/json;charset=UTF-8")
    @HTTP(method = "DELETE", path = "/records", hasBody = true)
    suspend fun deleteRecord(@Query("id") data: String)

    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET("/records")
    suspend fun getRecords() : List<Record>?
}
//todo
data class Registration(
    val id: UUID,
    val email: String,
    var master_password_hash: String,
    val hint: String
)

data class Token (
    var token: String
)
data class Record constructor(
    val creation_time: String,
    @SerializedName("update_time")
    val edited_time: String,
    val nonce: String,
    val encrypted_password: String,
    val id: String,
    val is_deleted: Boolean = false,
    val is_favorite: Boolean = false,
    val login: String,
    val name: String,
    val user_id: String
){
    override fun toString(): String {
        return "Record(creation_time='$creation_time', update_time='$edited_time', nonce='$nonce', encrypted_password='$encrypted_password', id='$id', is_deleted=$is_deleted, is_favorite=$is_favorite, login='$login', name='$name', user_id='$user_id')"
    }
}

data class AddRecord(
    val id: String,
    val login: String,
    val name: String,
    val encrypted_password: String,
    val nonce: String
)