package com.example.snailpasswordmanager.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity (tableName = "passwords")
data class PasswordEntity(
    @PrimaryKey(autoGenerate = true) var id:Int? = null,
    @ColumnInfo(name = "service") var service:String,
    @ColumnInfo(name = "login") var login: String,
    @ColumnInfo(name = "password") var password: String,
    @ColumnInfo(name = "timestamp") var timestamp: Long
)

class InvalidPasswordException(message: String): Exception(message)