package app.cicilan.repositories.usecases

import app.cicilan.entities.ModalForm
import app.cicilan.repositories.contracts.CicilanRepository

/**
 * Created by: Muhammad Jafar
 * At: 04 Nov 23
 * Find me: 131.powerfull@gmail.com
 */

class InsertCicilanUseCase(
    private val repo: CicilanRepository,
) {

    suspend operator fun invoke(form: ModalForm) = repo.insert(form)
}
