package token

import visitors.TokenVisitor

interface Token {
    fun accept(visitor : TokenVisitor<*>)

    fun toStr() : String
}