package com.example.snailpasswordmanager.presentation.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.snailpasswordmanager.domain.model.PasswordEntity
import com.example.snailpasswordmanager.domain.usecase.passwords.PasswordUseCases
import com.example.snailpasswordmanager.domain.util.PasswordOrder
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainListViewModel @Inject constructor(
    private val passwordUseCases: PasswordUseCases
    ) : ViewModel() {

    //private val _state = mutableListOf<PasswordsState>(PasswordsState())
    //val state: State<PasswordsState> = _state

    private var recentlyDeletedPassword: PasswordEntity? = null

    fun onEvent(event: PasswordsEvent) {
        when(event){

            is PasswordsEvent.Order ->{

            }
            is PasswordsEvent.AddPassword ->{
                viewModelScope.launch {
                    var passwordEntity = PasswordEntity(service = "test", password = "test", login = "test", timestamp = System.nanoTime())
                    passwordUseCases.insertPassword(passwordEntity)
                }
            }
            is PasswordsEvent.DeletePassword ->{
                viewModelScope.launch {
                    passwordUseCases.deletePassword(event.passwordEntity)
                    recentlyDeletedPassword = event.passwordEntity
                }

            }
            is PasswordsEvent.RestorePassword ->{
                viewModelScope.launch {
                    passwordUseCases.insertPassword(recentlyDeletedPassword ?: return@launch)
                    recentlyDeletedPassword = null
                }

            }
            is PasswordsEvent.ToggleOrderSection ->{

            }
        }
    }

    //private fun getPassowrds(passwordOrder: PasswordOrder) {
    //    passwordUseCases.getPasswordList(passwordOrder)
    //        .onEach { passwords ->
    //
    //        }
    //}
}