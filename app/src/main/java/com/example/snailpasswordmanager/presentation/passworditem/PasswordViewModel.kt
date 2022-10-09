package com.example.snailpasswordmanager.presentation.passworditem

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.snailpasswordmanager.domain.model.PasswordEntity
import com.example.snailpasswordmanager.domain.usecase.passwords.PasswordUseCases
import com.example.snailpasswordmanager.presentation.mainscreen.MainListViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class PasswordViewModel constructor(
    private val passwordUseCases: PasswordUseCases
) : ViewModel() {

    fun addPassword(passwordEntity: PasswordEntity) {
        viewModelScope.launch {
            Log.d("addPassword","PasswordEntity: " + passwordEntity.toString())
            passwordUseCases.insertPassword(passwordEntity)
        }
    }

    fun deletePassword(passwordEntity: PasswordEntity) {
        viewModelScope.launch {
            passwordUseCases.deletePassword(passwordEntity)
        }
    }
}

