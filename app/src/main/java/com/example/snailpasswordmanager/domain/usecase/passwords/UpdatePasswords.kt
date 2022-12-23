package com.example.snailpasswordmanager.domain.usecase.passwords

import com.example.snailpasswordmanager.domain.model.InvalidRecordException
import com.example.snailpasswordmanager.domain.model.RecordEntity
import com.example.snailpasswordmanager.domain.repository.RecordListRepository
import com.example.snailpasswordmanager.retrofit2.ServerApi
import javax.inject.Inject


class UpdatePasswords @Inject constructor(
    private val passwordListRepository: RecordListRepository,
    var serverApi: ServerApi
) {

    @Throws(InvalidRecordException::class)
    suspend operator fun invoke(passwordEntity: RecordEntity){

        passwordListRepository.insertRecord(passwordEntity)
    }
}
