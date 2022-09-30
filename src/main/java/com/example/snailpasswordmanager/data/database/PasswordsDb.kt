package com.example.snailpasswordmanager.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.snailpasswordmanager.domain.model.PasswordEntity

const val VERSION = 1

@Database (entities = [PasswordEntity::class], version = VERSION,exportSchema = false)
abstract class PasswordsDb : RoomDatabase() {
    abstract val dao : PassowrdDao

    companion object {
        const val DATABASE_NAME = "passwords_db"
    }
}