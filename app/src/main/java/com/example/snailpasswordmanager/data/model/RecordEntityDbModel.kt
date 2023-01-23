package com.example.snailpasswordmanager.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "passwords")
data class RecordEntityDbModel(
    @PrimaryKey(autoGenerate = false)   var id:UUID,
    @ColumnInfo(name = "service")       var service:String,
    @ColumnInfo(name = "login")         var login: String,
    @ColumnInfo(name = "nonce")         var nonce: String,
    @ColumnInfo(name = "user_id")     var userId: String,
    @ColumnInfo(name = "isfavorite")     var isfavorite: Boolean,
    @ColumnInfo(name = "password")      var password: String,
    @ColumnInfo(name = "edited_time")     var editedTime: String,
    @ColumnInfo(name = "creation_time")     var createdTime: String,
) {
    override fun toString(): String {
        return "RecordEntityDbModel(id=$id, service='$service', login='$login', nonce='$nonce', userId='$userId', isfavorite=$isfavorite, password='$password', editedTime='$editedTime', createdTime='$createdTime')"
    }
}