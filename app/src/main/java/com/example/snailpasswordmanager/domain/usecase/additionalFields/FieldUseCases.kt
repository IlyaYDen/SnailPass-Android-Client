package com.example.snailpasswordmanager.domain.usecase.additionalFields

import javax.inject.Inject

data class FieldUseCases @Inject constructor(
    val insertField: InsertField,
    val editField: EditField,
    val getField: GetField,
    val cloneField: CloneField,
    val deleteField: DeleteField
)