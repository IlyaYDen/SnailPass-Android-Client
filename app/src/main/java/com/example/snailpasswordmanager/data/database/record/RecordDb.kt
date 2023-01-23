package com.example.snailpasswordmanager.data.database.record

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.snailpasswordmanager.data.model.RecordEntityDbModel
import com.example.snailpasswordmanager.data.model.UserEntityDbModel

const val VERSION = 1

@Database (entities = [RecordEntityDbModel::class, UserEntityDbModel::class], version = VERSION,exportSchema = false)
abstract class RecordDb : RoomDatabase() {
    abstract val recordDao : RecordDao
    abstract val userDao : UserDao

    companion object {
        const val DATABASE_NAME = "records_db"
    }
}