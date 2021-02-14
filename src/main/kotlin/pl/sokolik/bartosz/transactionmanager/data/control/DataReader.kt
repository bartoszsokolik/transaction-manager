package pl.sokolik.bartosz.transactionmanager.data.control

import io.vavr.collection.List

interface DataReader<T> {

    fun read(): List<T>
}
