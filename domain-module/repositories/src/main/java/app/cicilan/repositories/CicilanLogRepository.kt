package app.cicilan.repositories

import app.cicilan.entities.ItemLogEntity
import app.cicilan.entities.State
import kotlinx.coroutines.flow.Flow

/**
 * Created by: Muhammad Jafar
 * At: 04 Nov 23
 * Find me: 131.powerfull@gmail.com
 */

interface CicilanLogRepository {
    fun getListLog(id: String): Flow<State<List<ItemLogEntity>>>
}
