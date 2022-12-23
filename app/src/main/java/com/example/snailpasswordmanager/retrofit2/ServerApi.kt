package com.example.snailpasswordmanager.retrofit2

import com.example.snailpasswordmanager.retrofit2.test.test
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface ServerApi {

    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET("/login")
    suspend fun getLogin(@Header("Authorization") s:String): Token;

    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET("/records")
    suspend fun addRecord(@Header("x-access-token") token:String);

    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET("/records")
    suspend fun getRecords(@Header("x-access-token") token:String) : List<Record>;
}

data class Link (val link: String,
                 val id : Long,
                 val LinkText: String
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