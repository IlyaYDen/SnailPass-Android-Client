package com.example.snailpasswordmanager.data.model

import com.example.snailpasswordmanager.domain.model.NoteEntity
import com.example.snailpasswordmanager.domain.model.RecordAddFieldEntity
import java.util.*

class NoteEntityMapper {
    fun mapEntityToDbModel(noteEntity: NoteEntity) : NoteEntityDbModel{
        //-Log.d("MYLOG_test","mapEntityToDbModel")
        return NoteEntityDbModel(
            id = noteEntity.id,
            name = noteEntity.name,
            content = noteEntity.content,
            is_favorite = noteEntity.is_favorite,
            is_deleted = noteEntity.is_deleted,
            creation_time = noteEntity.creation_time,
            update_time = noteEntity.update_time,
            user_id = noteEntity.user_id
        )
    }
    fun mapDbModelToEntity(noteEntityDbModel: NoteEntityDbModel) : NoteEntity {
        return NoteEntity(
            id = noteEntityDbModel.id,
            name = noteEntityDbModel.name,
            content = noteEntityDbModel.content,
            is_favorite = noteEntityDbModel.is_favorite,
            is_deleted = noteEntityDbModel.is_deleted,
            creation_time = noteEntityDbModel.creation_time,
            user_id = noteEntityDbModel.user_id,
            update_time = noteEntityDbModel.update_time
        )
    }
    fun mapListDbModelToListEntity(list: List<NoteEntityDbModel>) : List<NoteEntity>{
        return list.map { mapDbModelToEntity(it) }
    }
}