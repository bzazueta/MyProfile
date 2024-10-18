package com.monosoft.myprofile.domain.repository

import com.monosoft.myprofile.domain.models.LoginModel
import com.monosoft.myprofile.domain.utils.Resource

interface AuthRepository {

    suspend fun login(email:String,pass:String,marca:String,version:String): Resource<LoginModel>
}