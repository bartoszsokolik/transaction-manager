package pl.sokolik.bartosz.transactionmanager.data.control

import io.vavr.collection.List
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import pl.sokolik.bartosz.transactionmanager.data.model.AccountType
import java.util.stream.Collectors

class AccountTypeDataReader(private val accountTypeDataPath: String) : BaseDataReader(), DataReader<AccountType> {

    companion object {
        private const val TYPE: Int = 0
        private const val NAME: Int = 1
    }

    override fun read(): List<AccountType> {
        return List.ofAll(readLines(accountTypeDataPath).map { line -> AccountType(line[TYPE], line[NAME]) }
            .collect(Collectors.toList()))
    }
}
