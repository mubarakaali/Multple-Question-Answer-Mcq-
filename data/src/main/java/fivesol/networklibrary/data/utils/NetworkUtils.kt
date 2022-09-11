package fivesol.networklibrary.data.utils

import android.content.Context
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.nio.charset.StandardCharsets

 object NetworkUtils {

    private const val genericNetworkError = "An error occurred getting data from server."

    fun getNetworkErrorMessage(e: Exception) = when (e) {
        is IOException -> "Network not available."
        is SocketTimeoutException -> "Request timed out. Try again."
        is UnknownHostException -> "Unable to connect to server."
        else -> e.message!!
    }

    fun getErrorMessage(httpCode: Int) = when (httpCode) {
        // User unauthorised error
        401 -> "You have been unauthorized."
        // Time out error
        408 -> "Request timed out. Try again."
        // Internal server error
        500 -> "A server error occurred."
        // Any other error executing the API
        else -> genericNetworkError
    }
    fun getJsonFromAssets(context: Context, fileName: String): String {
        val jsonString: String
        jsonString = try {
            val `is` = context.assets.open(fileName)
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            String(buffer, StandardCharsets.UTF_8)
        } catch (e: IOException) {
            e.printStackTrace()
            return ""
        }
        return jsonString
    }


}