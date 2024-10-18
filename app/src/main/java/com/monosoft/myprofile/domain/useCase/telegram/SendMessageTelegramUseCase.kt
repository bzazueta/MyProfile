package com.monosoft.myprofile.domain.useCase.telegram

import com.monosoft.myprofile.domain.repository.TelegramRepository

class SendMessageTelegramUseCase (private val telegramRepository: TelegramRepository){

    suspend operator fun invoke(message: String) =
        telegramRepository.sendMessageTelegram(message)
}