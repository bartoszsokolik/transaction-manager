package pl.sokolik.bartosz.transactionmanager.data.control

import io.vavr.collection.List
import mu.KotlinLogging
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import pl.sokolik.bartosz.transactionmanager.data.model.AccountType
import pl.sokolik.bartosz.transactionmanager.data.model.Customer
import pl.sokolik.bartosz.transactionmanager.data.model.Transaction
import pl.sokolik.bartosz.transactionmanager.transaction.boundary.TransactionRepository
import pl.sokolik.bartosz.transactionmanager.transaction.model.TransactionDocument

class DataLoader(
    private val accountTypeDataReader: DataReader<AccountType>,
    private val customerDataReader: DataReader<Customer>,
    private val transactionDataReader: DataReader<Transaction>,
    private val transactionRepository: TransactionRepository
) {

    companion object {
        private val logger = KotlinLogging.logger {}
    }

    @EventListener
    fun run(e: ApplicationReadyEvent) {
        transactionRepository.deleteAll()
        insertData()
    }

    private fun insertData() {
        logger.info { "Inserting data started" }
        val accountTypes = accountTypeDataReader.read()
        val customers = customerDataReader.read()
        val transactions = transactionDataReader.read().map { transaction -> toTransactionDocument(transaction, accountTypes, customers)}
        transactionRepository.saveAll(transactions)
        logger.info { "Inserting data finished" }
    }

    private fun toTransactionDocument(transaction: Transaction, accountTypes: List<AccountType>, customers: List<Customer>): TransactionDocument {
        return TransactionDocument(
            transaction.transactionId,
            transaction.transactionAmount,
            findAccountType(accountTypes, transaction.accountType),
            findCustomer(customers, transaction.customerId),
            transaction.transactionDate
        )
    }

    private fun findAccountType(accountTypes: List<AccountType>, type: String): AccountType? {
        return accountTypes.find { account -> account.type.equals(type) }.orNull
    }

    private fun findCustomer(customers: List<Customer>, customerId: String): Customer? {
        return customers.find { customer -> customer.id.equals(customerId) }.orNull
    }
}

