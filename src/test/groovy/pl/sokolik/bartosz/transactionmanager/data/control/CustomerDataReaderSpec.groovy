package pl.sokolik.bartosz.transactionmanager.data.control

import pl.sokolik.bartosz.transactionmanager.SampleData
import pl.sokolik.bartosz.transactionmanager.data.model.Customer
import spock.lang.Specification

import static io.vavr.collection.List.of

class CustomerDataReaderSpec extends Specification implements SampleData {

    DataReader<Customer> reader = new CustomerDataReader("/test-data/test-customers.csv")

    def "should read customers"() {
        given:
            def expectedResult = of(createCustomer(['id': '1',
                                                    'firstName': 'Andrzej',
                                                    'lastName': 'Kowalski',
                                                    'lastLoginBalance': new BigDecimal("10000")]))

        when:
            def result = reader.read()

        then:
            result == expectedResult
    }
}
