package com.example.snailpasswordmanager.domain.usecase.passwords

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
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
    private var userEntityAuth: UserEntity
    ) {

    @RequiresApi(Build.VERSION_CODES.O)
    suspend operator fun invoke (
        //passwordOrder: PasswordOrder = PasswordOrder.Service(OrderType.Ascending)
    ) : Flow<List<RecordEntity>?> {
        //-Log.d("MYLOG_test", " invoke ")
        return passwordListRepository.getRecordList().map {


            if (it != null) {

                val list = emptyList<RecordEntity>().toMutableList()

                for(r in it){
                    if(r.userId == userEntityAuth.id.toString()) {


                        //val masterpass = userEntityAuth.password.toByteArray()//Base64.getDecoder().decode(userEntityAuth.password)
                        val masterpass = Base64.getDecoder().decode(userEntityAuth.password)

                        val logins = r.login.split(":")
                        val login = AESUtil.decrypt(Base64.getDecoder().decode(logins[0].toByteArray()), masterpass, Base64.getDecoder().decode(logins[1].toByteArray()))

                        val names = r.name.split(":")
                        val name = AESUtil.decrypt(Base64.getDecoder().decode(names[0].toByteArray()), masterpass, Base64.getDecoder().decode(names[1].toByteArray()))

                        val encrypted_passwords = r.encrypted_password.split(":")
                        val encrypted_password = AESUtil.decrypt(Base64.getDecoder().decode(encrypted_passwords[0].toByteArray()), masterpass, Base64.getDecoder().decode(encrypted_passwords[1].toByteArray()))

                        list.add(RecordEntity(
                            id = r.id,
                            name = String(name),
                            login = String(login),
                            encrypted_password = String(encrypted_password),
                            editedTime = r.editedTime,
                            creationTime = r.creationTime,
                            isfavorite = r.isfavorite,
                            userId = r.userId
                        ))

                    }
                }
                return@map list
            }
            else return@map null
        }
    }
}