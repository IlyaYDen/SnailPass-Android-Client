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

                        //-Log.d("test_masterpass", userEntityAuth.password)
                        //val login = AESUtil.decrypt("ow8nOksC84lKh/ACg4CZdQ==".toByteArray(), authInfo.hash2, "tttttttttttttttt".toByteArray())
                        val logins = r.login.split(":")
                        //-Log.d("test",logins[0] + " _ " + logins[1])
                        //val login = AESUtil.decrypt(logins[0].toByteArray(), masterpass, logins[1].toByteArray())
                        val login = AESUtil.decrypt(Base64.getDecoder().decode(logins[0].toByteArray()), masterpass, Base64.getDecoder().decode(logins[1].toByteArray()))

                        ////-Log.d("MYLOG_test2","GetPasswordList LOGiN")
                        val names = r.name.split(":")
                        //val name = AESUtil.decrypt(names[0].toByteArray(), masterpass, names[1].toByteArray())
                        val name = AESUtil.decrypt(Base64.getDecoder().decode(names[0].toByteArray()), masterpass, Base64.getDecoder().decode(names[1].toByteArray()))
                        ////-Log.d("MYLOG_test2","GetPasswordList")

                        val encrypted_passwords = r.encrypted_password.split(":")
                        //val encrypted_password = AESUtil.decrypt(encrypted_passwords[0].toByteArray(), masterpass, encrypted_passwords[1].toByteArray())
                        val encrypted_password = AESUtil.decrypt(Base64.getDecoder().decode(encrypted_passwords[0].toByteArray()), masterpass, Base64.getDecoder().decode(encrypted_passwords[1].toByteArray()))

                        list.add(RecordEntity(
                            id = r.id,
                            name = String(name),
                            login = String(login),
                            //nonce = r.nonce,
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

/*
            val a = it?.map { r ->

                // com.example.snailpasswordmanager    test@mail.ru
                // com.example.snailpasswordmanager    vNs5FGnpkEUQKxZIPsj9Mp56Vb3zsTEyx7C4hqLeWwE=

                //-Log.d("MYLOG_authEntity", "GetPasswordList: " + userEntityAuth.toString())


                //-Log.d("MYLOG_test1", r.toString())

                //-Log.d("MYLOG_test2",r.login)
                //-Log.d("MYLOG_test2",r.nonce)
                //-Log.d("MYLOG_test2",userEntityAuth.password)

                val masterpass = Base64.getDecoder().decode(userEntityAuth.password)

                //val login = AESUtil.decrypt("ow8nOksC84lKh/ACg4CZdQ==".toByteArray(), authInfo.hash2, "tttttttttttttttt".toByteArray())
                val login = AESUtil.decrypt(r.login.toByteArray(), masterpass, r.nonce.toByteArray())
                //-Log.d("MYLOG_test2","GetPasswordList LOGiN")
                val name = AESUtil.decrypt(r.name.toByteArray(), masterpass, r.nonce.toByteArray())
                //-Log.d("MYLOG_test2","GetPasswordList")
                val encrypted_password = AESUtil.decrypt(r.encrypted_password.toByteArray(), masterpass, r.nonce.toByteArray())


                //-Log.d("MYLOG_test2","GetPasswordList")
                RecordEntity(
                    id = r.id,
                    name = String(name),
                    login = String(login),
                    nonce = r.nonce,
                    encrypted_password = String(encrypted_password),
                    editedTime = r.editedTime,
                    creationTime = r.creationTime,
                    isfavorite = r.isfavorite,
                    userId = r.userId
                )
            }
            if (a != null) {
                val list = a.toMutableList()
                for( t in a){
                    //-Log.d("MYLOG_PASSWORDS" , "id 1: " + t.toString())
                    //-Log.d("MYLOG_PASSWORDS" , "id 1: " + t.userId)
                    //-Log.d("MYLOG_PASSWORDS" , "id 2: " + userEntityAuth.id)
                    if(t.userId != userEntityAuth.id.toString())
                        list.removeAt(list.indexOf(t))
                    return@map list
                }
            }
            a*/
        }


        //return passwordListRepository.getRecordList()
    /*.map { passwords ->
            when(passwordOrder.orderType) {
                is OrderType.Ascending->{

                    when(passwordOrder){
                        is PasswordOrder.Service -> passwords.sortedBy { it.name.lowercase(Locale.getDefault()) }
                        is PasswordOrder.Date -> passwords.sortedBy { it.timestamp }
                    }
                }
                is OrderType.Descending->{

                    when(passwordOrder){
                        is PasswordOrder.Service -> passwords.sortedByDescending { it.name.lowercase(
                            Locale.getDefault()
                        ) }
                        is PasswordOrder.Date -> passwords.sortedByDescending { it.timestamp }
                    }
                }
            }
        }*/
    }
}