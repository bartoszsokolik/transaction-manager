package pl.sokolik.bartosz.transactionmanager.transaction.boundary

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal
import java.time.LocalDateTime

data class TransactionResponse(
    @JsonProperty("Data transakcji")
    val transactionDate: LocalDateTime,

    @JsonProperty("Identyfikator transakcji")
    val transactionId: String,

    @JsonProperty("Kwota transakcji")
    val transactionAmount: BigDecimal,

    @JsonProperty("Rodzaj rachunku")
    val accountType: String?,

    @JsonProperty("Imię zlecającego")
    val customerName: String?,

    @JsonProperty("Nazwisko zlecającego")
    val customerLastname: String?
)
