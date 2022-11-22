package token

import visitors.TokenVisitor

sealed class Operation(val designation : String) : Token {
    override fun accept(visitor: TokenVisitor<*>) {
        visitor.visit(this)
    }

    override fun toStr(): String {
        return designation;
    }
}

class PLUS : Operation("PLUS")
class DIV : Operation("DIV")
class MUL : Operation("MUL")
class DIFF : Operation("DIFF")
