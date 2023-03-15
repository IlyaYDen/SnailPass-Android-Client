package com.example.snailpasswordmanager.presentation.accountInfo

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
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
    var fieldListEdited = MutableStateFlow<List<RecordAddFieldEntity>>(emptyList())

    @RequiresApi(Build.VERSION_CODES.O)
    fun addFields(subList: MutableList<RecordAddFieldEntity>) {
        viewModelScope.launch {
            fieldUseCases.insertField(subList)
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun addPassword(passwordEntity: RecordEntity, subList: MutableList<RecordAddFieldEntity>) {
        viewModelScope.launch {
            Log.d("addPassword", "start")
            passwordUseCases.insertPassword(passwordEntity)
            fieldUseCases.insertField(subList)
            Log.d("addPassword", "start2")
            responce.value = true
            Log.d("addPassword", "start3")
        }
    }
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

    var responce = MutableStateFlow(false)
    @RequiresApi(Build.VERSION_CODES.O)
    fun editPassword(passwordEntity: RecordEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            passwordUseCases.editPassword(passwordEntity)
            responce.value = true
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun editFields(editedList: ArrayList<RecordAddFieldEntity>) {
        viewModelScope.launch {
            fieldUseCases.editField(editedList)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun editPassword(passwordEntity: RecordEntity,subList: MutableList<RecordAddFieldEntity>) {
        viewModelScope.launch(Dispatchers.IO) {
            passwordUseCases.editPassword(passwordEntity)
            fieldUseCases.editField(subList)
            responce.value = true
        }
    }
    fun deletePassword(id: UUID) {
        viewModelScope.launch(Dispatchers.IO) {
            passwordUseCases.deletePassword(id)
            responce.value = true
        }
    }

    fun deleteFields(deletedList: ArrayList<UUID>) {
        viewModelScope.launch(Dispatchers.IO) {
            fieldUseCases.deleteField(deletedList)
            responce.value = true
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getAddFields(id:UUID) {
        viewModelScope.launch {
            //-Log.d("test","test")
            fieldUseCases.getField.invoke(id).collect {
                //-Log.d("test","test2")
                if(it!=null) {

                    val l :MutableList<RecordAddFieldEntity> = mutableListOf()

                    for (t in it) {

                        t.name = decodeUseCase.invoke(t.name)
                        t.value = decodeUseCase.invoke(t.value)
                        l.add(t)
                    }
                    fieldListEdited.value = l
                }
                //-else ttt

                    //-Log.d("test","empty2")
            }

        }
    }


}
