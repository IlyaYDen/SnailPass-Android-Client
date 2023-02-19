package com.example.snailpasswordmanager.presentation.mainscreen

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.snailpasswordmanager.domain.model.RecordEntity
import com.example.snailpasswordmanager.domain.usecase.additionalFields.FieldUseCases
import com.example.snailpasswordmanager.domain.usecase.passwords.PasswordUseCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

//@HiltViewModel
class MainListViewModel constructor(
    private val passwordUseCases: PasswordUseCases,
    private val fieldUseCases: FieldUseCases
    ) : ViewModel() {


    //private val _state = mutableListOf<PasswordsState>(PasswordsState())
    //val state: State<PasswordsState> = _state
    //var passwordList : List<RecordEntity> = emptyList()
    var passwordListEdited = MutableStateFlow<List<RecordEntity>>(emptyList())

    @RequiresApi(Build.VERSION_CODES.O)
    fun getPasswords() {
        Log.d("MYLOG_test","getpass1")

        viewModelScope.launch(Dispatchers.IO) {

            //Log.d("MYLOG_test","GetPasswordList launch")
            passwordUseCases.getPasswordList().collect {
                Log.d("MYLOG_test","getpass2 " + (it?.size))
                if (it != null) {
                    passwordListEdited.value = it
                    //add field get todo

                    for (t in it)
                        fieldUseCases.getField(t.id)
                }
            }
        }

    }

    fun getAddFields() {

        viewModelScope.launch(Dispatchers.IO) {
            Log.d("test___", passwordListEdited.value.size.toString())
            for (t in passwordListEdited.value)
                fieldUseCases.cloneField(t.id)
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