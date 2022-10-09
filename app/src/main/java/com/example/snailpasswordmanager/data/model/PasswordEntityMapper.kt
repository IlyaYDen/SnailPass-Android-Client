package com.example.snailpasswordmanager.data.model

import com.example.snailpasswordmanager.domain.model.PasswordEntity

class PasswordEntityMapper {
    fun mapEntityToDbModel(passwordEntity: PasswordEntity) = PasswordEntityDbModel(
        id = passwordEntity.id,
        password = passwordEntity.password,
        timestamp = passwordEntity.timestamp,
        login = passwordEntity.login,
        service = passwordEntity.service
    )
    fun mapDbModelToEntity(passwordEntityDbModel: PasswordEntityDbModel) = PasswordEntity(
        id = passwordEntityDbModel.id,
        password = passwordEntityDbModel.password,
        timestamp = passwordEntityDbModel.timestamp,
        login = passwordEntityDbModel.login,
        service = passwordEntityDbModel.service
    )

    fun mapListDbModelToListEntity(list: List<PasswordEntityDbModel>) = list.map {
        mapDbModelToEntity(it)
    }

}