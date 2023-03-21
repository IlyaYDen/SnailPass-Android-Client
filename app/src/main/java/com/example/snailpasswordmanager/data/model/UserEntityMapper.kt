package com.example.snailpasswordmanager.data.model

import android.util.Log
import com.example.snailpasswordmanager.domain.model.UserEntity
import java.util.*

object UserEntityMapper {
    fun mapEntityToDbModel(userEntity: UserEntity) : UserEntityDbModel{
        //-Log.d("MYLOG_test","mapEntityToDbModel")
        return UserEntityDbModel(
            email = userEntity.email,
            password = userEntity.password,
            id = userEntity.id.toString(),
            hint = userEntity.hint,
            is_admin = userEntity.isAdmin
        )
    }
    fun mapDbModelToEntity(userEntityDbModel: UserEntityDbModel) : UserEntity{
        return UserEntity(
            email = userEntityDbModel.email,
            password = userEntityDbModel.password,
            id = UUID.fromString(userEntityDbModel.id),
            hint = userEntityDbModel.hint,
            isAdmin = userEntityDbModel.is_admin
        )
    }
    fun mapListDbModelToListEntity(list: List<UserEntityDbModel>) : List<UserEntity>{
        return list.map { mapDbModelToEntity(it) }
    }

}