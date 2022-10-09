package com.example.snailpasswordmanager.data.database.user

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.snailpasswordmanager.data.database.password.PassowrdDao
import com.example.snailpasswordmanager.data.model.UserEntityDbModel
import com.example.snailpasswordmanager.domain.model.UserEntity


const val VERSION = 1

@Database(entities = [UserEntityDbModel::class], version = VERSION,exportSchema = false)
abstract class UserDb: RoomDatabase() {
    abstract val dao : UserDao
}