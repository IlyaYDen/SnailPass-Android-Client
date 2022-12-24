package com.example.snailpasswordmanager.data.model

import com.example.snailpasswordmanager.domain.model.UserEntity
import java.util.*

class UserEntityMapper {
    fun mapEntityToDbModel(userEntity: UserEntity) : UserEntityDbModel{
        return UserEntityDbModel(
            email = userEntity.email,
            password = userEntity.password,
            id = userEntity.id,
            hint = userEntity.hint,
            is_admin = 0 // todo
        )
    }
    fun mapDbModelToEntity(userEntityDbModel: UserEntityDbModel) : UserEntity{
        return UserEntity(
            email = userEntityDbModel.email,
            password = userEntityDbModel.password,
            id = //if(userEntityDbModel.id==null) UUID.fromString(userEntityDbModel.email) else
            userEntityDbModel.id, //todo
            hint = userEntityDbModel.hint,
            //admin = userEntityDbModel.is_admin todo
        )
    }
    fun mapListDbModelToListEntity(list: List<UserEntityDbModel>) : List<UserEntity>{
        return list.map { mapDbModelToEntity(it) }
    }
}