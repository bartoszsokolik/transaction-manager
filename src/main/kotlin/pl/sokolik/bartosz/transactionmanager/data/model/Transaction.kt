package pl.sokolik.bartosz.transactionmanager.data.model

import java.math.BigDecimal
import java.time.LocalDateTime

data class Transaction(

    val transactionId: String,
    val transactionAmount: BigDecimal,
    val accountType: String,
    val customerId: String,
    val transactionDate: LocalDateTime,
)
