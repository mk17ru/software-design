package visitors

import token.Brace
import token.NumberToken
import token.Operation
import token.Token

interface TokenVisitor<R> {
    fun visit(token: NumberToken)

    fun visit(token: Brace)

    fun visit(token: Operation)

    fun process(tokens : List<Token>) : R
}