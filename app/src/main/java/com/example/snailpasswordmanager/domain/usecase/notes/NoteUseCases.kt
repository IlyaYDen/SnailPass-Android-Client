package com.example.snailpasswordmanager.domain.usecase.notes

import com.example.snailpasswordmanager.domain.usecase.passwords.DeletePassword
import com.example.snailpasswordmanager.domain.usecase.passwords.EditPassword
import com.example.snailpasswordmanager.domain.usecase.passwords.GetPasswordList
import com.example.snailpasswordmanager.domain.usecase.passwords.InsertPassword
import javax.inject.Inject

data class NoteUseCases @Inject constructor(
    val getNoteList: GetNoteList,
    val deleteNote: DeleteNote,
    val editNote: EditNote,
    val insertNote: InsertNote,
    val getNoteById: GetNoteById,
)