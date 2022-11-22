package token

class Tokenizer {

    fun tokenize(str : String) : List<Token> {
        var state : State<Char, Token> = OperationState(ArrayList())
        for (i in 0 until str.length) {
            state = state.handle(str[i])
        }
        return state.getResult()
    }


}