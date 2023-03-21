package com.example.snailpasswordmanager.domain.model

import androidx.room.ColumnInfo

data class NoteEntity(
    var id: String,
    var name: String,
    var content: String,
    var is_favorite: Boolean,
    var is_deleted: Boolean,
    var creation_time: String,
    var update_time: String,
    var user_id: String
)