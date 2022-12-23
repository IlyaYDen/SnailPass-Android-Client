package com.example.snailpasswordmanager.domain.usecase.passwords

import android.util.Log
import com.example.snailpasswordmanager.domain.model.InvalidRecordException
import com.example.snailpasswordmanager.domain.model.RecordEntity
import com.example.snailpasswordmanager.domain.repository.RecordListRepository
import javax.inject.Inject

class InsertPassword @Inject constructor(
    private val recordListRepository: RecordListRepository
) {

    @Throws(InvalidRecordException::class)
    suspend operator fun invoke(passwordEntity: RecordEntity){
        Log.d("testb","2")
        if(passwordEntity.service.isBlank())
            throw InvalidRecordException("The service can't be empty.")
        if(passwordEntity.login.isBlank())
            throw InvalidRecordException("The login can't be empty.")
        if(passwordEntity.password.isBlank())
            throw InvalidRecordException("The password can't be empty.")
        recordListRepository.insertRecord(passwordEntity)
    }
}
