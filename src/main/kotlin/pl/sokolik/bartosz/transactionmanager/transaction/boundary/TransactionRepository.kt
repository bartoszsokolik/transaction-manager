package pl.sokolik.bartosz.transactionmanager.transaction.boundary

import pl.sokolik.bartosz.transactionmanager.transaction.control.TransactionQuery
import pl.sokolik.bartosz.transactionmanager.transaction.model.TransactionDocument

interface TransactionRepository {

    fun findAll(searchQuery: TransactionQuery): List<TransactionDocument>

    fun saveAll(transactions: Iterable<TransactionDocument>): List<TransactionDocument>

    fun deleteAll()
}
