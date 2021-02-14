package pl.sokolik.bartosz.transactionmanager.transaction.boundary

import io.restassured.common.mapper.TypeRef
import org.springframework.beans.factory.annotation.Autowired
import pl.sokolik.bartosz.transactionmanager.IntegrationSpec
import pl.sokolik.bartosz.transactionmanager.SampleData

import static org.springframework.http.HttpStatus.OK

class TransactionControllerIT extends IntegrationSpec implements SampleData {

    @Autowired
    TransactionRepository transactionRepository

    def setup() {
        transactionRepository.saveAll([sampleTransactionDocument, secondSampleTransactionDocument, thirdSampleTransactionDocument])
    }

    def cleanup() {
        transactionRepository.deleteAll()
    }

    def "should find transactions with given criteria"() {
        when:
            def request = given()

            request = accountType != null ? request.queryParam("account_type", accountType) : request
            request = customerId != null ? request.queryParam("customer_id", customerId) : request

            def response = request.get("/api/transactions")

        then:
            def result = response.then()
                    .statusCode(OK.value())
                    .extract()
                    .response()
                    .as(new TypeRef<List<TransactionResponse>>() {})

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
