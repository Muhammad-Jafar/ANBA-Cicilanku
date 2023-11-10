package app.cicilan.repository

import app.cicilan.db.CicilanDao
import app.cicilan.entities.ItemLogEntity
import app.cicilan.entities.State
import app.cicilan.repositories.CicilanLogRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

/**
 * Created by: Muhammad Jafar
 * At: 04 Nov 23
 * Find me: 131.powerfull@gmail.com
 */

class CicilanLogRepoImpl(
    private val dao: CicilanDao,
) : CicilanLogRepository {
    override fun getListLog(id: String): Flow<State<List<ItemLogEntity>>> = flow {
        dao.getListLogCicilan(id)
            .map {
                emit(State.Success(it))
            }
            .onStart {
                emit(State.Loading())
            }
            .onCompletion {
                emit(State.Error(it?.localizedMessage ?: "An expected error occured"))
            }
            .flowOn(Dispatchers.IO)
    }
}
