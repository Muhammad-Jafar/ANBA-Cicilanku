package app.cicilan.usecases

import app.cicilan.entities.Item
import kotlinx.coroutines.flow.Flow

interface CicilanViewerUseCase {
    fun getById(id: Int): Flow<Item?>
    fun countList(status: String): Flow<Int>
    fun getList(status: String): Flow<List<Item>>
}
