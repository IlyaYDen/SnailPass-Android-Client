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
    fun addPassword(passwordEntity: RecordEntity, subList: MutableList<RecordAddFieldEntity>) {
        viewModelScope.launch {
            passwordUseCases.insertPassword(passwordEntity)
            fieldUseCases.insertField(subList)
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun addPassword(passwordEntity: RecordEntity) {
        viewModelScope.launch {
            passwordUseCases.insertPassword(passwordEntity)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun editPassword(passwordEntity: RecordEntity) {
        viewModelScope.launch {
            passwordUseCases.editPassword(passwordEntity)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun editPassword(passwordEntity: RecordEntity,subList: MutableList<RecordAddFieldEntity>) {
        viewModelScope.launch {
            passwordUseCases.editPassword(passwordEntity)
            fieldUseCases.editField(subList)
        }
    }
    fun deletePassword(id: UUID) {
        viewModelScope.launch {
            passwordUseCases.deletePassword(id)
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
