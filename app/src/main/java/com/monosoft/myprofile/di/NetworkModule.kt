package com.monosoft.myprofile.di

import com.google.gson.Gson
import com.monosoft.myprofile.data.dataSource.remote.service.AuthService
import com.monosoft.myprofile.data.dataSource.remote.service.CrudApiService
import com.monosoft.myprofile.data.dataSource.remote.service.FirebaseService
import com.monosoft.myprofile.data.dataSource.remote.service.NotificationService
import com.monosoft.myprofile.data.dataSource.remote.service.TelegramService
import com.monosoft.myprofile.data.dataSource.remote.service.UserProfileService
import com.monosoft.myprofile.data.dataSource.remote.service.WorkExperienceService
import com.monosoft.myprofile.domain.models.WorkExperience
import com.optic.ecommerceappmvvm.core.Config
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.nio.file.attribute.AclEntry.Builder
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class DefaultRetrofit
    @Provides
    @Singleton
    @DefaultRetrofit
    fun provideRetrofit():Retrofit{
        return Retrofit
            .Builder()
            .baseUrl(Config.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class CustomRetrofit
    @Provides
    @Singleton
    @CustomRetrofit
    fun provideRetrofitUrl(): Retrofit { // POSTMAN - THUNDER CLIENT - RETROFIT
        return Retrofit
            .Builder()
            .baseUrl("https://monitodevs.com/updateFirebaseV1Php/")
            //.client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    @Provides
    @Singleton
    fun provideAuthService(@NetworkModule.DefaultRetrofit retrofit: Retrofit):AuthService{
        return retrofit.create(AuthService::class.java)
    }

    @Provides
    @Singleton
    fun provideUserProfileService(@NetworkModule.DefaultRetrofit retrofit: Retrofit):UserProfileService{
        return retrofit.create(UserProfileService::class.java)
    }

    @Provides
    @Singleton
    fun provideTelegramService(@NetworkModule.DefaultRetrofit retrofit: Retrofit):TelegramService{
        return retrofit.create(TelegramService::class.java)
    }

    @Provides
    @Singleton
    fun provideFirebaseService(@NetworkModule.DefaultRetrofit retrofit: Retrofit): FirebaseService {
        return retrofit.create(FirebaseService::class.java)
    }

    @Provides
    @Singleton
    fun provideFirebaseUrlService(@NetworkModule.CustomRetrofit retrofit: Retrofit): NotificationService {
        return retrofit.create(NotificationService::class.java)
    }

    @Provides
    @Singleton
    fun provideWorkExperienceService(@NetworkModule.DefaultRetrofit retrofit: Retrofit): WorkExperienceService {
        return retrofit.create(WorkExperienceService::class.java)
    }

    @Provides
    @Singleton
    fun provideCrudApiServiceService(@NetworkModule.DefaultRetrofit retrofit: Retrofit): CrudApiService {
        return retrofit.create(CrudApiService::class.java)
    }
}