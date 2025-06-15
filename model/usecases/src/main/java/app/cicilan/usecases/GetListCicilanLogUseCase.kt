package app.cicilan.usecases

import app.cicilan.repositories.contracts.CicilanLogRepository

/**
 * Created by: Muhammad Jafar
 * At: 04 Nov 23
 * Find me: 131.powerfull@gmail.com
 */

class GetListCicilanLogUseCase(
    private val repo: CicilanLogRepository,
) {
    operator fun invoke(idLog: Int) = repo.getListLog(idLog)
}
