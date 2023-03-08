package com.example.snailpasswordmanager.domain.usecase.cryptography

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.snailpasswordmanager.domain.crypto.AES.AESUtil
import com.example.snailpasswordmanager.domain.model.UserEntity
import java.util.*

data class Encode(
    private var userEntityAuth: UserEntity
) {

    @RequiresApi(Build.VERSION_CODES.O)
    suspend operator fun invoke(text:String) : String {
        val masterpass = Base64.getDecoder().decode(userEntityAuth.password)

        //val login = AESUtil.decrypt("ow8nOksC84lKh/ACg4CZdQ==".toByteArray(), authInfo.hash2, "tttttttttttttttt".toByteArray())
        val split = text.split(":")
        return String(AESUtil.decrypt(Base64.getEncoder().encode(split[0].toByteArray()), masterpass, Base64.getEncoder().encode(split[1].toByteArray())))
    }
}