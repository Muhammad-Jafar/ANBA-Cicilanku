package app.cicilan.repositories.contracts

import app.cicilan.entities.Item
import app.cicilan.entities.ItemLog
import app.cicilan.entities.ModalForm
import kotlinx.coroutines.flow.Flow

/**
 * Created by: Muhammad Jafar
 * At: 04 Nov 23
 * Find me: 131.powerfull@gmail.com
 */

interface CicilanRepository {
    fun get(status: String): Flow<List<Item>>
    fun getById(id: Int): Flow<Item>
    fun count(status: String): Flow<Int>
    suspend fun insert(add: ModalForm)
    suspend fun update(update: ModalForm)
    suspend fun updateNominal(itemLog: ItemLog)
    suspend fun delete(id: Int)
}
