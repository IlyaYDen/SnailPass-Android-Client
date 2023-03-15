package com.example.snailpasswordmanager.presentation.noteList

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.snailpasswordmanager.domain.model.NoteEntity
import com.example.snailpasswordmanager.domain.model.RecordEntity
import com.example.snailpasswordmanager.domain.usecase.notes.NoteUseCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class NoteListViewModel(
    private val noteUseCases: NoteUseCases
) : ViewModel()  {


    //private val _state = mutableListOf<PasswordsState>(PasswordsState())
    //val state: State<PasswordsState> = _state
    //var passwordList : List<RecordEntity> = emptyList()
    var noteListEdited = MutableStateFlow<List<NoteEntity>>(emptyList())

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

}
