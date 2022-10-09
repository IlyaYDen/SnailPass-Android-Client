package com.example.snailpasswordmanager.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "passwords")
data class PasswordEntityDbModel(
    @PrimaryKey(autoGenerate = true) var id:Int? = null,
    @ColumnInfo(name = "service") var service:String,
    @ColumnInfo(name = "login") var login: String,
    @ColumnInfo(name = "password") var password: String,
    @ColumnInfo(name = "timestamp") var timestamp: Long
)