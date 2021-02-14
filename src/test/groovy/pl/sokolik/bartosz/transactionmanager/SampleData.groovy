package pl.sokolik.bartosz.transactionmanager

import pl.sokolik.bartosz.transactionmanager.data.model.AccountType
import pl.sokolik.bartosz.transactionmanager.data.model.Customer
import pl.sokolik.bartosz.transactionmanager.data.model.Transaction
import pl.sokolik.bartosz.transactionmanager.transaction.boundary.TransactionResponse
import pl.sokolik.bartosz.transactionmanager.transaction.model.TransactionDocument
import spock.lang.Shared

import java.time.LocalDateTime

trait SampleData {

    Customer sampleCustomer = new Customer("1", "Jan", "Kowalski", new BigDecimal("5000"))
    Customer secondSampleCustomer = new Customer("2", "Adam", "Nowak", new BigDecimal("3000"))

    AccountType sampleAccountType = new AccountType("1", "checking account")
    AccountType secondSampleAccountType = new AccountType("2", "saving account")

    Transaction sampleTransaction = new Transaction("1", new BigDecimal("100"), "1", "1", LocalDateTime.of(2021, 1, 31, 0, 0, 0))

    TransactionDocument sampleTransactionDocument = new TransactionDocument(
            UUID.randomUUID().toString(),
            "1",
            new BigDecimal("100"),
            sampleAccountType,
            sampleCustomer,
            LocalDateTime.of(2021, 1, 30, 0, 0, 0)
    )

    TransactionDocument secondSampleTransactionDocument = createTransactionDocument(['transactionAmount': new BigDecimal("50"),
                                                                                     'accountType'      : secondSampleAccountType,
                                                                                     'customer'         : secondSampleCustomer])

    TransactionDocument thirdSampleTransactionDocument = createTransactionDocument(['transactionAmount': new BigDecimal("75"),
                                                                                    'accountType'      : secondSampleAccountType])

    AccountType createAccountType(Map<String, String> params = [:]) {
        return new AccountType(
                params['id'] ?: sampleAccountType.type,
                params['name'] ?: sampleAccountType.name
        )
    }

    Customer createCustomer(Map<String, Object> params = [:]) {
        return new Customer(
                params['id'] as String ?: sampleCustomer.id,
                params['firstName'] as String ?: sampleCustomer.firstName,
                params['lastName'] as String ?: sampleCustomer.lastName,
                params['lastLoginBalance'] as BigDecimal ?: sampleCustomer.lastLoginBalance
        )
    }

    Transaction createTransaction(Map<String, Object> params = [:]) {
        return new Transaction(
                params['transactionId'] as String ?: sampleTransaction.transactionId,
                params['transactionAmount'] as BigDecimal ?: sampleTransaction.transactionAmount,
                params['accountType'] as String ?: sampleTransaction.accountType,
                params['customerId'] as String ?: sampleTransaction.customerId,
                params['transactionDate'] as LocalDateTime ?: sampleTransaction.transactionDate
        )
    }

    TransactionDocument createTransactionDocument(Map<String, Object> params = [:]) {
        return new TransactionDocument(
                params['id'] as String ?: UUID.randomUUID().toString(),
                params['transactionId'] as String ?: sampleTransactionDocument.transactionId,
                params['transactionAmount'] as BigDecimal ?: sampleTransactionDocument.transactionAmount,
                params['accountType'] as AccountType ?: sampleAccountType,
                params['customer'] as Customer ?: sampleCustomer,
                params['transactionDate'] as LocalDateTime ?: sampleTransactionDocument.transactionDate
        )
    }

    @Shared
    TransactionResponse sampleTransactionDocumentResponse = new TransactionResponse(
            sampleTransactionDocument.transactionDate,
            sampleTransactionDocument.transactionId,
            sampleTransactionDocument.transactionAmount,
            sampleAccountType.name,
            sampleCustomer.firstName,
            sampleCustomer.lastName
    )

    @Shared
    TransactionResponse secondSampleTransactionDocumentResponse = new TransactionResponse(
            secondSampleTransactionDocument.transactionDate,
            secondSampleTransactionDocument.transactionId,
            secondSampleTransactionDocument.transactionAmount,
            secondSampleAccountType.name,
            secondSampleCustomer.firstName,
            secondSampleCustomer.lastName
    )

    @Shared
    TransactionResponse thirdSampleTransactionDocumentResponse = new TransactionResponse(
            thirdSampleTransactionDocument.transactionDate,
            thirdSampleTransactionDocument.transactionId,
            thirdSampleTransactionDocument.transactionAmount,
            secondSampleAccountType.name,
            sampleCustomer.firstName,
            sampleCustomer.lastName
    )

}
