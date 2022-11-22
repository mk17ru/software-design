import org.junit.jupiter.api.Test
import token.*
import visitors.CalcVisitor
import kotlin.test.assertEquals

class CalcTest {

    @Test
    fun simpleAdd() {
        assertEquals(CalcVisitor().process(listOf(NumberToken(5), NumberToken(7), PLUS())), 12)
    }

    @Test
    fun simpleDiff() {
        assertEquals(CalcVisitor().process(listOf(NumberToken(5), NumberToken(7), DIFF())), -2)
    }

    @Test
    fun simpleDiv() {
        assertEquals(CalcVisitor().process(listOf(NumberToken(10), NumberToken(2), DIV())), 5)
    }

    @Test
    fun simpleMul() {
        assertEquals(CalcVisitor().process(listOf(NumberToken(2), NumberToken(22), MUL())), 44)
    }

    @Test
    fun testManyOperation() {
        assertEquals(
            CalcVisitor().process(
                listOf(
                    NumberToken(30), NumberToken(2),
                    PLUS(), NumberToken(8), DIV(), NumberToken(1), DIFF()
                )
            ), 3
        )
    }
}