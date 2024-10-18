package com.monosoft.myprofile.domain.useCase.network_checked

import com.monosoft.myprofile.domain.repository.NetworkChecker
import javax.inject.Inject

class NetworkCheckedUseCase @Inject constructor(
    private val networkChecker: NetworkChecker
) {
   suspend fun execute(): Boolean {
        return if (networkChecker.isNetworkAvailable()) {
            // Realizar la operación cuando hay conexión
            true
        } else {
            // Manejar el caso cuando no hay conexión
            false
        }
    }
   suspend fun execute2(): Boolean {
        return if (networkChecker.exitToInternet()) {
            // Realizar la operación cuando hay conexión
            true
        } else {
            // Manejar el caso cuando no hay conexión
            false
        }
    }
}