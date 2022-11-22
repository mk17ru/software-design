package visitors

import token.*

class ParserVisitor : TokenVisitor<List<Token>> {

    private val result: ArrayList<Token> = ArrayList()

    private val stack = ArrayDeque<Token>()

    override fun visit(token: NumberToken) {
        result.add(token)
    }

    override fun visit(token: Brace) {
        if (token.isOpen) {
            stack.add(token)
        } else {
            while (!(stack.last() is Brace && (stack.last() as Brace).isOpen) && !stack.isEmpty()) {
                result.add(stack.removeLast())
            }
            stack.removeLast()
        }
    }

    private fun isNextLevel(firstOper: Operation, secondOper: Operation): Boolean {
        return firstOper is PLUS || firstOper is DIFF && secondOper is MUL || secondOper is DIV
    }

    override fun visit(token: Operation) {
        while (!stack.isEmpty() && stack.last() is Operation && isNextLevel(
                token,
                stack.last() as Operation
            )
        ) {
            result.add(stack.removeLast())
        }
        stack.add(token)
    }

    private fun endProcess() {
        while (!stack.isEmpty()) {
            val token = stack.removeLast()
            if (token !is Brace) {
                result.add(token)
            }
        }
    }

    override fun process(tokens: List<Token>): List<Token> {
        for (cur in tokens) {
            cur.accept(this)
        }
        endProcess()
        return result
    }

}