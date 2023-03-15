package com.example.snailpasswordmanager.domain.repository

import com.example.snailpasswordmanager.LoginMode
import com.example.snailpasswordmanager.data.retrofit2.Registration
import com.example.snailpasswordmanager.domain.model.UserEntity
import com.google.gson.Gson
import retrofit2.Response


interface UserRepository {
    suspend fun addUser(userEntity: Registration) : Response<Gson>
    fun removeUser(userEntity: UserEntity)
    fun getUserList():List<UserEntity>
    fun getUser(userId: Int):UserEntity?
    suspend fun getLoginAccess(user: UserEntity, encodedString : String):Pair<String, LoginMode>
}