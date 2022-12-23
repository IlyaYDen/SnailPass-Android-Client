package com.example.snailpasswordmanager.presentation.mainscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.snailpasswordmanager.domain.model.RecordEntity
import com.example.snailpasswordmanager.domain.usecase.passwords.PasswordUseCases
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

//@HiltViewModel
class MainListViewModel constructor(
    private val passwordUseCases: PasswordUseCases
    ) : ViewModel() {


    //private val _state = mutableListOf<PasswordsState>(PasswordsState())
    //val state: State<PasswordsState> = _state

    private var recentlyDeletedPassword: RecordEntity? = null

    fun getPasswords(): Flow<List<RecordEntity>> {
        Log.d("test","getpass1")
        return passwordUseCases.getPasswordList()

    }
    fun onEvent(event: PasswordsEvent) {


        when (event) {

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
            is PasswordsEvent.GetPasswordsList -> {
                viewModelScope.launch {
                    passwordUseCases.getPasswordList()
                }
            }
            is PasswordsEvent.ToggleOrderSection -> {

            }
            is PasswordsEvent.Order -> {

            }
            else -> {

            }
        }
    }

    fun updateDb() {
        TODO("Not yet implemented")
    }
}





//private fun getPassowrds(passwordOrder: PasswordOrder) {
    //    passwordUseCases.getPasswordList(passwordOrder)
    //        .onEach { passwords ->
    //
    //        }
    //}
//}