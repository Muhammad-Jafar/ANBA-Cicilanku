package app.cicilan.repositories.contracts

import app.cicilan.entities.Item
import app.cicilan.entities.State
import kotlinx.coroutines.flow.Flow

/**
 * Created by: Muhammad Jafar
 * At: 04 Nov 23
 * Find me: 131.powerfull@gmail.com
 */

interface CicilanViewerRepository {
    fun getById(id: Int): Flow<State<Item>>
    fun countList(status: String): Flow<State<Int>>
    fun getList(status: String): Flow<State<List<Item>>>
}
