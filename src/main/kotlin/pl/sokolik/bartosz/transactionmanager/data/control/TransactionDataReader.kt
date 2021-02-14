package pl.sokolik.bartosz.transactionmanager.data.control

import io.vavr.collection.List
import io.vavr.control.Try
import pl.sokolik.bartosz.transactionmanager.data.model.Transaction
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.stream.Collectors

class TransactionDataReader(private val transactionDataPath: String) : BaseDataReader(), DataReader<Transaction> {

    companion object {
        @JvmField
        val FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

        private const val TRANSACTION_ID: Int = 0
        private const val TRANSACTION_AMOUNT: Int = 1
        private const val ACCOUNT_TYPE: Int = 2
        private const val CUSTOMER_ID: Int = 3
        private const val TRANSACTION_DATE: Int = 4
    }

    override fun read(): List<Transaction> {
        return List.ofAll(readLines(transactionDataPath).map { line -> toTransaction(line) }
            .collect(Collectors.toList()))
    }

    private fun toTransaction(line: kotlin.collections.List<String>): Transaction {
        return Transaction(
            line[TRANSACTION_ID],
            toBigDecimal(line[TRANSACTION_AMOUNT]),
            line[ACCOUNT_TYPE],
            line[CUSTOMER_ID],
            toLocalDateTime(line[TRANSACTION_DATE])
        )
    }

    private fun toLocalDateTime(date: String): LocalDateTime {
        return Try.of { LocalDateTime.parse(date, FORMATTER) }.getOrElseGet { LocalDateTime.now() }
    }
}
