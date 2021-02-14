package pl.sokolik.bartosz.transactionmanager.transaction.boundary

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import pl.sokolik.bartosz.transactionmanager.transaction.control.TransactionQuery
import pl.sokolik.bartosz.transactionmanager.transaction.control.TransactionService

@RestController
@RequestMapping("/api/transactions")
class TransactionController(
    private val transactionService: TransactionService
) {

    @GetMapping
    fun findAll(
        @RequestParam(name = "account_type", required = false) accountType: String?,
        @RequestParam(name = "customer_id", required = false) customerId: String?
    ): List<TransactionResponse> {
        return transactionService.findAllTransactions(TransactionQuery(accountType, customerId))
    }
}
