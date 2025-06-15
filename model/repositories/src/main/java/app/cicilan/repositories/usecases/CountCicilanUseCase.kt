package app.cicilan.repositories.usecases

import app.cicilan.repositories.contracts.CicilanRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by: Muhammad Jafar
 * At: 04 Nov 23
 * Find me: 131.powerfull@gmail.com
 */

class CountCicilanUseCase(
    private val repo: CicilanRepository,
) {
    operator fun invoke(status: String): Flow<Int> = repo.count(status)
}
