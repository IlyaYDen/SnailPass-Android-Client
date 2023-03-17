package com.example.snailpasswordmanager.data.database.record

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.snailpasswordmanager.data.model.NoteEntityDbModel
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes")
    fun getNotes() : Flow<List<NoteEntityDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(noteEntityDbModel: NoteEntityDbModel)

    @Query("DELETE FROM notes WHERE id=:id")
    fun deleteNoteById(id: String)

    @Query("DELETE FROM notes WHERE user_id =:id")
    suspend fun deleteNotes(id: UUID)//(recordEntityDbModel: RecordEntityDbModel)

    @Query("SELECT * FROM notes WHERE id=:id")
    fun getNoteById(id: String) : Flow<NoteEntityDbModel>

}