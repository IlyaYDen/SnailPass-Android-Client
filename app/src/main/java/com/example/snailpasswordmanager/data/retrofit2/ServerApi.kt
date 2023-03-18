package com.example.snailpasswordmanager.data.retrofit2

import com.example.snailpasswordmanager.domain.model.NoteEntity
import com.example.snailpasswordmanager.domain.model.RecordAddFieldEntity
import com.example.snailpasswordmanager.domain.model.UserEntity
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.*
import java.util.*

interface ServerApi {

    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET("/login")
    suspend fun getLogin(@Header("Authorization") s:String): Response<Token>

    @Headers("Content-Type: application/json;charset=UTF-8")
    //"x-access-token: Bearer ааа.bbb.ccc")
    @GET("/users")
    suspend fun getUser(): UserEntity
//saa@aaa.aaa
    @POST("/users")
    suspend fun registration(@Body body: Registration): Response<Gson>

    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST("/records")
    suspend fun addRecord(@Body data: AddRecord)

    @Headers("Content-Type: application/json;charset=UTF-8")
    @PUT("/records")
    suspend fun editRecord(@Body data: Record)

    @Headers("Content-Type: application/json;charset=UTF-8")
    @HTTP(method = "DELETE", path = "/records", hasBody = true)
    suspend fun deleteRecord(@Query("id") data: String)

    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET("/records")
    suspend fun getRecords() : List<RecordGet>?

    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET("/additional_fields")
    suspend fun getAdditionalFields(@Query("id") id : String) : List<RecordAddFieldEntity>?

    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST("/additional_fields")
    suspend fun postAdditionalFields(@Body field:RecordAddFieldEntity)// : Call<JsonObject>?

    @Headers("Content-Type: application/json;charset=UTF-8")
    @PUT("/additional_fields")
    suspend fun editAdditionalFields(@Body field:RecordAddFieldEntity)// : Call<JsonObject>?

    @Headers("Content-Type: application/json;charset=UTF-8")
    @DELETE("/additional_fields")
    suspend fun deleteAdditionalField(@Query("id") data: String)// : Call<JsonObject>?



//notes
    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST("/notes")
    suspend fun createNote(@Body noteEntity: NoteEntity)// : Call<JsonObject>?

    @Headers("Content-Type: application/json;charset=UTF-8")
    @PUT ("/notes")
    suspend fun editNote(@Body noteEntity: NoteEntity)// : Call<JsonObject>?

    @Headers("Content-Type: application/json;charset=UTF-8")
    @DELETE("/notes")
    suspend fun deleteNote(@Query("id") id : String)// : Call<JsonObject>?

    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET("/notes")
    suspend fun getNotes() : List<NoteEntity>?

}

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
    //val nonce: String,
    val password: String,
    val id: String,
    val is_deleted: Boolean = false,
    val is_favorite: Boolean = false,
    val login: String,
    val name: String,
    val user_id: String
){
    override fun toString(): String {
        return "Record(creation_time='$creation_time', update_time='$edited_time', encrypted_password='$password', id='$id', is_deleted=$is_deleted, is_favorite=$is_favorite, login='$login', name='$name', user_id='$user_id')"
    }
}
data class RecordGet constructor(
    val creation_time: String,
    @SerializedName("update_time")
    val edited_time: String,
    //val nonce: String,
    val password: String,
    val id: String,
    val is_deleted: Boolean = false,
    val is_favorite: Boolean = false,
    val login: String,
    val name: String,
    val user_id: String,
    val additional_fields: List<RecordAddFieldEntity>
)

data class AddRecord(
    val id: String,
    val login: String,
    val name: String,
    val password: String,
    val is_favorite: Boolean,
    val is_deleted: Boolean = false,
    //val nonce: String
)