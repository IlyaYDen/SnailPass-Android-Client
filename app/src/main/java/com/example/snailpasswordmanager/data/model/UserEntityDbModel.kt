package com.example.snailpasswordmanager.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "users", indices = [Index(value = ["email"], unique = true)])
data class UserEntityDbModel(
    @PrimaryKey var id: String,
    @ColumnInfo(name = "email") var email: String,
    @ColumnInfo(name = "password") var password: String,
    @ColumnInfo(name = "hint") var hint: String?,
    @ColumnInfo(name = "is_admin") var is_admin: Boolean,
)
