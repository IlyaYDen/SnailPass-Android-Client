package com.example.snailpasswordmanager.presentation.noteList

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.snailpasswordmanager.domain.model.NoteEntity
import com.example.snailpasswordmanager.domain.model.RecordEntity
import com.example.snailpasswordmanager.domain.usecase.notes.NoteUseCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.*

class NoteListViewModel(
    private val noteUseCases: NoteUseCases
) : ViewModel()  {

    val response = MutableStateFlow(false)

    //private val _state = mutableListOf<PasswordsState>(PasswordsState())
    //val state: State<PasswordsState> = _state
    //var passwordList : List<RecordEntity> = emptyList()
    //var noteListEdited = MutableStateFlow<List<NoteEntity>>(emptyList())
    var noteListEdited = mutableStateOf<List<NoteEntity>>(emptyList())

    @RequiresApi(Build.VERSION_CODES.O)
    fun getNotes() {
        //-Log.d("MYLOG_test","getpass1")

        viewModelScope.launch(Dispatchers.IO) {

            noteUseCases.getNoteList().collect {
                if (it != null) {
                    noteListEdited.value = it
                    //todo make list load in the same time
                }
            }
        }

    }

    var note = MutableStateFlow(NoteEntity(
        "","","",false,false,"","",""
    ))



    private var addFieldsJob: Job? = null
    @RequiresApi(Build.VERSION_CODES.O)
    fun getNoteById(id: UUID) {

        addFieldsJob?.cancel() // прерывание предыдущей корутины
        addFieldsJob = viewModelScope.launch(Dispatchers.IO) {
            noteUseCases.getNoteById(id).collect {
                note.value = it
            }
        }
    }

    fun deleteNote(id: String) {

        viewModelScope.launch(Dispatchers.IO) {
            noteUseCases.deleteNote(UUID.fromString(id))
            response.value = true
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun insertNote(noteEntity: NoteEntity) {

        viewModelScope.launch(Dispatchers.IO) {
            noteUseCases.insertNote(noteEntity)

            response.value = true
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun editNote(noteEntity: NoteEntity) {

        viewModelScope.launch(Dispatchers.IO) {
            noteUseCases.editNote(noteEntity)
            response.value = true
        }
    }

    fun clearNotes() {
        note.value = NoteEntity(
            "","","",false,false,"","",""
        )
    }

}
