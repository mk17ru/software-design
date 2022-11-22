package visitors

import token.*

class CalcVisitor: TokenVisitor<Int> {

    private val answer = 0

    private val stack = ArrayList<Int>()

    override fun visit(token: NumberToken) {
        stack.add(token.number)
    }

    override fun visit(token: Brace) {}

    override fun visit(token: Operation) {
        when(token) {
            is PLUS -> eval(Int::plus)
            is DIV -> eval(Int::div)
            is DIFF -> eval(Int::minus)
            is MUL -> eval(Int::times)
        }
    }

    private fun eval(func : (Int, Int) -> Int) {
        val x = stack.removeLast()
        val y = stack.removeLast()
        stack.add(func(y, x))
    }

    override fun process(tokens: List<Token>): Int {
        for (token in tokens) {
            token.accept(this)
        }
        return stack.removeLast()
    }

}