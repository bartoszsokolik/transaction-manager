package pl.sokolik.bartosz.transactionmanager.transaction.control

import org.springframework.stereotype.Service
import pl.sokolik.bartosz.transactionmanager.transaction.boundary.TransactionRepository
import pl.sokolik.bartosz.transactionmanager.transaction.boundary.TransactionResponse
import pl.sokolik.bartosz.transactionmanager.transaction.model.TransactionDocument

@Service
class TransactionService(private val transactionRepository: TransactionRepository) {

    fun findAllTransactions(transactionSearchQuery: TransactionQuery): List<TransactionResponse> {
        return transactionRepository.findAll(transactionSearchQuery)
            .map { transaction -> toTransactionResponse(transaction) }
    }

    private fun toTransactionResponse(transaction: TransactionDocument): TransactionResponse {
        return TransactionResponse(
            transaction.transactionDate,
            transaction.transactionId,
            transaction.transactionAmount,
            transaction.accountType?.name,
            transaction.customer?.firstName,
            transaction.customer?.lastName
        )
    }

}
