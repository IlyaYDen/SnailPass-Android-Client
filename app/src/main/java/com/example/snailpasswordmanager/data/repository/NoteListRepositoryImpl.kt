package com.example.snailpasswordmanager.data.repository

import com.example.snailpasswordmanager.data.database.record.NoteDao
import com.example.snailpasswordmanager.data.model.NoteEntityDbModel
import com.example.snailpasswordmanager.data.model.NoteEntityMapper
import com.example.snailpasswordmanager.data.model.RecordEntityMapper
import com.example.snailpasswordmanager.data.retrofit2.ServerApi
import com.example.snailpasswordmanager.domain.model.NoteEntity
import com.example.snailpasswordmanager.domain.repository.NoteListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.util.*
import javax.inject.Inject

class NoteListRepositoryImpl @Inject constructor(
    var serverApi: ServerApi,
    private val noteDao: NoteDao,
) : NoteListRepository {


    private val noteEntityMapper = NoteEntityMapper()

    override suspend fun getNoteList(): Flow<List<NoteEntity>?> {
        try{
            val notes = serverApi.getNotes()
            noteDao.deleteNotes()
            if(notes!=null){
                notes.map {
                    noteDao.insertNote(noteEntityMapper.mapEntityToDbModel(
                        it
                    )
                    )
                }
            }
        } catch (e : HttpException) {
            //-Log.d("MYLOG_testER","FAIL serverApi.getRecords()")
            if (e.code() == 404) {

                noteDao.deleteNotes()
            }
            return flow {}
        }catch (e : Exception){
            return noteDao.getNotes().map {
                noteEntityMapper.mapListDbModelToListEntity(it)
            }

        }
        return noteDao.getNotes().map {
            noteEntityMapper.mapListDbModelToListEntity(it)
        }
    }

    override suspend fun insertNote(noteEntity: NoteEntity) {
        try {
            serverApi.createNote(noteEntity)
            noteDao.insertNote(noteEntityMapper.mapEntityToDbModel(noteEntity))
        }catch (_: Exception){

        }
    }

    override suspend fun editNote(noteEntity: NoteEntity) {
        try {
            serverApi.editNote(noteEntity)
            noteDao.deleteNoteById(noteEntity.id)
            noteDao.insertNote(noteEntityMapper.mapEntityToDbModel(noteEntity))
        }catch (_: Exception){

        }
    }

    override suspend fun deleteNote(id: UUID) {
        try {
            serverApi.deleteNote(id.toString())
            noteDao.deleteNoteById(id.toString())

        } catch (_: Exception){

        }
    }

    override fun getNoteById(id: UUID): Flow<NoteEntity> {


        val t = noteDao.getNoteById(id.toString())
        return t.map {
            noteEntityMapper.mapDbModelToEntity(it)
        }
    }

}