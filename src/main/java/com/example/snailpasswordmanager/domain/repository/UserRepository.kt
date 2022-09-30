package com.example.snailpasswordmanager.domain.repository

import com.example.snailpasswordmanager.domain.model.UserEntity


interface UserRepository {
    fun addUser(userEntity: UserEntity)
    fun removeUser(userEntity: UserEntity)
    fun getUserList():List<UserEntity>
    fun getUser(userId: Int):UserEntity?
}