package app.cicilan.repositories

import app.cicilan.entities.ItemLogEntity
import app.cicilan.entities.ModalForm

/**
 * Created by: Muhammad Jafar
 * At: 04 Nov 23
 * Find me: 131.powerfull@gmail.com
 */

interface CicilanRepository {
    suspend fun insert(add: ModalForm)
    suspend fun update(update: ModalForm)
    suspend fun updateNominal(itemLog: ItemLogEntity)
    suspend fun delete(id: String)
}
