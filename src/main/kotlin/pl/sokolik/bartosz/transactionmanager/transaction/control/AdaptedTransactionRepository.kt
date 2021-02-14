package pl.sokolik.bartosz.transactionmanager.transaction.control

import org.apache.commons.lang3.StringUtils
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Repository
import pl.sokolik.bartosz.transactionmanager.transaction.boundary.TransactionRepository
import pl.sokolik.bartosz.transactionmanager.transaction.model.TransactionDocument

@Repository
class AdaptedTransactionRepository(
    private val transactionMongoRepository: TransactionMongoRepository,
    private val mongoTemplate: MongoTemplate) : TransactionRepository {

    companion object {
        const val ALL_VALUES = "ALL"
    }

    override fun findAll(searchQuery: TransactionQuery): List<TransactionDocument> {
        val query = Query().with(Sort.by(Sort.Direction.ASC, "transactionAmount"))
        addAccountTypeCriteria(searchQuery, query)
        addCustomerIdCriteria(searchQuery, query)
        return mongoTemplate.find(query, TransactionDocument::class.java)
    }

    override fun saveAll(transactions: Iterable<TransactionDocument>): List<TransactionDocument> {
        return transactionMongoRepository.saveAll(transactions)
    }

    override fun deleteAll() {
        transactionMongoRepository.deleteAll()
    }

    private fun addAccountTypeCriteria(transactionSearchQuery: TransactionQuery, query: Query) {
        transactionSearchQuery.accountType
            ?.takeIf { accountType -> isNotBlankOrAll(accountType) }
            ?.split(",")
            ?.also { accountTypes -> query.addCriteria(Criteria.where("accountType.type").`in`(accountTypes)) }
    }

    private fun addCustomerIdCriteria(transactionSearchQuery: TransactionQuery, query: Query) {
        transactionSearchQuery.customerId
            ?.takeIf { customerId -> isNotBlankOrAll(customerId) }
            ?.split(",")
            ?.also { customerIds -> query.addCriteria(Criteria.where("customer.id").`in`(customerIds)) }
    }

    private fun isNotBlankOrAll(queryValue: String?): Boolean {
        return ALL_VALUES != queryValue && !StringUtils.isBlank(queryValue)
    }

}
