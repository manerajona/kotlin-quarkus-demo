package com.github.manerajona

import com.github.manerajona.service.MaskService
import io.smallrye.mutiny.Uni
import io.smallrye.mutiny.infrastructure.Infrastructure
import jakarta.ws.rs.BadRequestException
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam

@Path("/mask")
class MaskResource(val labSeqService: MaskService) {

    @GET
    @Path("/{cardNumber}")
    fun getMaskedCardNumber(@PathParam("cardNumber") cardNumber: String): Uni<String> {
        val isValidCard = cardNumber.toLongOrNull() != null && (cardNumber.length in listOf(13, 14, 15, 16, 19))
        return if (isValidCard) {
            labSeqService.maskCardNumber(cardNumber, 'X')
                .runSubscriptionOn(Infrastructure.getDefaultExecutor())
        } else {
            Uni.createFrom().emitter {
                it.fail(BadRequestException("Invalid credit card."))
            }
        }
    }
}