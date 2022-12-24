package com.example.snailpasswordmanager.data.repository

import com.example.snailpasswordmanager.data.database.user.UserDao
import com.example.snailpasswordmanager.domain.model.UserEntity
import com.example.snailpasswordmanager.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val dao: UserDao
) : UserRepository {

    private val UserList = mutableListOf<UserEntity>()

    private var autoIncrementid = 0;

    override fun addUser(userEntity: UserEntity) {
        //if(userEntity.id == UserEntity.UNDEFINED_ID)
        //    userEntity.id = autoIncrementid++
        UserList.add(userEntity)
        //TODO("Not yet implemented")
    }

    override fun removeUser(userEntity: UserEntity) {
        UserList.remove(userEntity)
        //TODO("Not yet implemented")
    }

    override fun getUserList(): List<UserEntity> {
        return UserList.toList()
        //TODO("Not yet implemented")
    }

    override fun getUser(userId: Int): UserEntity? {
        return UserList.get(userId)
        //TODO("Not yet implemented")
    }
    //override fun getUserByName(userId: Int): UserEntity? {
    //    return UserList.get(userId)
    //    //TODO("Not yet implemented")
    //}


}