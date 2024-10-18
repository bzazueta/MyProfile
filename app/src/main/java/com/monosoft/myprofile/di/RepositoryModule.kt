package com.monosoft.myprofile.di

import com.monosoft.ecommercebenja.data.dataSource.remote.AuthRemoteDataSource
import com.monosoft.myprofile.data.dataSource.remote.ApiCrudRemoteDataSource
import com.monosoft.myprofile.data.dataSource.remote.ApiCrudRemoteDataSourceImpl
import com.monosoft.myprofile.data.dataSource.remote.FirebaseRemoteDataSource
import com.monosoft.myprofile.data.dataSource.remote.FirebaseRemoteDataSourceImpl
import com.monosoft.myprofile.data.dataSource.remote.TelegramRemoteDataSource
import com.monosoft.myprofile.data.dataSource.remote.UserProfileDataSource
import com.monosoft.myprofile.data.dataSource.remote.WorkExperienceRemoteDataSource
import com.monosoft.myprofile.data.repository.ApiCrudRepositoryImpl
import com.monosoft.myprofile.data.repository.AuthRepositoryImpl
import com.monosoft.myprofile.data.repository.FirebaseRepositoryImpl
import com.monosoft.myprofile.data.repository.TelegramRepositoryImpl
import com.monosoft.myprofile.data.repository.UserProfileRepositoryImpl
import com.monosoft.myprofile.data.repository.WorkExperienceRepositoryImpl
import com.monosoft.myprofile.domain.repository.AuthRepository
import com.monosoft.myprofile.domain.repository.CrudApiRepository
import com.monosoft.myprofile.domain.repository.FirebaseRepository
import com.monosoft.myprofile.domain.repository.ProfileRepository
import com.monosoft.myprofile.domain.repository.TelegramRepository
import com.monosoft.myprofile.domain.repository.WorkExperienceRepository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideAuthRepository(authRemoteDataSource: AuthRemoteDataSource):AuthRepository = AuthRepositoryImpl(authRemoteDataSource)

    @Provides
    fun provideProfileRepository(userProfileDataSource: UserProfileDataSource): ProfileRepository = UserProfileRepositoryImpl(userProfileDataSource)

    @Provides
    fun provideTelegramRepository(telegramRemoteDataSource: TelegramRemoteDataSource): TelegramRepository = TelegramRepositoryImpl(telegramRemoteDataSource)

    @Provides
    fun provideFirebaseRepository(firebaseRemoteDataSource: FirebaseRemoteDataSource): FirebaseRepository = FirebaseRepositoryImpl(firebaseRemoteDataSource)

    @Provides
    fun provideWorkExperienceRepository(workExperienceRemoteDataSource: WorkExperienceRemoteDataSource): WorkExperienceRepository = WorkExperienceRepositoryImpl(workExperienceRemoteDataSource)

    @Provides
    fun provideCrudApiRepository(apiCrudRemoteDataSource: ApiCrudRemoteDataSource): CrudApiRepository = ApiCrudRepositoryImpl(apiCrudRemoteDataSource)

}