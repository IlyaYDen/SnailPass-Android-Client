package com.example.snailpasswordmanager.presentation.recordList

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.snailpasswordmanager.domain.model.RecordEntity
import com.example.snailpasswordmanager.domain.usecase.passwords.PasswordUseCases
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

//@HiltViewModel
@RequiresApi(Build.VERSION_CODES.O)
class RecordListViewModel constructor(
    private val passwordUseCases: PasswordUseCases
    ) : ViewModel() {

    var passwordListEdited = mutableStateOf<List<RecordEntity>>(emptyList())

    @RequiresApi(Build.VERSION_CODES.O)
    fun getPasswords() {
        viewModelScope.launch(Dispatchers.IO) {

            passwordUseCases.getPasswordList().collect {

                passwordListEdited.value = it
            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)//todo send only favorite or deleted
    fun editPassword(recordEntity: RecordEntity) {
        viewModelScope.launch(Dispatchers.IO) {

            passwordUseCases.editPassword(recordEntity)

            var list = mutableListOf<RecordEntity>()
            list.addAll(passwordListEdited.value)

            for (i in 0 until list.size-1) {
                if(list[i].id == recordEntity.id) {
                    list.set(i,recordEntity)
                }
            }
            passwordListEdited.value = list
        }
    }

}