package com.example.snailpasswordmanager.presentation.mainscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.snailpasswordmanager.domain.model.PasswordEntity
import com.example.snailpasswordmanager.domain.usecase.passwords.GetPasswordList
import com.example.snailpasswordmanager.domain.usecase.passwords.PasswordUseCases
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltViewModel
class MainListViewModel constructor(
    private val passwordUseCases: PasswordUseCases
    ) : ViewModel() {


    //private val _state = mutableListOf<PasswordsState>(PasswordsState())
    //val state: State<PasswordsState> = _state

    private var recentlyDeletedPassword: PasswordEntity? = null

    fun getPasswords(): Flow<List<PasswordEntity>> {
        Log.d("test","getpass1")
        return passwordUseCases.getPasswordList()

    }
    fun onEvent(event: PasswordsEvent) {

        when (event) {

            is PasswordsEvent.Order -> {

            }
            is PasswordsEvent.AddPassword -> {
                viewModelScope.launch {
                    Log.d("testb","1")

                    //val passwordEntity = event.passwordEntity

                    passwordUseCases.insertPassword(event.passwordEntity)
                }
            }
            is PasswordsEvent.DeletePassword -> {
                viewModelScope.launch {
                    passwordUseCases.deletePassword(event.passwordEntity)
                    recentlyDeletedPassword = event.passwordEntity
                }

            }
            is PasswordsEvent.RestorePassword -> {
                viewModelScope.launch {
                    passwordUseCases.insertPassword(recentlyDeletedPassword ?: return@launch)
                    recentlyDeletedPassword = null
                }

            }
            is PasswordsEvent.ToggleOrderSection -> {

            }
        }
    }
}





//private fun getPassowrds(passwordOrder: PasswordOrder) {
    //    passwordUseCases.getPasswordList(passwordOrder)
    //        .onEach { passwords ->
    //
    //        }
    //}
//}