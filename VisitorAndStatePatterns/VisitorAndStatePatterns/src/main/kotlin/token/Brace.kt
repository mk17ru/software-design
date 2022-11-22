package token

import visitors.TokenVisitor

class Brace(val isOpen : Boolean) : Token {
    override fun accept(visitor: TokenVisitor<*>) {
        visitor.visit(this)
    }

    override fun toStr(): String {
        return if (isOpen) "(" else ")"
    }
}