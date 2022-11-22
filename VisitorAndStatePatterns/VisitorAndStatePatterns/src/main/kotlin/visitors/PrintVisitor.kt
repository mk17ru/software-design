package visitors

import token.Brace
import token.NumberToken
import token.Operation
import token.Token

class PrintVisitor() : TokenVisitor<Unit> {
    override fun visit(token: NumberToken) {
        print(token.toStr())
    }

    override fun visit(token: Brace) {
        print(token.toStr())
    }

    override fun visit(token: Operation) {
        print(token.toStr())
    }

    override fun process(tokens: List<Token>) {
        for (token in tokens) {
            token.accept(this)
            print(" ")
        }
        println()
    }


}