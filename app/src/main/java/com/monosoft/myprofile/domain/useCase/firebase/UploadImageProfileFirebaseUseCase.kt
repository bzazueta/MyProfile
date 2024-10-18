package com.monosoft.myprofile.domain.useCase.firebase

import com.monosoft.myprofile.domain.repository.FirebaseRepository

class UploadImageProfileFirebaseUseCase (private val firebaseRepository: FirebaseRepository){

    suspend operator fun invoke(idUser :String,urlImage :String)=firebaseRepository.uploadImageProfileFirebase(idUser,urlImage)
}