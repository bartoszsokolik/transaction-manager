package pl.sokolik.bartosz.transactionmanager.data.control

import pl.sokolik.bartosz.transactionmanager.SampleData
import pl.sokolik.bartosz.transactionmanager.data.model.Transaction
import spock.lang.Specification

import static io.vavr.collection.List.of

class TransactionDataReaderSpec extends Specification implements SampleData {

    DataReader<Transaction> reader = new TransactionDataReader("/test-data/test-transactions.csv")

    def "should read transactions"() {
        given:
            def expectedResult = of(createTransaction(['customerId': '1']), createTransaction(['customerId': '2']))

        when:
            def result = reader.read()

        then:
            result == expectedResult
    }

}
