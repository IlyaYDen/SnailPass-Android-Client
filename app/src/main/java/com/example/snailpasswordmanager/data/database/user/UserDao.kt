package com.example.snailpasswordmanager.data.database.user

import androidx.room.*
import com.example.snailpasswordmanager.data.model.UserEntityDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getUsers() : Flow<List<UserEntityDbModel>>

    //@Query("SELECT * FROM users")
    //fun getUserByName(userEntityDbModel: UserEntityDbModel) : Flow<UserEntityDbModel>
}