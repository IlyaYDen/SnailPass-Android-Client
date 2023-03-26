package com.example.snailpasswordmanager.domain.usecase.passwords

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.snailpasswordmanager.data.repository.AuthorizationData
import com.example.snailpasswordmanager.domain.crypto.AES.AESUtil
import com.example.snailpasswordmanager.domain.model.RecordEntity
import com.example.snailpasswordmanager.domain.model.UserEntity
import com.example.snailpasswordmanager.domain.repository.RecordListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*
import javax.inject.Inject
import kotlin.math.log

class GetPasswordList @Inject constructor(
    private val passwordListRepository: RecordListRepository,
    private var userEntityAuth: AuthorizationData
    ) {

    @RequiresApi(Build.VERSION_CODES.O)
    suspend operator fun invoke(): Flow<List<RecordEntity>> {
        return passwordListRepository.getRecordList().map { records ->
            if(records == null) {
                return@map emptyList()
            }
            records.filter { it.userId == userEntityAuth.user.id.toString() }
                .map { record ->
                    val masterpass = Base64.getDecoder().decode(userEntityAuth.user.password)
                    val login = AESUtil.decrypt(
                        Base64.getDecoder().decode(record.login.split(":")[0].toByteArray()),
                        masterpass,
                        Base64.getDecoder().decode(record.login.split(":")[1].toByteArray())
                    )
                    val name = AESUtil.decrypt(
                        Base64.getDecoder().decode(record.name.split(":")[0].toByteArray()),
                        masterpass,
                        Base64.getDecoder().decode(record.name.split(":")[1].toByteArray())
                    )
                    val encrypted_password = AESUtil.decrypt(
                        Base64.getDecoder()
                            .decode(record.encrypted_password.split(":")[0].toByteArray()),
                        masterpass,
                        Base64.getDecoder()
                            .decode(record.encrypted_password.split(":")[1].toByteArray())
                    )
                    RecordEntity(
                        id = record.id,
                        name = String(name),
                        login = String(login),
                        encrypted_password = String(encrypted_password),
                        editedTime = record.editedTime,
                        creationTime = record.creationTime,
                        isfavorite = record.isfavorite,
                        userId = record.userId,
                        isdeleted = record.isdeleted
                    )
                }
        }
    }
}
