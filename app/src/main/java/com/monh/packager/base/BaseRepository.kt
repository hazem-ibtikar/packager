package com.monh.packager.base

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import com.monh.packager.R
import com.monh.packager.data.locale.SharedPreferencesUtils
import com.monh.packager.utils.ConnectivityUtils
import com.monh.packager.utils.network.ApplicationException
import com.monh.packager.utils.network.BaseResponse
import com.monh.packager.utils.network.ErrorType
import com.monh.packager.utils.network.Result
import com.orhanobut.logger.Logger.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject


open class BaseRepository() {

    @Inject
    lateinit var connectivityUtils: ConnectivityUtils

    @Inject
    lateinit var sharedPreferencesUtils: SharedPreferencesUtils

    private val gSon = Gson()
    private val noInternetError = Result.Error(
        ApplicationException(
            type = ErrorType.Network.NoInternetConnection,
            errorMessageRes = R.string.error_no_internet_connection
        )
    )

    val unexpectedError = Result.Error(ApplicationException(type = ErrorType.Network.Unexpected))
    suspend fun <T : Any> safeApiCall(tag:String = "", call: suspend () -> Response<T>): Result<T> {
        return withContext(Dispatchers.IO) {
            return@withContext try {
                // check internet connection
                if (connectivityUtils.isNetworkConnected.not()) return@withContext noInternetError

                // make api call
                val response = call()

                // check response and convert to result
                return@withContext handleResult(response, tag)

            } catch (error: Throwable) {
                Timber.e(error)
                unexpectedError(error)
            }
        }
    }

    private fun unexpectedError(error: Throwable): Result.Error {
        return Result.Error(
            ApplicationException(
                throwable = error,
                type = ErrorType.Network.Unexpected
            )
        )
    }
    private fun <T : Any> handleResult(response: Response<T>, tag:String = ""): Result<T> {
        return when (response.code()) {
            in 1..399 -> Result.Success(response.body()!!)
            401 -> Result.Error(
                ApplicationException(
                    type = ErrorType.Network.Unauthorized,
                    errorMessage = getErrorMessage(response),
                    tag = tag/*,
                    extra = getErrorExtra(response)*/
                )
            )
            404 -> Result.Error(
                ApplicationException(
                    type = ErrorType.Network.ResourceNotFound,
                    errorMessage = getErrorMessage(response),
                    tag = tag/*,
                    extra = getErrorExtra(response)*/
                )
            )
            else -> Result.Error(
                ApplicationException(
                    /*extra = getErrorExtra(response),*/
                    type = ErrorType.Network.Unexpected,
                    errorMessage = getErrorMessage(response),
                    tag = tag

                )
            )
        }
    }

    private fun <T> getErrorExtra(response: Response<T>): JsonObject? {
        //Create json object from string
        return try {
            val jsonObj = JsonParser().parse(response.errorBody()?.string()).asJsonObject
            jsonObj["error"].asJsonObject["extra"].asJsonObject
        } catch (e: Exception){
            null
        }
    }

    private fun <T> getErrorMessage(response: Response<T>): String? {
        return gSon.fromJson<BaseResponse<*>>(
            response.errorBody()?.string(),
            object : TypeToken<BaseResponse<*>>() {}.type
        ).error?.message
    }
}