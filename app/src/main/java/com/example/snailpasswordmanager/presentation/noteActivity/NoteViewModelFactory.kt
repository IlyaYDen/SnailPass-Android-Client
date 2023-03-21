package com.example.snailpasswordmanager.presentation.noteActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.snailpasswordmanager.domain.usecase.additionalFields.FieldUseCases
import com.example.snailpasswordmanager.domain.usecase.cryptography.Decode
import com.example.snailpasswordmanager.domain.usecase.notes.NoteUseCases
import com.example.snailpasswordmanager.domain.usecase.passwords.PasswordUseCases
import com.example.snailpasswordmanager.presentation.accountInfo.AccountInfoViewModel
import javax.inject.Inject

class NoteViewModelFactory @Inject constructor(
    var noteUseCase: NoteUseCases
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NoteViewModel(
            noteUseCase
        ) as T

    }
}