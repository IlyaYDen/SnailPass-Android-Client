package com.example.snailpasswordmanager.presentation.passworditem

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.snailpasswordmanager.domain.model.RecordEntity
import com.example.snailpasswordmanager.domain.usecase.passwords.PasswordUseCases
import kotlinx.coroutines.launch

class PasswordViewModel constructor(
    private val passwordUseCases: PasswordUseCases
) : ViewModel() {

    fun addPassword(passwordEntity: RecordEntity) {
        viewModelScope.launch {
            Log.d("addPassword","PasswordEntity: " + passwordEntity.toString())
            passwordUseCases.insertPassword(passwordEntity)
        }
    }

    fun deletePassword(recordEntity: RecordEntity) {
        viewModelScope.launch {
            passwordUseCases.deletePassword(recordEntity)
        }
    }
}

