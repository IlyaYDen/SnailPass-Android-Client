package com.example.snailpasswordmanager.presentation.mainActivity

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.snailpasswordmanager.domain.usecase.additionalFields.FieldUseCases
import com.example.snailpasswordmanager.domain.usecase.notes.NoteUseCases
import com.example.snailpasswordmanager.domain.usecase.passwords.PasswordUseCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val noteUseCases: NoteUseCases,
    private val passwordUseCases: PasswordUseCases,
    private val fieldUseCases: FieldUseCases
) : ViewModel()  {
    @RequiresApi(Build.VERSION_CODES.O)
    fun getAllData(){
        viewModelScope.launch(Dispatchers.IO) {
            noteUseCases.getNoteList()
        }
    }
}