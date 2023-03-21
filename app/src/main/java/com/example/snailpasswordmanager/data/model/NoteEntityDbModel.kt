package com.example.snailpasswordmanager.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntityDbModel(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "id") var id: String,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "content") var content: String,
    @ColumnInfo(name = "is_favorite") var is_favorite: Boolean,
    @ColumnInfo(name = "is_deleted") var is_deleted: Boolean,
    @ColumnInfo(name = "creation_time") var creation_time: String,
    @ColumnInfo(name = "update_time") var update_time: String,
    @ColumnInfo(name = "user_id") var user_id: String
)//NoteEntity