package com.example.snailpasswordmanager.data.database.password

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.snailpasswordmanager.data.model.PasswordEntityDbModel
import com.example.snailpasswordmanager.domain.model.PasswordEntity

const val VERSION = 1

@Database (entities = [PasswordEntityDbModel::class], version = VERSION,exportSchema = false)
abstract class PasswordDb : RoomDatabase() {
    abstract val dao : PassowrdDao

    companion object {
        const val DATABASE_NAME = "passwords_db"
    }
}