package pl.sokolik.bartosz.transactionmanager.data.model

import java.math.BigDecimal

data class Customer(
        val id: String,
        val firstName: String,
        val lastName: String,
        val lastLoginBalance: BigDecimal
)
