package com.monosoft.myprofile.domain.utils

import android.util.Log
import com.monosoft.myprofile.domain.models.ErrorResponseLogin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

/**
 * recibimos la respuesta del servidor para revisar si la respuesta fue exitosa o hubo una falla
 * regresamos la respuesta segun sea el caso success o failure
 */

object ResponseToRequest {

    fun <T> send(result: Response<T>): Resource<T> {
        return try {
            if (result.isSuccessful) { // 201
                Resource.Success(result.body()!!)
            }
            else {
                //utilizamos la libreria moshi para parsear el json a un modelos de datos
                val errorResponse: ErrorResponseLogin? = ConvertErrorBody.convert(result.errorBody())
                Resource.Failure(errorResponse?.result?.get(0)?.msg.toString() ?: "Error desconido")
            }
        }
        catch (e: HttpException) {
            Log.d("ResponseToRequest", "Message: ${e.message()}")
            Log.d("ResponseToRequest", "Message: ${e.cause}")
            e.printStackTrace()
            Resource.Failure(e.message ?: "Error desconido en la peticion Http")
        }
        catch (e: IOException) {
            Log.d("ResponseToRequest", "Message: ${e}")
            Log.d("ResponseToRequest", "Message: ${e.cause}")
            e.printStackTrace()
            Resource.Failure("Verifica tu conexion a internet")
        }
        catch (e: Exception) {
            Log.d("ResponseToRequest", "Message: ${e}")
            Log.d("ResponseToRequest", "Message: ${e.cause}")
            e.printStackTrace()
            Resource.Failure(e.message ?: "Error desconido")
        }
    }

}