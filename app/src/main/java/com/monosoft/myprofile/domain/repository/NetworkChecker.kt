package com.monosoft.myprofile.domain.repository

interface NetworkChecker {
    fun isNetworkAvailable(): Boolean
    suspend fun exitToInternet(): Boolean
}