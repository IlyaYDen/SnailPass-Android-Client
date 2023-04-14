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
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class AccountInfoViewModel(
    private val passwordUseCases: PasswordUseCases,
    private val fieldUseCases: FieldUseCases,
    private val decodeUseCase: Decode
): ViewModel() {

    val response = MutableStateFlow(false)
    val fieldListEdited = mutableStateListOf<Pair<RecordAddFieldEntity,Int>>()
    //val fieldListEdited: List<Pair<RecordAddFieldEntity,Int>> = _fieldListEdited

    @RequiresApi(Build.VERSION_CODES.O)
    fun addPassword(passwordEntity: RecordEntity) {
        Log.d("AddPassword", "Adding password: $passwordEntity")
        viewModelScope.launch {
            Log.d("AddPassword", "Inserting password in database")
            passwordUseCases.insertPassword(passwordEntity)
            response.value = true
            Log.d("AddPassword", "Password added successfully")
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun editPassword(passwordEntity: RecordEntity) {
        Log.d("EditPassword", "Editing password: $passwordEntity")
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("EditPassword", "Updating password in database")
            passwordUseCases.editPassword(passwordEntity)
            response.value = true
            Log.d("EditPassword", "Password edited successfully")
        }
    }

    fun addField(id:UUID) {
        Log.d("AddField", "Adding field with id $id")
        fieldListEdited.add(Pair(RecordAddFieldEntity(
            id = UUID.randomUUID(),
            "","",record_id = id
        ),1))
    }

    fun addField(field: RecordAddFieldEntity) {
        Log.d("AddField", "Adding field: $field")
        fieldListEdited.add(Pair(field,1))
    }

    fun editField(name: String, value: String, index: Int) {
        Log.d("EditField", "Editing field at index $index with name: $name and value: $value")
        val item = fieldListEdited[index].first
        item.name = name
        item.value = value
        if(fieldListEdited[index].second == 1)
            fieldListEdited.set(index,Pair(item,1))
        else
            fieldListEdited.set(index,Pair(item,3))
    }

    fun deleteField(index: Int) {
        Log.d("DeleteField", "Deleting field at index $index")
        if(fieldListEdited[index].second == 1){
            fieldListEdited.removeAt(index)
        }
        else {
            fieldListEdited[index] = Pair(fieldListEdited[index].first,2)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addFieldsToServer(subList: MutableList<RecordAddFieldEntity>) {
        Log.d("AddFieldsToServer", "Adding fields to server: $subList")
        viewModelScope.launch {
            fieldUseCases.insertField(subList)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun editFieldsToServer(editedList: ArrayList<RecordAddFieldEntity>) {
        Log.d("EditFieldsToServer", "Editing fields on server: $editedList")
        viewModelScope.launch {
            fieldUseCases.editField(editedList)
        }
    }
    fun deleteFieldsFromServer(deletedList: ArrayList<UUID>) {
        Log.d("DeleteFieldsFromServer", "Starting deletion of fields from server")
        viewModelScope.launch(Dispatchers.IO) {
            fieldUseCases.deleteField(deletedList)
            Log.d("DeleteFieldsFromServer", "Deletion of fields from server completed successfully")
            response.value = true
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun markAsDeletePassword(recordEntity: RecordEntity) {
        Log.d("MarkAsDeletePassword", "Starting mark as delete for password with ID: ${recordEntity.id}")
        viewModelScope.launch(Dispatchers.IO) {
            passwordUseCases.editPassword(
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
            Log.d("MarkAsDeletePassword", "Mark as delete completed successfully for password with ID: ${recordEntity.id}")
            response.value = true
        }
    }
    private var addFieldsJob: Job? = null

    @RequiresApi(Build.VERSION_CODES.O)
    fun getAddFields(id: UUID) {
        Log.d("GetAddFields", "Starting retrieval of additional fields for ID: $id")
        addFieldsJob?.cancel() // прерывание предыдущей корутины
        addFieldsJob = viewModelScope.launch(Dispatchers.IO) {
            fieldListEdited.clear()
            fieldUseCases.getField.invoke(id).collect {
                if (it != null) {
                    val l: MutableList<Pair<RecordAddFieldEntity, Int>> = mutableListOf()
                    for (t in it) {
                        t.name = decodeUseCase.invoke(t.name)
                        t.value = decodeUseCase.invoke(t.value)
                        l.add(Pair(t, 0))
                    }
                    fieldListEdited.clear()
                    fieldListEdited.addAll(l)

                    Log.d("getAddFields", "Additional fields retrieved successfully for ID: $id")
                } else {
                    Log.d("getAddFields", "No additional fields found for ID: $id")
                }
            }
        }
    }

    fun clearList() {
        Log.d("ClearList", "Clearing fieldListEdited")
        fieldListEdited.clear()
    }

}
