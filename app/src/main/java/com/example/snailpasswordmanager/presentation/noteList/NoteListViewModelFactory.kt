package com.example.snailpasswordmanager.presentation.noteList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.snailpasswordmanager.domain.usecase.notes.NoteUseCases
import javax.inject.Inject

class NoteListViewModelFactory @Inject constructor(

    var noteUseCase: NoteUseCases,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NoteListViewModel(
            noteUseCase
        ) as T

    }
}