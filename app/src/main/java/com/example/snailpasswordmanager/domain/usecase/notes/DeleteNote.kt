package com.example.snailpasswordmanager.domain.usecase.notes

import com.example.snailpasswordmanager.domain.repository.NoteListRepository
import java.util.*
import javax.inject.Inject

class DeleteNote @Inject constructor(
    private val noteListRepository: NoteListRepository
) {
    suspend operator fun invoke(id: UUID){
        noteListRepository.deleteNote(id)
    }
}