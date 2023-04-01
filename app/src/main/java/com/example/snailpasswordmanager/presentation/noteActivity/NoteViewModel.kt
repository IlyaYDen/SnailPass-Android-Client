package com.example.snailpasswordmanager.presentation.noteActivity

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.snailpasswordmanager.domain.model.NoteEntity
import com.example.snailpasswordmanager.domain.usecase.notes.NoteUseCases
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.*

class NoteViewModel(
    private val noteUseCases: NoteUseCases
) : ViewModel() {
    var note = MutableStateFlow<NoteEntity?>(null)
    var boolean = MutableStateFlow<Boolean>(false)
    @RequiresApi(Build.VERSION_CODES.O)
    fun getNoteById(id: UUID) {

        viewModelScope.launch(Dispatchers.IO) {
            return@launch noteUseCases.getNoteById(id).collect {
                //note.value = it
                note.emit(it)
            }
        }
    }

    fun deleteNote(id: String) {

        viewModelScope.launch(Dispatchers.IO) {
            noteUseCases.deleteNote(UUID.fromString(id))
            boolean.value = true
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun insertNote(noteEntity: NoteEntity) {

        viewModelScope.launch(Dispatchers.IO) {
            noteUseCases.insertNote(noteEntity)

            boolean.value = true
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun editNote(noteEntity: NoteEntity) {

        viewModelScope.launch(Dispatchers.IO) {
            noteUseCases.editNote(noteEntity)
            boolean.value = true
        }
    }
}