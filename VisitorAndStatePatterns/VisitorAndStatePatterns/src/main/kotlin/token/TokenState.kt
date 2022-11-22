package token

class OperationState(private val result : ArrayList<Token>) : State<Char, Token> {

    override fun handle(value: Char): State<Char, Token> {
        when(value) {
            ' ' -> return this
            '('-> {
                result.add(Brace(true))
                return this
            }
            ')'-> {
                result.add(Brace(false))
                return this
            }
            '-', '–'-> {
                result.add(DIFF())
                return this
            }
            '+'-> {
                result.add(PLUS())
                return this
            }
            '/'-> {
                result.add(DIV())
                return this
            }
            '*'-> {
                result.add(MUL())
                return this
            }
            in '0'..'9' -> {
                return NumberState(result).handle(value)
            }
            else -> throw IllegalArgumentException("No such transfer!")
        }
    }

    override fun getResult(): List<Token> {
        return result
    }
}

class NumberState(private val result : ArrayList<Token>) : State<Char, Token> {

    private var number = 0

    private var hasSomething = false

    override fun handle(value: Char) : State<Char, Token>  {
        when(value) {
            in '0'..'9' -> {
                hasSomething = true
                number = number * 10 + (value - '0')
                return this
            }
            else -> {
                hasSomething = false
                result.add(NumberToken(number))
                return OperationState(result).handle(value)
            }
        }
    }

    override fun getResult(): List<Token> {
        if (hasSomething) {
            result.add(NumberToken(number))
        }
        return result
    }
}