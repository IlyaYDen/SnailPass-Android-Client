package com.example.snailpasswordmanager.data.model

import com.example.snailpasswordmanager.domain.model.UserEntity

class UserEntityMapper {
    fun mapEntityToDbModel(userEntity: UserEntity) : UserEntityDbModel{
        return UserEntityDbModel(
            login = userEntity.login,
            password = userEntity.password,
            id = userEntity.id
        )
    }
    fun mapDbModelToEntity(userEntityDbModel: UserEntityDbModel) : UserEntity{
        return UserEntity(
            login = userEntityDbModel.login,
            password = userEntityDbModel.password,
            id = if(userEntityDbModel.id==null) UserEntity.UNDEFINED_ID else userEntityDbModel.id!! //todo
        )
    }
    fun mapListDbModelToListEntity(list: List<UserEntityDbModel>) : List<UserEntity>{
        return list.map { mapDbModelToEntity(it) }
    }
}