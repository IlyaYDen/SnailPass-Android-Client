package com.example.snailpasswordmanager.data.database.record

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.snailpasswordmanager.data.model.RecordEntityDbModel

const val VERSION = 1

@Database (entities = [RecordEntityDbModel::class], version = VERSION,exportSchema = false)
abstract class RecordDb : RoomDatabase() {
    abstract val dao : RecordDao

    companion object {
        const val DATABASE_NAME = "records_db"
    }
}