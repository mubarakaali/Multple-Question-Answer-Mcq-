package fivesol.networklibrary.data.remote.datasource

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import fivesol.networklibrary.domain.models.Resource
import fivesol.networklibrary.data.utils.NetworkUtils.getErrorMessage
import fivesol.networklibrary.data.utils.NetworkUtils.getNetworkErrorMessage

abstract class BaseRemoteDataSource {


    protected suspend fun <T> getApiResponse(call :suspend ()->Response<T>): Resource<T> {
        try {
                val response = call()
                if (response.isSuccessful){
                    val  body = response.body()
                    if (body!=null){
                        return Resource.Valid(body)
                    }
                }
                return error("${response.code()} ${response.message()}")

        }catch (e:Exception){
            return error(e.message?: e.toString())
        }
    }

    private fun <T>error(message:String): Resource<T> {
        Log.e("jejeje ", "BaseRemoteDataSource error:.... $message ")
        return Resource.Invalid(message = "Network call has failed due to $message")
    }


    /**
     * This method  will safely invoke the remote api call and return a flow of [Resource]s
     *
     */
    fun <T : Any> safeApiCall(
        // TODO: Move this to a retrofit adapter
        call: suspend () -> Response<T>
    ): Flow<Resource<T>> =
        flow {
            emit(Resource.Loading())
            try {
                emit(safeApiResult(call.invoke()))
            } catch (e: Exception) {
                Log.e("jejeje ", "BaseRemoteDataSource safeApiCall error:.... ${e.message} ")
                emit(Resource.Invalid<T>(getNetworkErrorMessage(e)))
            }
        }


    private fun <T> safeApiResult(response: Response<T>): Resource<T> =
        if (response.isSuccessful) Resource.Valid(response.body()!!)
        else Resource.Invalid(getErrorMessage(response.code()))

}