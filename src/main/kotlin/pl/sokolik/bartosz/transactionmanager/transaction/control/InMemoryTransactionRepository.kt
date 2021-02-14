package pl.sokolik.bartosz.transactionmanager.transaction.control

import pl.sokolik.bartosz.transactionmanager.transaction.boundary.TransactionRepository
import pl.sokolik.bartosz.transactionmanager.transaction.model.TransactionDocument
import java.util.*

class InMemoryTransactionRepository : TransactionRepository {

    @JvmField
    val REPOSITORY: MutableMap<String, TransactionDocument> = mutableMapOf()

    override fun findAll(searchQuery: TransactionQuery): List<TransactionDocument> {
        return REPOSITORY.values.filter { p -> accountTypePredicate(p, searchQuery) && customerPredicate(p, searchQuery) }
            .sortedBy { i -> i.transactionAmount }
    }

    override fun saveAll(transactions: Iterable<TransactionDocument>): List<TransactionDocument> {
        val persistedTransactions = transactions.map { t -> t.copy(id = UUID.randomUUID().toString()) }.toList()
        persistedTransactions.forEach { a -> a.id?.let { REPOSITORY[it] = a } }
        return persistedTransactions
    }

    override fun deleteAll() {
        REPOSITORY.clear()
    }

    private fun accountTypePredicate(transaction: TransactionDocument, searchQuery: TransactionQuery): Boolean {
        val accountType = searchQuery.accountType
        return if (accountType == null || accountType == "ALL") true else accountType.split(",").contains(transaction.accountType?.type)
    }

    private fun customerPredicate(transaction: TransactionDocument, searchQuery: TransactionQuery): Boolean {
        val customerId = searchQuery.customerId
        return if (customerId == null || customerId == "ALL") true else customerId.split(",").contains(transaction.customer?.id)
    }
}
