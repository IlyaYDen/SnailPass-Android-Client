package com.example.snailpasswordmanager

import android.util.Base64
import com.example.snailpasswordmanager.retrofit2.ServerApi
import kotlinx.coroutines.*
import kotlinx.coroutines.test.runTest
import okhttp3.*
import org.junit.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class RetrofitUnitTesting {
    var tkn = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjJkNTM2ODU0LWU5ZDgtNDcxZS1iNTVmLTIzMGJjOTJiZmMxOSIsImV4cCI6MTY3MTYwOTg2MX0.3pZJUb4kzlhQwq3B32rFWnYku5x_d4jUY29wbznJLnE"

    @Test
    fun login_isCorrect() = runTest{
        val credentials: String = Credentials.basic("rebmanop@email.com","ef78adbfaa17db00ff9125f0fa3476601489a1630d17c4d37d712d694a79d7dd3c60731ffe32251ff56d5c30747f1069c1fc26f52884c349986cb5eef7de7503")

        val t = Retrofit.Builder()
            .baseUrl("http://localhost:5000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ServerApi::class.java)
        val a = t.getLogin(credentials)
        tkn = a.token
        println(a.token)
        Assert.assertNotEquals(a.token, "")
    }
    @Test
    fun add_new_rec() = runTest{

        val t = Retrofit.Builder()
            .baseUrl("http://localhost:5000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ServerApi::class.java)

        val a = t.getRecords(tkn)
        for(f in a){
            println(f.toString())
        }
        Assert.assertNotEquals(a, "")
    }




}