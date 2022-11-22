import org.junit.jupiter.api.Test
import token.Token
import token.Tokenizer
import visitors.CalcVisitor
import visitors.ParserVisitor
import visitors.PrintVisitor
import kotlin.test.assertEquals

class VisitorTest {

    @Test
    fun simpleTest() {
        val str = "(30 + 2) / 8"
        assertEquals(CalcVisitor().process(ParserVisitor().process(Tokenizer().tokenize(str))), 4)
    }

    @Test
    fun multiTest() {
        val str = "(23 + 10) * 5 – 3 * (32 + 5) * (10 – 4 * 5) + 8 / 2"
        assertEquals(CalcVisitor().process(ParserVisitor().process(Tokenizer().tokenize(str))), 1279)
    }


}