package app.cicilan.usecases

import app.cicilan.repositories.CicilanViewerRepository

/**
 * Created by: Muhammad Jafar
 * At: 04 Nov 23
 * Find me: 131.powerfull@gmail.com
 */

class CountCicilanUseCase(
    private val useCase: CicilanViewerRepository,
) {
    operator fun invoke(status: String) = useCase.countList(status)
}
