package com.example.snailpasswordmanager.data.model

import android.util.Log
import com.example.snailpasswordmanager.domain.model.RecordAddFieldEntity
import com.example.snailpasswordmanager.domain.model.UserEntity
import java.util.*

class RecordAddFieldEntityMapper {
    fun mapEntityToDbModel(recordAddFieldEntity: RecordAddFieldEntity) : RecordAddFieldEntityDbModel{
        Log.d("MYLOG_test","mapEntityToDbModel")
        return RecordAddFieldEntityDbModel(
            id = recordAddFieldEntity.id.toString(),
            field_name = recordAddFieldEntity.field_name,
            value = recordAddFieldEntity.value,
            //nonce = recordAddFieldEntity.nonce,
            record_id = recordAddFieldEntity.record_id.toString()
        )
    }
    fun mapDbModelToEntity(recordAddFieldEntity: RecordAddFieldEntityDbModel) : RecordAddFieldEntity{
        return RecordAddFieldEntity(
            id = UUID.fromString(recordAddFieldEntity.id),
            field_name = recordAddFieldEntity.field_name,
            value = recordAddFieldEntity.value,
            //nonce = recordAddFieldEntity.nonce,
            record_id = UUID.fromString(recordAddFieldEntity.record_id)
        )
    }
    fun mapListDbModelToListEntity(list: List<RecordAddFieldEntityDbModel>) : List<RecordAddFieldEntity>{
        return list.map { mapDbModelToEntity(it) }
    }

}