import controller.Controller
import database.Market
import database.Mongo
import io.reactivex.netty.protocol.http.server.HttpServer


fun main(args: Array<String>) {

    val controller = Controller(Market(Mongo()))

    HttpServer
        .newServer(8080)
        .start { req, resp ->
            val url = req.decodedPath.substring(1)

            val response = controller.handle(url, req.queryParameters)
            resp.writeString(response);
        }
        .awaitShutdown()
}

