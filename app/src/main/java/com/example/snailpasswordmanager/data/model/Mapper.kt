package com.example.snailpasswordmanager.data.model

interface Mapper {
    fun <T,R> mapEntityToDbModel(passwordEntity: T) : R

    fun <T,R> mapDbModelToEntity(EntityDbModel: T) : R

    fun <T,R> mapListDbModelToListEntity(list: List<T>) : R
}