package com.example.snailpasswordmanager.data.database.record

import androidx.room.*
import com.example.snailpasswordmanager.data.model.UserEntityDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getUsers() : Flow<List<UserEntityDbModel>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(userEntityDbModel: UserEntityDbModel)

}