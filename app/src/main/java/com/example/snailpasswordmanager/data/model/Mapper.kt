package com.example.snailpasswordmanager.data.model

import com.example.snailpasswordmanager.domain.model.PasswordEntity

interface Mapper {
    fun <T,R> mapEntityToDbModel(passwordEntity: T) : R

    fun <T,R> mapDbModelToEntity(EntityDbModel: T) : R

    fun <T,R> mapListDbModelToListEntity(list: List<T>) : R
}