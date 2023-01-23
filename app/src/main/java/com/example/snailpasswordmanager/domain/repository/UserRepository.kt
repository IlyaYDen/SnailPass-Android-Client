package com.example.snailpasswordmanager.domain.repository

import com.example.snailpasswordmanager.data.retrofit2.Registration
import com.example.snailpasswordmanager.domain.model.UserEntity


interface UserRepository {
    suspend fun addUser(userEntity: Registration) : Boolean
    fun removeUser(userEntity: UserEntity)
    fun getUserList():List<UserEntity>
    fun getUser(userId: Int):UserEntity?
    suspend fun getloginAccess(user: UserEntity, encodedString : String):Boolean
}