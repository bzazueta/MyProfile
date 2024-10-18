package com.monosoft.myprofile.domain.useCase.firebase

import com.monosoft.myprofile.domain.repository.FirebaseRepository

class SendNotificationPushFirebaseUseCase (private val firebaseRepository: FirebaseRepository){

    suspend operator fun invoke(message: String,tknFrb :String,title :String) =
        firebaseRepository.sendNotificationPushFirebase(message,tknFrb,title)
}