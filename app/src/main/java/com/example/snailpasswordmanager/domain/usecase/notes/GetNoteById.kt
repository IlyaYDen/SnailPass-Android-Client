package com.example.snailpasswordmanager.domain.usecase.notes

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.snailpasswordmanager.data.repository.AuthorizationData
import com.example.snailpasswordmanager.domain.crypto.AES.AESUtil
import com.example.snailpasswordmanager.domain.model.NoteEntity
import com.example.snailpasswordmanager.domain.model.UserEntity
import com.example.snailpasswordmanager.domain.repository.NoteListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*
import javax.inject.Inject

class GetNoteById @Inject constructor(
    private val noteListRepository: NoteListRepository,
    private var userEntityAuth: AuthorizationData
) {
    @RequiresApi(Build.VERSION_CODES.O)
    operator fun invoke(id: UUID): Flow<NoteEntity> {
        return noteListRepository.getNoteById(id).map {

            //val masterpass = userEntityAuth.password.toByteArray()//Base64.getDecoder().decode(userEntityAuth.password)
            val masterpass = Base64.getDecoder().decode(userEntityAuth.user.password)

            val name = it.name.split(":")
            val nameD = AESUtil.decrypt(Base64.getDecoder().decode(name[0].toByteArray()), masterpass, Base64.getDecoder().decode(name[1].toByteArray()))

            val content = it.content.split(":")
            val contentD = AESUtil.decrypt(Base64.getDecoder().decode(content[0].toByteArray()), masterpass, Base64.getDecoder().decode(content[1].toByteArray()))


            NoteEntity(
                id = it.id,
                name = String(nameD),
                content = String(contentD),
                is_favorite = it.is_favorite,
                is_deleted = it.is_deleted,
                creation_time = it.creation_time,
                update_time = it.update_time,
                user_id = it.user_id
            )

        }
    }
}