package com.example.snailpasswordmanager.presentation.recordList

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.snailpasswordmanager.domain.usecase.additionalFields.FieldUseCases
import com.example.snailpasswordmanager.domain.usecase.passwords.PasswordUseCases
import javax.inject.Inject


class RecordListViewModelFactory @Inject constructor(

    var passwordUseCases: PasswordUseCases,
    //var fieldUseCases: FieldUseCases
) : ViewModelProvider.Factory {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RecordListViewModel(
            passwordUseCases = passwordUseCases
        ) as T

    }
}