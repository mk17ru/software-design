package token

import visitors.TokenVisitor

class NumberToken(val number : Int) : Token {

    override fun accept(visitor: TokenVisitor<*>) {
        visitor.visit(this)
    }

    override fun toStr(): String {
        return number.toString()
    }
}