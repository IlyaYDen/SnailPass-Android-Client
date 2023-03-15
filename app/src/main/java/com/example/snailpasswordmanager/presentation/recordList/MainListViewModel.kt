package com.example.snailpasswordmanager.presentation.recordList

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
//todo make work with server in step-by-step - one request full done only then send next one
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
        //-Log.d("MYLOG_test","getpass1")

        Log.d("MYLOG_testgetpass1","start")
        viewModelScope.launch(Dispatchers.IO) {

            Log.d("MYLOG_testgetpass1","start2")
            ////-Log.d("MYLOG_test","GetPasswordList launch")
            passwordUseCases.getPasswordList().collect {

                Log.d("MYLOG_testgetpass1","collect")
                //-Log.d("MYLOG_test","getpass2 " + (it?.size))
                if (it != null) {
                    Log.d("MYLOG_testgetpass1","getpass1")
                    passwordListEdited.value = it

                    Log.d("MYLOG_testgetpass1","getpass2")
                    //todo make password list load in the same time

                    for (t in it)
                        fieldUseCases.getField(t.id)
                }
            }
        }

    }

    fun getAddFields() {

        viewModelScope.launch(Dispatchers.IO) {
            //-Log.d("test___", passwordListEdited.value.size.toString())
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