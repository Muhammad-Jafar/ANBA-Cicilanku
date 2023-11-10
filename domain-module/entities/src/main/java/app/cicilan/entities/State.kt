package app.cicilan.entities

/**
 * Created by: Inisialku
 * At: 04 Nov 23
 * Find me: 131.powerfull@gmail.com
 */

/*sealed class State<T>(val data: T? = null, val message: String? = null) {
    class Loading<T>(data: T? = null) : State<T>(data)
    class Success<T>(data: T) : State<T>(data)
    class Error<T>(cause: String, data: T? = null) : State<T>(data, cause)
}*/

sealed class State<out T> {
    class Loading<T> : State<T>()
    class Empty<T> : State<T>()
    class Success<T>(val data: T) : State<T>()
    class Error<T>(val cause: String? = null) : State<T>()
}
