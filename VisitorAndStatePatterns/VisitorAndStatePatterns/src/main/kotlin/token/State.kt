package token

interface State<T, R> {
    fun handle(value : T) : State<T, R>

    fun getResult() : List<R>
}