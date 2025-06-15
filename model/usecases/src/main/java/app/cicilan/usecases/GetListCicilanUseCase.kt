package app.cicilan.usecases

import app.cicilan.repositories.contracts.CicilanRepository

/**
 * Created by: Muhammad Jafar
 * At: 04 Nov 23
 * Find me: 131.powerfull@gmail.com
 */

class GetListCicilanUseCase(
    private val repo: CicilanRepository,
) {
    operator fun invoke(status: String) = repo.get(status)
}
