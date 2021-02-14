package pl.sokolik.bartosz.transactionmanager.transaction.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.FieldType
import pl.sokolik.bartosz.transactionmanager.data.model.AccountType
import pl.sokolik.bartosz.transactionmanager.data.model.Customer
import java.math.BigDecimal
import java.time.LocalDateTime

@Document(collection = "transactions")
data class TransactionDocument(

    @Id
    val id: String?,
    val transactionId: String,

    @Field(targetType = FieldType.DECIMAL128)
    val transactionAmount: BigDecimal,
    val accountType: AccountType?,
    val customer: Customer?,
    val transactionDate: LocalDateTime,

    ) {

    constructor(
        transactionId: String,
        transactionAmount: BigDecimal,
        accountType: AccountType?,
        customer: Customer?,
        transactionDate: LocalDateTime,
    ) : this(null, transactionId, transactionAmount, accountType, customer, transactionDate)
}
