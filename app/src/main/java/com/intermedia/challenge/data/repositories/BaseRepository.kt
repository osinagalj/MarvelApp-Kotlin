package com.intermedia.challenge.data.repositories

import com.intermedia.challenge.BuildConfig
import com.intermedia.challenge.data.models.NetResult
import retrofit2.Response
import java.security.MessageDigest

abstract class BaseRepository {

    protected val authParams = AuthParams(BuildConfig.PUBLIC_API_KEY, 1, generateHash())

    protected fun <T> handleResult(result: Response<T>): NetResult<T> {
        if (result.isSuccessful)
            result.body()
                ?.let { content -> return NetResult.Success(content) }
        return NetResult.Error()
    }

    protected class AuthParams(
        private val apiKey: String,
        private val ts: Int,
        private val hash: String
    ) {
        fun getMap(): HashMap<String, String> {
            val hashMap = HashMap<String, String>()
            hashMap["apikey"] = apiKey
            hashMap["ts"] = ts.toString()
            hashMap["hash"] = hash
            return hashMap
        }
    }

    private fun generateHash(): String = MessageDigest.getInstance("MD5")
        .digest(("1${BuildConfig.PRIVATE_API_KEY}${BuildConfig.PUBLIC_API_KEY}").toByteArray())
        .joinToString("") { "%02x".format(it) }
}