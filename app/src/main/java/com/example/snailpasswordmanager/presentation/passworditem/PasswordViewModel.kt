package com.example.snailpasswordmanager.presentation.passworditem

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.snailpasswordmanager.domain.model.RecordEntity
import com.example.snailpasswordmanager.domain.usecase.passwords.PasswordUseCases
import kotlinx.coroutines.launch

class PasswordViewModel constructor(
    private val passwordUseCases: PasswordUseCases
) : ViewModel() {

    @RequiresApi(Build.VERSION_CODES.O)
    fun addPassword(passwordEntity: RecordEntity) {
        Log.d("MYLOG_testAdd",passwordEntity.toString())
        viewModelScope.launch {
            passwordUseCases.insertPassword(passwordEntity)
        }
    }

    fun deletePassword(recordEntity: RecordEntity) {
        viewModelScope.launch {
            passwordUseCases.deletePassword(recordEntity)
        }
    }
}

