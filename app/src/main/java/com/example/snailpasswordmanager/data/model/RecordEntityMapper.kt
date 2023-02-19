package com.example.snailpasswordmanager.data.model

import android.util.Log
import com.example.snailpasswordmanager.domain.model.RecordEntity
import java.util.*

class RecordEntityMapper {
    //fun mapEntityToDbModel(recordEntity: RecordEntity) = RecordEntityDbModel(
    //    id = recordEntity.id,
    //    password = recordEntity.encrypted_password,
    //    timestamp = recordEntity.timestamp,
    //    login = recordEntity.login,
    //    service = recordEntity.name
    //)
    fun mapEntityToDbModel(recordEntity: RecordEntity): RecordEntityDbModel{
        //Log.d("MYLOG_testN","test1")
        return RecordEntityDbModel(
            id = recordEntity.id.toString(),
            password = recordEntity.encrypted_password,
            editedTime = recordEntity.editedTime,
            login = recordEntity.login,
            service = recordEntity.name,
            //nonce = recordEntity.nonce,
            userId = recordEntity.userId,
            isfavorite = recordEntity.isfavorite,
            createdTime = recordEntity.creationTime
        )
    }
    fun mapDbModelToEntity(recordEntityDbModel: RecordEntityDbModel) = RecordEntity(
        id = UUID.fromString(recordEntityDbModel.id),
        encrypted_password = recordEntityDbModel.password,
        editedTime = recordEntityDbModel.editedTime,
        login = recordEntityDbModel.login,
        name = recordEntityDbModel.service,
        creationTime = recordEntityDbModel.createdTime,
        //nonce = recordEntityDbModel.nonce,

        userId = recordEntityDbModel.userId,
        isfavorite = recordEntityDbModel.isfavorite,
    )

    fun mapListDbModelToListEntity(list: List<RecordEntityDbModel>) = list.map {


        mapDbModelToEntity(it)
    }

}