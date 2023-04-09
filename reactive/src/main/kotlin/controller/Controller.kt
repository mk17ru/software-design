package controller

import database.Market
import entity.CURRENCY
import entity.Order
import entity.User
import rx.Observable
import java.lang.IllegalStateException

class Controller(val market : Market) {

    fun handle(url : String, body: Map<String, List<String>>) : Observable<String> {
        return when(url) {
            "addOrder" -> return market.addOrder(Order(
                id = body["id"]!![0].toInt(),
                name = body["name"]!![0],
                priceDollar = body["priceDollar"]!![0].toDouble()
            )).map { it.toString() }
            "addUser" -> return market.addUser(
                User(
                    login = body["login"]!![0],
                    currency = CURRENCY.valueOf(body["currency"]!![0])
                )
            ).map { it.toString() }
            "getOrders" -> {
                val res = market.getOrdersByUserLogin(body["login"]!![0]).map{it.toString()}
                return res
            }
            else -> {
                throw IllegalStateException("No such endpoint!")
            }
        }
    }
}