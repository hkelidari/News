package com.hk.news.core.data.api

import retrofit2.Response
import javax.inject.Inject

class NetworkCallHandler @Inject constructor(
    private val connectionManager: ConnectionManager
) {

    suspend fun <T> call(networkCall: suspend () -> Response<T>): Result<T> =
        try {
            if (!connectionManager.isConnected())
                Result.Error(Exceptions.NoInternetConnectionException())
            else {
                val apiResponse = networkCall()
                if (apiResponse.isSuccessful) {
                    val body = apiResponse.body()
                    if (body != null) {
                        Result.Success(body)
                    } else {
                        Result.Error(Exceptions.GeneralRemoteException())
                    }

                } else {
                    Result.Error(Exceptions.GeneralRemoteException())
                }
            }

        } catch (t: Throwable) {
            Result.Error(Exceptions.GeneralRemoteException())
        }
}