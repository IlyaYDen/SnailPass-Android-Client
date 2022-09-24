package com.example.snailpasswordmanager.presentation.mainscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.snailpasswordmanager.data.PasswordListRepositoryImpl
import com.example.snailpasswordmanager.domain.model.PasswordEntity
import com.example.snailpasswordmanager.domain.usecase.Passwords.AddPasswordUseCase
import com.example.snailpasswordmanager.domain.usecase.Passwords.GetPasswordListUseCase
import com.example.snailpasswordmanager.domain.usecase.Passwords.GetPasswordUseCase
import com.example.snailpasswordmanager.domain.usecase.Passwords.RemovePasswordUseCase

class MainListViewModel : ViewModel() {

    private val repository = PasswordListRepositoryImpl

    private val getPasswordListUseCase = GetPasswordListUseCase(repository)
    private val deletePasswordListUseCase = RemovePasswordUseCase(repository)
    private val addPasswordUseCase = AddPasswordUseCase(repository)

    val passwordList = MutableLiveData<List<PasswordEntity>>()

    fun getPasswordList() {
        val list = getPasswordListUseCase.execute()
        passwordList.value = list
    }
    fun deletePassword(passwordEntity: PasswordEntity){
        deletePasswordListUseCase.execute(passwordEntity)
    }
    fun addPassword(passwordEntity: PasswordEntity){
        addPasswordUseCase.execute(passwordEntity)

    }
}