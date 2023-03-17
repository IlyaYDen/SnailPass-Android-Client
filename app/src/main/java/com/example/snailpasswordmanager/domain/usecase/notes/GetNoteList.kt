package com.example.snailpasswordmanager.domain.usecase.notes

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.snailpasswordmanager.data.repository.AuthorizationData
import com.example.snailpasswordmanager.domain.crypto.AES.AESUtil
import com.example.snailpasswordmanager.domain.model.NoteEntity
import com.example.snailpasswordmanager.domain.model.RecordEntity
import com.example.snailpasswordmanager.domain.model.UserEntity
import com.example.snailpasswordmanager.domain.repository.NoteListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*
import javax.inject.Inject

class GetNoteList @Inject constructor(
    private val noteListRepository: NoteListRepository,
    private var userEntityAuth: AuthorizationData
) {
    @RequiresApi(Build.VERSION_CODES.O)
    suspend operator fun invoke() : Flow<List<NoteEntity>?> {
        //-Log.d("MYLOG_test", " invoke ")
        return noteListRepository.getNoteList().map {


            if (it != null) {

                val list = emptyList<NoteEntity>().toMutableList()

                for(r in it){
                    if(r.user_id == userEntityAuth.user.id.toString()) {


                        //val masterpass = userEntityAuth.password.toByteArray()//Base64.getDecoder().decode(userEntityAuth.password)
                        val masterpass = Base64.getDecoder().decode(userEntityAuth.user.password)

                        val name = r.name.split(":")
                        val nameD = AESUtil.decrypt(Base64.getDecoder().decode(name[0].toByteArray()), masterpass, Base64.getDecoder().decode(name[1].toByteArray()))

                        val content = r.content.split(":")
                        val contentD = AESUtil.decrypt(Base64.getDecoder().decode(content[0].toByteArray()), masterpass, Base64.getDecoder().decode(content[1].toByteArray()))

                        list.add(
                            NoteEntity(
                                id = r.id,
                                name = String(nameD),
                                content = String(contentD),
                                is_favorite = r.is_favorite,
                                is_deleted = r.is_deleted,
                                creation_time = r.creation_time,
                                update_time = r.update_time,
                                user_id = r.user_id
                            )
                        )

                    }
                }
                return@map list
            }
            else return@map null
        }
    }
}