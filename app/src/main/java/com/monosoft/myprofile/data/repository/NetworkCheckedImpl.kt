package com.monosoft.myprofile.data.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.compose.ui.text.resolveDefaults
import com.monosoft.myprofile.domain.repository.NetworkChecker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.HttpURLConnection
import java.net.InetAddress
import java.net.URL

class NetworkCheckedImpl (private val context: Context) : NetworkChecker {

    override fun isNetworkAvailable(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {true}
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {true}
                else -> false
            }
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }
    }

    override suspend fun exitToInternet(): Boolean {

        try {


                val address: InetAddress = InetAddress.getByName("www.google.com")
                address.isReachable(1500)


            return  true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }


}