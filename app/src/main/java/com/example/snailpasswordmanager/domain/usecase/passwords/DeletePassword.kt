package com.example.snailpasswordmanager.domain.usecase.passwords

import com.example.snailpasswordmanager.domain.model.RecordEntity
import com.example.snailpasswordmanager.domain.repository.RecordListRepository
import javax.inject.Inject

class DeletePassword @Inject constructor(
    private val passwordListRepository: RecordListRepository
     ) {
    suspend operator fun invoke(passwordEntity: RecordEntity){
        passwordListRepository.deleteRecord(passwordEntity)
    }
}