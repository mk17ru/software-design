import org.junit.jupiter.api.Test
import token.*
import visitors.ParserVisitor
import kotlin.test.assertEquals

class ParserTest {

    @Test
    fun simpleAdd() {
        val a = NumberToken(5)
        val b = NumberToken(7)
        val oper = PLUS()
        assertEquals(ParserVisitor().process(listOf(a, oper, b)), listOf(a, b, oper))
    }

    @Test
    fun simpleDiff() {
        val a = NumberToken(5)
        val b = NumberToken(7)
        val oper = DIFF()
        assertEquals(ParserVisitor().process(listOf(a, oper, b)), listOf(a, b, oper))
    }

    @Test
    fun simpleMul() {
        val a = NumberToken(5)
        val b = NumberToken(7)
        val oper = MUL()
        assertEquals(ParserVisitor().process(listOf(a, oper, b)), listOf(a, b, oper))
    }

    @Test
    fun simpleDIV() {
        val a = NumberToken(5)
        val b = NumberToken(7)
        val oper = DIV()
        assertEquals(ParserVisitor().process(listOf(a, oper, b)), listOf(a, b, oper))
    }

    @Test
    fun multiOper() {
        val a = NumberToken(5)
        val b = NumberToken(7)
        val oper = DIV()
        val oper2 = PLUS()
        assertEquals(ParserVisitor().process(listOf(a, oper, b, oper2, a, oper, b)), listOf(a, b, oper, a, b, oper, oper2))
    }


}