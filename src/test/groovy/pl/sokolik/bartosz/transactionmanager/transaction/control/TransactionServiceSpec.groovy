package pl.sokolik.bartosz.transactionmanager.transaction.control

import pl.sokolik.bartosz.transactionmanager.SampleData
import spock.lang.Specification

class TransactionServiceSpec extends Specification implements SampleData {

    def transactionRepository = new InMemoryTransactionRepository()

    def transactionService = new TransactionService(transactionRepository)

    def setup() {
        transactionRepository.saveAll([sampleTransactionDocument, secondSampleTransactionDocument, thirdSampleTransactionDocument])
    }

    def "should find transactions with given criteria"() {
        given:
            def query = new TransactionQuery(accountType, customerId)

        when:
            def result = transactionService.findAllTransactions(query)

        then:
            result == expectedResult

        where:
            accountType | customerId | expectedResult
            null        | null       | [secondSampleTransactionDocumentResponse, thirdSampleTransactionDocumentResponse, sampleTransactionDocumentResponse]
            "ALL"       | null       | [secondSampleTransactionDocumentResponse, thirdSampleTransactionDocumentResponse, sampleTransactionDocumentResponse]
            null        | "ALL"      | [secondSampleTransactionDocumentResponse, thirdSampleTransactionDocumentResponse, sampleTransactionDocumentResponse]
            "ALL"       | "ALL"      | [secondSampleTransactionDocumentResponse, thirdSampleTransactionDocumentResponse, sampleTransactionDocumentResponse]
            "1"         | "1"        | [sampleTransactionDocumentResponse]
            "1,2"       | "1"        | [thirdSampleTransactionDocumentResponse, sampleTransactionDocumentResponse]
            "2"         | "1,2"      | [secondSampleTransactionDocumentResponse, thirdSampleTransactionDocumentResponse]
    }
}
