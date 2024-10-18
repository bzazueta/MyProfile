package com.monosoft.myprofile.di

import com.monosoft.ecommercebenja.data.dataSource.remote.AuthRemoteDataSource
import com.monosoft.ecommercebenja.data.dataSource.remote.AuthRemoteDataSourceImpl
import com.monosoft.myprofile.data.dataSource.remote.ApiCrudRemoteDataSource
import com.monosoft.myprofile.data.dataSource.remote.ApiCrudRemoteDataSourceImpl
import com.monosoft.myprofile.data.dataSource.remote.FirebaseRemoteDataSource
import com.monosoft.myprofile.data.dataSource.remote.FirebaseRemoteDataSourceImpl
import com.monosoft.myprofile.data.dataSource.remote.TelegramRemoteDataSource
import com.monosoft.myprofile.data.dataSource.remote.TelegramRemoteDataSourceImpl
import com.monosoft.myprofile.data.dataSource.remote.UserProfileDataSource
import com.monosoft.myprofile.data.dataSource.remote.UserProfileDataSourceImpl
import com.monosoft.myprofile.data.dataSource.remote.WorkExperienceRemoteDataSource
import com.monosoft.myprofile.data.dataSource.remote.WorkExperienceRemoteDataSourceImpl
import com.monosoft.myprofile.data.dataSource.remote.service.AuthService
import com.monosoft.myprofile.data.dataSource.remote.service.CrudApiService
import com.monosoft.myprofile.data.dataSource.remote.service.FirebaseService
import com.monosoft.myprofile.data.dataSource.remote.service.NotificationService
import com.monosoft.myprofile.data.dataSource.remote.service.TelegramService
import com.monosoft.myprofile.data.dataSource.remote.service.UserProfileService
import com.monosoft.myprofile.data.dataSource.remote.service.WorkExperienceService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataModule {

    @Provides
    fun provideAuthRemoteDataSource(authService: AuthService):AuthRemoteDataSource = AuthRemoteDataSourceImpl(authService)

    @Provides
    fun provideUserProfileDataSource(userProfileService: UserProfileService): UserProfileDataSource =  UserProfileDataSourceImpl(userProfileService)

    @Provides
    fun provideTelegramDataSource(telegramService: TelegramService): TelegramRemoteDataSource =  TelegramRemoteDataSourceImpl(telegramService)

    @Provides
    fun provideFirebaseDataSource(firebaseService: FirebaseService,notificationService: NotificationService): FirebaseRemoteDataSource =  FirebaseRemoteDataSourceImpl(firebaseService,notificationService)

    @Provides
    fun provideWorkExperienceDataSource(workExperienceService: WorkExperienceService): WorkExperienceRemoteDataSource =  WorkExperienceRemoteDataSourceImpl(workExperienceService)

    @Provides
    fun provideApiCrudDataSource(crudApiService: CrudApiService): ApiCrudRemoteDataSource =  ApiCrudRemoteDataSourceImpl(crudApiService)

}