package pl.sokolik.bartosz.transactionmanager.data.control

import io.vavr.collection.List
import io.vavr.collection.List.ofAll
import pl.sokolik.bartosz.transactionmanager.data.model.Customer
import java.util.stream.Collectors

class CustomerDataReader(private val customerDataPath: String) : BaseDataReader(), DataReader<Customer> {

    companion object {
        private const val CUSTOMER_ID: Int = 0
        private const val FIRST_NAME: Int = 1
        private const val LAST_NAME: Int = 2
        private const val LAST_LOGIN_BALANCE: Int = 3
    }

    override fun read(): List<Customer> {
        return ofAll(readLines(customerDataPath).map { line ->
            Customer(
                line[CUSTOMER_ID],
                line[FIRST_NAME],
                line[LAST_NAME],
                toBigDecimal(line[LAST_LOGIN_BALANCE])
            )
        }.collect(Collectors.toList()))
    }
}
