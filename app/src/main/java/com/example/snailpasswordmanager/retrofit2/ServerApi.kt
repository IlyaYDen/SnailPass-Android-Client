package com.example.snailpasswordmanager.retrofit2

import com.example.snailpasswordmanager.domain.model.UserEntity
import com.example.snailpasswordmanager.retrofit2.test.test
import retrofit2.Response
import retrofit2.http.*

interface ServerApi {

    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET("/login")
    suspend fun getLogin(@Header("Authorization") s:String): Token;

    @POST("/users")
    suspend fun registration(@Body body:Registration): Token;

    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET("/records")
    suspend fun addRecord(@Header("x-access-token") token:String);

    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET("/records")
    suspend fun getRecords(@Header("x-access-token") token:String) : List<Record>;
}

data class Registration (val id: String,
                         val email : String,
                         var master_password_hash: String? = null,
                         val nonce: String
)
data class Token (val token: String
)
data class Record(
    val creation_time: String,
    val encrypted_password: String,
    val id: String,
    val is_deleted: Boolean,
    val is_favorite: Boolean,
    val login: String,
    val name: String,
    val user_id: String
)