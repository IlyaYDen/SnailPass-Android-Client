package com.example.snailpasswordmanager.presentation.recordList

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.snailpasswordmanager.domain.model.RecordEntity
import com.example.snailpasswordmanager.domain.usecase.additionalFields.FieldUseCases
import com.example.snailpasswordmanager.domain.usecase.passwords.PasswordUseCases
import com.example.snailpasswordmanager.utils.NetworkUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

//@HiltViewModel
class RecordListViewModel constructor(
    private val passwordUseCases: PasswordUseCases,
    private val fieldUseCases: FieldUseCases
    ) : ViewModel() {


    //private val _state = mutableListOf<PasswordsState>(PasswordsState())
    //val state: State<PasswordsState> = _state
    //var passwordList : List<RecordEntity> = emptyList()
    var passwordListEdited = MutableStateFlow<List<RecordEntity>>(emptyList())

    @RequiresApi(Build.VERSION_CODES.O)
    fun getPasswords() {
        viewModelScope.launch(Dispatchers.IO) {


            passwordUseCases.getPasswordList().collect {

                passwordListEdited.value = it
                //todo make list load in the same time

                for (t in it) {
                    fieldUseCases.deleteLocalFieldById(t.id)
                    fieldUseCases.cloneField(t.id)
                }
            }
        }

    }

    fun getAddFields() {

        viewModelScope.launch(Dispatchers.IO) {
            //-Log.d("test___", passwordListEdited.value.size.toString())
            //fieldUseCases.deleteFieldsTable()
            for (t in passwordListEdited.value) {
                fieldUseCases.deleteLocalFieldById(t.id)
                fieldUseCases.cloneField(t.id)
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