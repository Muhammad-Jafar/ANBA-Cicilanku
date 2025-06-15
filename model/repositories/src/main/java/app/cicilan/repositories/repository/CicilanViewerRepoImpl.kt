package app.cicilan.repositories.repository

import app.cicilan.entities.Item
import app.cicilan.entities.State
import app.cicilan.local.db.CicilanDao
import app.cicilan.repositories.contracts.CicilanViewerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

/**
 * Created by: Muhammad Jafar
 * At: 05 Nov 23
 * Find me: 131.powerfull@gmail.com
 */

class CicilanViewerRepoImpl(
    private val dao: CicilanDao,
) : CicilanViewerRepository {
    override fun getById(id: Int): Flow<State<Item>> = flow {
        dao.getCicilanById(id)
            .map {
                if (it != null) emit(State.Success(it))
            }
            .onStart {
                emit(State.Loading())
            }
            .onCompletion {
                emit(State.Error(it?.localizedMessage ?: "An expected error occured"))
            }
            .flowOn(Dispatchers.IO)
    }

    override fun countList(status: String): Flow<State<Int>> = flow {
        dao.countCicilan(status)
            .map {
                if (it != null) emit(State.Success(it))
                emit(State.Success(0))
            }
            .onStart {
                emit(State.Loading())
            }
            .onCompletion {
                emit(State.Error(it?.localizedMessage ?: "An expected error occured"))
            }.flowOn(Dispatchers.IO)
    }

    override fun getList(status: String): Flow<State<List<Item>>> = flow {
        dao.getListCicilan(status)
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
