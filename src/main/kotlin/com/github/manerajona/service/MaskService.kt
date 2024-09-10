package com.github.manerajona.service

import io.quarkus.cache.CacheResult
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped
import kotlin.math.max


@ApplicationScoped
class MaskService {

    /**
     * Returns a masked credit card number whose last 4 characters are shown while the rest is masked using the
     * {@code maskCharacter} parameter of type {@link Character}.
     * <br>
     *
     * @param creditCardNumber the credit card number to be masked.
     * @param maskCharacter    this {@code Character} masks the digits of the card.
     * @return the masked credit card number.
     */
    @CacheResult(cacheName = "maskedCardNumber")
    fun maskCardNumber(creditCardNumber: String, maskCharacter: Char): Uni<String> {
        val numDigitsToMask = max(0.0, (creditCardNumber.length - 4).toDouble()).toInt()
        val maskedDigits = maskCharacter.toString().repeat(numDigitsToMask)
        val lastDigits = creditCardNumber.substring(creditCardNumber.length - 4)

        return Uni.createFrom().item(maskedDigits + lastDigits)
    }

}