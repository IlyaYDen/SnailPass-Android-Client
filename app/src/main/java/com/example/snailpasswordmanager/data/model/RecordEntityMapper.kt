package com.example.snailpasswordmanager.data.model

import com.example.snailpasswordmanager.domain.model.RecordEntity

class RecordEntityMapper {
    fun mapEntityToDbModel(recordEntity: RecordEntity) = RecordEntityDbModel(
        id = recordEntity.id,
        password = recordEntity.password,
        timestamp = recordEntity.timestamp,
        login = recordEntity.login,
        service = recordEntity.service
    )
    fun mapDbModelToEntity(recordEntityDbModel: RecordEntityDbModel) = RecordEntity(
        id = recordEntityDbModel.id,
        password = recordEntityDbModel.password,
        timestamp = recordEntityDbModel.timestamp,
        login = recordEntityDbModel.login,
        service = recordEntityDbModel.service
    )

    fun mapListDbModelToListEntity(list: List<RecordEntityDbModel>) = list.map {
        mapDbModelToEntity(it)
    }

}