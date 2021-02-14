package pl.sokolik.bartosz.transactionmanager.data.control

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import pl.sokolik.bartosz.transactionmanager.transaction.boundary.TransactionRepository

@Configuration
class DataLoaderConfiguration {

    @Bean
    @Profile("!test")
    fun dataLoader(@Value("\${initial.data.path.accounttype}") accountTypeDataPath: String,
                   @Value("\${initial.data.path.customer}") customerDataPath: String,
                   @Value("\${initial.data.path.transaction}") transactionDataPath: String,
                   transactionRepository: TransactionRepository): DataLoader {

        val accountTypeReader = AccountTypeDataReader(accountTypeDataPath)
        val customerReader = CustomerDataReader(customerDataPath)
        val transactionReader = TransactionDataReader(transactionDataPath)

        return DataLoader(accountTypeReader, customerReader, transactionReader, transactionRepository)
    }
}
