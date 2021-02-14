package pl.sokolik.bartosz.transactionmanager

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class TransactionManagerApplication

fun main(args: Array<String>) {
	runApplication<TransactionManagerApplication>(*args)
}
