package app.cicilan.usecases

import app.cicilan.repositories.contracts.CicilanRepository

/**
 * Created by: Muhammad Jafar
 * At: 04 Nov 23
 * Find me: 131.powerfull@gmail.com
 */

class InsertCicilanUseCase(
    private val repo: CicilanRepository,
) {

   /* suspend operator fun invoke() = flow {
        try {
            emit(State.Loading)
            val test = repo.insert()
        } catch (e: Throwable) {
        } catch (e: Error) {
        }
    }*/
}
