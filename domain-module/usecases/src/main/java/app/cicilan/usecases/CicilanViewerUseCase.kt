package app.cicilan.usecases

import app.cicilan.entities.ItemEntity
import kotlinx.coroutines.flow.Flow

interface CicilanViewerUseCase {
    fun getById(id: String): Flow<ItemEntity?>
    fun countList(status: String): Flow<Int>
    fun getList(status: String): Flow<List<ItemEntity>>
}
