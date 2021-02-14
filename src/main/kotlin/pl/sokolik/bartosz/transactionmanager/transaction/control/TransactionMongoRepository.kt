package pl.sokolik.bartosz.transactionmanager.transaction.control

import org.springframework.data.mongodb.repository.MongoRepository
import pl.sokolik.bartosz.transactionmanager.transaction.model.TransactionDocument

interface TransactionMongoRepository : MongoRepository<TransactionDocument, String> {
}
