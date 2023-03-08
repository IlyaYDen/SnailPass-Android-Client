package com.example.snailpasswordmanager.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "additional_fields")
data class RecordAddFieldEntityDbModel(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "value") var value: String,
    //@ColumnInfo(name = "nonce") var nonce: String,
    @ColumnInfo(name = "record_id") var record_id: String,
)
