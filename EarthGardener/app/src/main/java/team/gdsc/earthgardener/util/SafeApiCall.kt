package team.gdsc.earthgardener.util

import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import team.gdsc.earthgardener.domain.signin.model.ErrorBody
import timber.log.Timber
import java.io.IOException

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> T) : ResultWrapper<T>{
    return withContext(dispatcher){
        try{
            ResultWrapper.Success(apiCall.invoke())
        }catch(throwable: Throwable){
            when(throwable){
                is IOException -> ResultWrapper.NetworkError
                is HttpException -> {
                    val code = throwable.code()
                    Timber.d(code.toString())
                    val errorBody = Gson().fromJson(
                        throwable.response()?.errorBody()?.string(),
                        ErrorBody::class.java
                    )
                    val message = errorBody.message
                    ResultWrapper.GenericError(code, message)
                }
                else -> ResultWrapper.GenericError(null, null)
            }
        }
    }
}