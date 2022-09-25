package com.example.snailpasswordmanager.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.snailpasswordmanager.domain.model.PasswordEntity

@Database (entities = [PasswordEntity::class], version = 1,exportSchema = false)
abstract class PasswordsDb : RoomDatabase() {
    abstract val dao : PassowrdDao

    companion object {
        const val DATABASE_NAME = "passwords_db"
    }
}