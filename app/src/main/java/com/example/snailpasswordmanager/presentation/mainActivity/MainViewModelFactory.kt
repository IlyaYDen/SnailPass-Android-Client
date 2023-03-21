package com.example.snailpasswordmanager.presentation.mainActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.snailpasswordmanager.domain.usecase.additionalFields.FieldUseCases
import com.example.snailpasswordmanager.domain.usecase.notes.NoteUseCases
import com.example.snailpasswordmanager.domain.usecase.passwords.PasswordUseCases
import com.example.snailpasswordmanager.presentation.noteList.NoteListViewModel
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(

    var noteUseCase: NoteUseCases,
    var accountUseCase: PasswordUseCases,
    var fieldUseCases: FieldUseCases
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            noteUseCase,accountUseCase,fieldUseCases
        ) as T

    }
}