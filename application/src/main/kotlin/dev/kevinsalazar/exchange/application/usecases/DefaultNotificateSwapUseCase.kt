package dev.kevinsalazar.exchange.application.usecases

import dev.kevinsalazar.exchange.domain.events.SuccessfulSwapEvent
import dev.kevinsalazar.exchange.domain.ports.driving.NotificateSwapUseCase

class DefaultNotificateSwapUseCase : NotificateSwapUseCase {

    override suspend fun execute(event: SuccessfulSwapEvent) {
        println("DefaultNotificateSwapUseCase: OK")
    }
}