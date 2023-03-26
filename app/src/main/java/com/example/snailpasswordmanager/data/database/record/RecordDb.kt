package com.example.snailpasswordmanager.data.database.record

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.snailpasswordmanager.data.model.NoteEntityDbModel
import com.example.snailpasswordmanager.data.model.RecordAddFieldEntityDbModel
import com.example.snailpasswordmanager.data.model.RecordEntityDbModel
import com.example.snailpasswordmanager.data.model.UserEntityDbModel
import com.example.snailpasswordmanager.domain.model.RecordAddFieldEntity
const val VERSION = 6

@Database (
    entities = [RecordEntityDbModel::class, UserEntityDbModel::class, RecordAddFieldEntityDbModel::class, NoteEntityDbModel::class],
    version = VERSION,
    exportSchema = true
)
abstract class RecordDb : RoomDatabase() {
    abstract val recordDao : RecordDao
    abstract val userDao : UserDao
    abstract val recordAddFieldDao : RecordAddFieldDao
    abstract val noteDao : NoteDao

    companion object {
        const val DATABASE_NAME = "records_db"
    }
}
//@Entity
//data class test(
//    @PrimaryKey(autoGenerate = true) val t : Int,
//    val t2 : Int
//)