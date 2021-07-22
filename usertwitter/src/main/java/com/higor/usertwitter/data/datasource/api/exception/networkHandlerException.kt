package com.higor.usertwitter.data.datasource.api.exception

import com.higor.usertwitter.domain.exception.TwitterUserException
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import java.net.UnknownHostException

internal fun networkHandlerExceptions(ex: Exception): Exception {
    return when (ex) {
        is TwitterUserException -> TwitterUserException()
        is IOException -> NetworkConnectionException()
        is UnknownHostException -> NetworkConnectionException()
        is HttpException -> apiErrorFromCodeException(ex.code())
        else -> GenericNetworkException()
    }
}

private fun apiErrorFromCodeException(code: Int): Exception {

    //make custom Error
    return NetworkConnectionException()
}
