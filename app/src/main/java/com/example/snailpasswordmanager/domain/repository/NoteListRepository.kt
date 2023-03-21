package com.example.snailpasswordmanager.domain.repository

import com.example.snailpasswordmanager.domain.model.NoteEntity
import com.example.snailpasswordmanager.domain.model.RecordEntity
import kotlinx.coroutines.flow.Flow
import java.util.*

interface NoteListRepository {

    suspend fun getNoteList(): Flow<List<NoteEntity>?>

    suspend fun insertNote(noteEntity: NoteEntity)

    suspend fun editNote(noteEntity: NoteEntity)

    suspend fun deleteNote(id: UUID)

    fun getNoteById(id: UUID) : Flow<NoteEntity>
}