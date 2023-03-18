package com.example.snailpasswordmanager.data.repository

import com.example.snailpasswordmanager.LoginMode
import com.example.snailpasswordmanager.data.database.record.UserDao
import com.example.snailpasswordmanager.data.model.UserEntityMapper
import com.example.snailpasswordmanager.data.retrofit2.Registration
import com.example.snailpasswordmanager.data.retrofit2.ServerApi
import com.example.snailpasswordmanager.data.retrofit2.Token
import com.example.snailpasswordmanager.domain.model.UserEntity
import com.example.snailpasswordmanager.domain.repository.UserRepository
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import okhttp3.Credentials
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.util.*
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val dao: UserDao,
    var serverApi: ServerApi,
    var userEntityAuth: AuthorizationData,
    var token: Token
) : UserRepository {
    private val UserList = mutableListOf<UserEntity>()


    override suspend fun addUser(userEntity: Registration): Response<Gson> {
        //if(userEntity.id == UserEntity.UNDEFINED_ID)
        //    userEntity.id = autoIncrementid++
        //try {
            var resp = serverApi.registration(userEntity)

            val user2 = UserEntity(
                id = UUID.randomUUID(),
                email = userEntity.email,
                password = userEntity.master_password_hash,
                hint = userEntity.hint
            )
            val user = UserEntityMapper.mapEntityToDbModel(
                user2
            ) // testtesttest
            //-Log.d("MYLOG_testUUID",user2.id.toString())
            //-Log.d("MYLOG_testUUID",user.id.toString())
            userEntityAuth.user.id = UUID.fromString(user.id)
            dao.addUser(
                user
            )

            //-Log.d("MYLOG_test","test")
            return resp
/*

        } catch (e: HttpException) {
            //-Log.d("MYLOG_test", "Server error")
        } catch (e: IOException) {
            //-Log.d("MYLOG_test", "internet error " )

        }*/

    }

    override fun removeUser(userEntity: UserEntity) {
        UserList.remove(userEntity)
    }

    override fun getUserList(): List<UserEntity> {
        return UserList.toList()
    }

    override fun getUser(userId: Int): UserEntity? {
        return UserList.get(userId)
    }

    override suspend fun getLoginAccess(user: UserEntity, encodedString : String): Pair<String, LoginMode> {

        try{




            val credentials: String = Credentials.basic(user.email,encodedString)
            val request = serverApi.getLogin(credentials)
            val body = request.body()
            val t = request.errorBody()

            if(request.isSuccessful && body != null) {

                token.token = body.token

                val request2 = serverApi.getUser()

                userEntityAuth.user.id = request2.id
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



                userEntityAuth.loginMode = LoginMode.ONLINE
                return Pair("",LoginMode.ONLINE)
            }
            else if(t !=null) {

                return Pair(Gson().fromJson(t.string(), JsonObject::class.java).get("message").asJsonObject.get("error").toString(),LoginMode.ERROR)
            }
            return Pair("Unexpected_error",LoginMode.ERROR)//todo refactor
        }
        catch (e : Exception){
            //-Log.d("MYLOG_test","t  "+e.message)
            var bu = false
            var bua = 1


            var t = dao.getUsers().first()
            t.map { b->
                //-Log.d("MYLOG_test","Пользователь " + b.email + " : " + b.password)
                if(user.email.equals(b.email) && encodedString.equals(b.password)){
                    bu = true
                    userEntityAuth.user.id = UUID.fromString(b.id)
                    bua++
                }
            }

            if(bu) {
                userEntityAuth.loginMode = LoginMode.OFFLINE
                return Pair("", LoginMode.OFFLINE)
            }
            else
                return Pair("Unexpected_error",LoginMode.ERROR)//todo refactor
        }

    }

    override suspend fun getLoginOfflineAccess(
        user: UserEntity,
        encodedString: String
    ): Pair<String, LoginMode> {
        var t = dao.getUsers()
        t.map { b->
            for(i in b){
                if (user.email.equals(i.email) && encodedString.equals(i.password)){
                    userEntityAuth.user.id = UUID.fromString(i.id)
                }
        }
        }

        userEntityAuth.loginMode = LoginMode.OFFLINE
        return Pair("",LoginMode.OFFLINE)
    }


}
data class AuthorizationData(
    var user: UserEntity,
    var loginMode: LoginMode
)