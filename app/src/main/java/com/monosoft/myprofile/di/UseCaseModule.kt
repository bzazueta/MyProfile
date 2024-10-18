package com.monosoft.myprofile.di

import com.monosoft.myprofile.domain.repository.AuthRepository
import com.monosoft.myprofile.domain.repository.CrudApiRepository
import com.monosoft.myprofile.domain.repository.FirebaseRepository
import com.monosoft.myprofile.domain.repository.ProfileRepository
import com.monosoft.myprofile.domain.repository.TelegramRepository
import com.monosoft.myprofile.domain.repository.WorkExperienceRepository
import com.monosoft.myprofile.domain.useCase.AuthUseCase
import com.monosoft.myprofile.domain.useCase.LoginUseCase
import com.monosoft.myprofile.domain.useCase.crud_api.AddNameApiUseCase
import com.monosoft.myprofile.domain.useCase.crud_api.CrudApiUseCase
import com.monosoft.myprofile.domain.useCase.crud_api.DeleteNameApiUseCase
import com.monosoft.myprofile.domain.useCase.crud_api.EditNameApiUseCase
import com.monosoft.myprofile.domain.useCase.crud_api.GetAllApiUseCase
import com.monosoft.myprofile.domain.useCase.firebase.FirebaseUseCase
import com.monosoft.myprofile.domain.useCase.firebase.SendNotificationPushFirebaseUseCase
import com.monosoft.myprofile.domain.useCase.firebase.UploadImageProfileFirebaseUseCase
import com.monosoft.myprofile.domain.useCase.profile.ProfileUseCase
import com.monosoft.myprofile.domain.useCase.profile.UserProfileUseCase
import com.monosoft.myprofile.domain.useCase.telegram.SendMessageTelegramUseCase
import com.monosoft.myprofile.domain.useCase.telegram.TelegramUseCase
import com.monosoft.myprofile.domain.useCase.work_experience.GetWorkExperienceByIdUseCase
import com.monosoft.myprofile.domain.useCase.work_experience.WorkExperienceUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideUseCase(authRepository: AuthRepository) = AuthUseCase(
        login = LoginUseCase(authRepository)
    )

    @Provides
    fun provideProfileUseCase(userProfileRepository: ProfileRepository) = ProfileUseCase(
       userProfileUseCase = UserProfileUseCase(userProfileRepository)
    )

    @Provides
    fun provideTelegramUseCase(telegramRepository: TelegramRepository) = TelegramUseCase(
        SendMessageTelegramUseCase(telegramRepository)
    )

    @Provides
    fun provideFirebaseUseCase(firebaseRepository: FirebaseRepository) = FirebaseUseCase(
        SendNotificationPushFirebaseUseCase(firebaseRepository),
        UploadImageProfileFirebaseUseCase(firebaseRepository)
    )

    @Provides
    fun provideworkExperienceUseCase(workExperienceRepository: WorkExperienceRepository) = WorkExperienceUseCase(
        GetWorkExperienceByIdUseCase(workExperienceRepository)
    )

    @Provides
    fun provideCrudApiUseCase(crudApiRepository: CrudApiRepository) = CrudApiUseCase(
        AddNameApiUseCase(crudApiRepository),
        GetAllApiUseCase(crudApiRepository),
        DeleteNameApiUseCase(crudApiRepository),
        EditNameApiUseCase(crudApiRepository)
    )
}