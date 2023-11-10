package app.cicilan.usecases

import app.cicilan.repositories.CicilanLogRepository

/**
 * Created by: Muhammad Jafar
 * At: 04 Nov 23
 * Find me: 131.powerfull@gmail.com
 */

class GetListCicilanLogUseCase(
    private val repo: CicilanLogRepository,
) {
    operator fun invoke(idLog: String) = repo.getListLog(idLog)
}
