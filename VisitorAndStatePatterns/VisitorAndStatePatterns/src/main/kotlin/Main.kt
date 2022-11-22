import token.Token
import token.Tokenizer
import visitors.CalcVisitor
import visitors.ParserVisitor
import visitors.PrintVisitor

fun main(args: Array<String>) {
    val str = readLine()!!

    val tokenizer = Tokenizer()
    val printVisitor = PrintVisitor()
    val list : List<Token> = tokenizer.tokenize(str)
    printVisitor.process(list)

    val parser = ParserVisitor()
    val parsedList = parser.process(list)
    printVisitor.process(parsedList)


    val calc = CalcVisitor()
    println(calc.process(parsedList))


}