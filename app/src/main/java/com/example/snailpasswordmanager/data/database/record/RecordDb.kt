package com.example.snailpasswordmanager.data.database.record

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.snailpasswordmanager.data.model.RecordAddFieldEntityDbModel
import com.example.snailpasswordmanager.data.model.RecordEntityDbModel
import com.example.snailpasswordmanager.data.model.UserEntityDbModel
import com.example.snailpasswordmanager.domain.model.RecordAddFieldEntity

const val VERSION = 3

@Database (
    entities = [RecordEntityDbModel::class, UserEntityDbModel::class, RecordAddFieldEntityDbModel::class],
    version = VERSION,
    exportSchema = false
)
abstract class RecordDb : RoomDatabase() {
    abstract val recordDao : RecordDao
    abstract val userDao : UserDao
    abstract val recordAddFieldDao : RecordAddFieldDao

    companion object {
        const val DATABASE_NAME = "records_db"
    }
}