package com.example.snailpasswordmanager.data.repository

import android.util.Log
import com.example.snailpasswordmanager.data.database.record.UserDao
import com.example.snailpasswordmanager.data.model.UserEntityMapper
import com.example.snailpasswordmanager.data.retrofit2.Registration
import com.example.snailpasswordmanager.data.retrofit2.ServerApi
import com.example.snailpasswordmanager.data.retrofit2.Token
import com.example.snailpasswordmanager.domain.model.UserEntity
import com.example.snailpasswordmanager.domain.repository.UserRepository
import kotlinx.coroutines.flow.first
import okhttp3.Credentials
import retrofit2.HttpException
import java.io.IOException
import java.util.*
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val dao: UserDao,
    var serverApi: ServerApi,
    var userEntityAuth: UserEntity,
    var token: Token
) : UserRepository {
    //todo - add level between usecases and repository
    private val UserList = mutableListOf<UserEntity>()


    override suspend fun addUser(userEntity: Registration): Boolean {
        //if(userEntity.id == UserEntity.UNDEFINED_ID)
        //    userEntity.id = autoIncrementid++
        try {
            serverApi.registration(userEntity)

            val user2 = UserEntity(
                id = UUID.randomUUID(),
                email = userEntity.email,
                password = userEntity.master_password_hash,
                hint = userEntity.hint
            )
            val user = UserEntityMapper.mapEntityToDbModel(
                user2
            ) // testtesttest
            Log.d("MYLOG_testUUID",user2.id.toString())
            Log.d("MYLOG_testUUID",user.id.toString())
            userEntityAuth.id = UUID.fromString(user.id)
            dao.addUser(
                user
            )

            Log.d("MYLOG_test","test")
            return true


        } catch (e: HttpException) {
            Log.d("MYLOG_test", "Server error")
            return false
        } catch (e: IOException) {
            Log.d("MYLOG_test", "internet error " )
            return false
        }

        //TODO("Not yet implemented")
    }

    override fun removeUser(userEntity: UserEntity) {
        UserList.remove(userEntity)
        //TODO("Not yet implemented")
    }

    override fun getUserList(): List<UserEntity> {
        return UserList.toList()
        //TODO("Not yet implemented")
    }

    override fun getUser(userId: Int): UserEntity? {
        return UserList.get(userId)
        //TODO("Not yet implemented")
    }

    override suspend fun getloginAccess(user: UserEntity, encodedString : String): Boolean {

        try{




            val credentials: String = Credentials.basic(user.email,encodedString)
            val request = serverApi.getLogin(credentials)
            token.token = request.token

            val request2 = serverApi.getUser()


            Log.d("MYLOG_test","getloginAccess "+ encodedString + " - "+ request2.toString())
            userEntityAuth.id = request2.id
            dao.addUser(
                UserEntityMapper.mapEntityToDbModel(
                    UserEntity(
                        id = request2.id,
                        email = request2.email,
                        password = encodedString,
                        hint = request2.hint,
                        isAdmin = request2.isAdmin
                    )
                )
            )



            return true
        }
        catch (e : HttpException){ //todo if it is server bug?
            Log.d("MYLOG_test","test")
            return false
        }
        catch (e : Exception){
            Log.d("MYLOG_test","t  "+e.message)
            var bu = false
            var bua = 1


            var t = dao.getUsers().first()
            t.map { b->
                Log.d("MYLOG_test","Пользователь " + b.email + " : " + b.password)
                if(user.email.equals(b.email) && encodedString.equals(b.password)){
                    bu = true
                    userEntityAuth.id = UUID.fromString(b.id)
                    bua++
                    Log.d("MYLOG_test","Ответ положительный  " + bu)
                }
            }
           // dao.getUsers().collect { a ->
           //     val t = UserEntityMapper.mapListDbModelToListEntity(a)
           //     Log.d("MYLOG_test","Я " + user.email + " : " + encodedString)
           //
           // }

            Log.d("MYLOG_test","ОТвет 1  " + bu)
            Log.d("MYLOG_test","ОТвет 2  " + bua)
            return bu
        }


        return false
        //TODO("Not yet implemented")
    }
    //override fun getUserByName(userId: Int): UserEntity? {
    //    return UserList.get(userId)
    //    //TODO("Not yet implemented")
    //}


}