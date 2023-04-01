package com.example.snailpasswordmanager.presentation.accountInfo

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.snailpasswordmanager.domain.model.RecordAddFieldEntity
import com.example.snailpasswordmanager.domain.model.RecordEntity
import com.example.snailpasswordmanager.domain.usecase.cryptography.Decode
import com.example.snailpasswordmanager.domain.usecase.additionalFields.FieldUseCases
import com.example.snailpasswordmanager.domain.usecase.passwords.PasswordUseCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class AccountInfoViewModel(
    private val passwordUseCases: PasswordUseCases,
    private val fieldUseCases: FieldUseCases,
    private val decodeUseCase: Decode
): ViewModel() {

    val responce = MutableStateFlow(false)
    private val _fieldListEdited = mutableStateListOf<Pair<RecordAddFieldEntity,Int>>()
    val fieldListEdited: List<Pair<RecordAddFieldEntity,Int>> = _fieldListEdited

    @RequiresApi(Build.VERSION_CODES.O)
    fun addPassword(passwordEntity: RecordEntity) {
        viewModelScope.launch {
            Log.d("addPassword", "start")
            passwordUseCases.insertPassword(passwordEntity)
            Log.d("addPassword", "start2")
            responce.value = true
            Log.d("addPassword", "start3")
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun editPassword(passwordEntity: RecordEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            passwordUseCases.editPassword(passwordEntity)
            responce.value = true
        }
    }

    fun addField(id:UUID) {
        _fieldListEdited.add(Pair(RecordAddFieldEntity(
            id = UUID.randomUUID(),
            "","",record_id = id
        ),1))
    }
    fun addField(field: RecordAddFieldEntity) {
        _fieldListEdited.add(Pair(field,1))
    }
    fun editField(name: String, value: String, index: Int) {
        val item = _fieldListEdited[index].first
        item.name = name
        item.value = value
        if(_fieldListEdited[index].second == 1)
            _fieldListEdited.set(index,Pair(item,1))
        else
            _fieldListEdited.set(index,Pair(item,3))
    }
    fun deleteField(index: Int) {
        if(_fieldListEdited[index].second == 1){
            _fieldListEdited.removeAt(index)
        }
        else {
            _fieldListEdited[index] = Pair(_fieldListEdited[index].first,2)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addFieldsToServer(subList: MutableList<RecordAddFieldEntity>) {
        viewModelScope.launch {
            fieldUseCases.insertField(subList)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun editFieldsToServer(editedList: ArrayList<RecordAddFieldEntity>) {
        viewModelScope.launch {
            fieldUseCases.editField(editedList)
        }
    }

    fun deleteFieldsFromServer(deletedList: ArrayList<UUID>) {
        viewModelScope.launch(Dispatchers.IO) {
            fieldUseCases.deleteField(deletedList)
            responce.value = true
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun markAsDeletePassword(recordEntity: RecordEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            passwordUseCases.editPassword(//todo markAsDeletePassword method like passwordUseCases.markAsDeletePassword(UUID)
                RecordEntity(
                    id = recordEntity.id,
                    encrypted_password = recordEntity.encrypted_password,
                    editedTime = recordEntity.editedTime,
                    login = recordEntity.login,
                    name = recordEntity.name,
                    isdeleted = true,
                    userId = recordEntity.userId,
                    isfavorite = recordEntity.isfavorite,
                    creationTime = recordEntity.creationTime
                )
            )
            responce.value = true
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getAddFields(id:UUID) {
        viewModelScope.launch {
            //-Log.d("test","test")

            _fieldListEdited.clear()
            fieldUseCases.getField.invoke(id).collect {
                if(it!=null) {

                    val l :MutableList<Pair<RecordAddFieldEntity,Int>> = mutableListOf()

                    for (t in it) {

                        t.name = decodeUseCase.invoke(t.name)
                        t.value = decodeUseCase.invoke(t.value)
                        //fieldListEdited.value.add(t)
                        l.add(Pair(t,0))
                    }
                    _fieldListEdited.clear()
                    _fieldListEdited.addAll(l)


                }
                //-else ttt

                    //-Log.d("test","empty2")
            }

        }
    }

    fun clearList() {
        _fieldListEdited.clear()
    }


}
