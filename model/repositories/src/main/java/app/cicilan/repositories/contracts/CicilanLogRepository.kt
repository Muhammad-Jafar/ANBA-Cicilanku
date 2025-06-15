package app.cicilan.repositories.contracts

import app.cicilan.entities.ItemLog
import kotlinx.coroutines.flow.Flow

/**
 * Created by: Muhammad Jafar
 * At: 04 Nov 23
 * Find me: 131.powerfull@gmail.com
 */

interface CicilanLogRepository {
    fun getLog(id: Int): Flow<List<ItemLog>>
}
