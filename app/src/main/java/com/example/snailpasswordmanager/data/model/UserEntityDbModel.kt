package com.example.snailpasswordmanager.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.snailpasswordmanager.domain.model.UserEntity

@Entity(tableName = "users")
data class UserEntityDbModel(
    @PrimaryKey(autoGenerate = true) var id:Int? = null,
    @ColumnInfo(name = "login") var login: String,
    @ColumnInfo(name = "password") var password: String
)
