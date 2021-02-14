package pl.sokolik.bartosz.transactionmanager.data.control

import pl.sokolik.bartosz.transactionmanager.SampleData
import pl.sokolik.bartosz.transactionmanager.data.model.AccountType
import spock.lang.Specification

import static io.vavr.collection.List.of

class AccountTypeDataReaderSpec extends Specification implements SampleData {

    DataReader<AccountType> reader = new AccountTypeDataReader("/test-data/test-accountypes.csv")

    def "should read account types"() {
        given:
            def expectedResult = of(createAccountType(['accountType': '1', 'name': 'test account']))

        when:
            def result = reader.read()

        then:
            result == expectedResult
    }
}
