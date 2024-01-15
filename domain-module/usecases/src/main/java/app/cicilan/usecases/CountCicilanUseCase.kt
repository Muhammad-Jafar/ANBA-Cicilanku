package app.cicilan.usecases

import app.cicilan.entities.State
import app.cicilan.repositories.CicilanViewerRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by: Muhammad Jafar
 * At: 04 Nov 23
 * Find me: 131.powerfull@gmail.com
 */

class CountCicilanUseCase(
    private val useCase: CicilanViewerRepository,
) {
    operator fun invoke(status: String): Flow<State<Int>> = useCase.countList(status)
}
