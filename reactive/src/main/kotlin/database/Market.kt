package database

import com.mongodb.client.model.Filters
import entity.CURRENCY
import entity.Order
import entity.OrderWrapper
import entity.User
import org.bson.Document
import rx.Observable
import service.Convert

class Market(val mongo: Mongo) : MarketInterface{

    private val converter = Convert()

    override fun addOrder(order: Order): Observable<Boolean> {
        return mongo.getOrders().find(Filters.eq("id", order.id)).toObservable()
            .singleOrDefault(null)
            .flatMap {
                doc ->
                when(doc) {
                    null -> mongo.getOrders().insertOne(
                        Document(
                            mapOf(
                                "id" to order.id,
                                "name" to order.name,
                                "priceDollar" to order.priceDollar
                            )
                        )
                    ).isEmpty.map{ !it }

                    else -> Observable.just(false)
                }
            }
    }

    override fun addUser(user: User): Observable<Boolean> {
        return mongo.getUsers().find(Filters.eq("login", user.login)).toObservable()
            .singleOrDefault(null)
            .flatMap {
                    doc ->
                when(doc) {
                    null -> mongo.getUsers().insertOne(
                        Document(
                            mapOf(
                                "login" to user.login,
                                "currency" to user.currency.toString(),
                            )
                        )
                    ).isEmpty.map{ !it }
                    else -> Observable.just(false)
                }
            }
    }

    override fun getOrdersByUserLogin(login : String): Observable<OrderWrapper> {
        return mongo.getUsers().find(Filters.eq("login", login)).toObservable().
                map{
                   CURRENCY.valueOf(it.getString("currency"))
                }.
                flatMap {currency ->
                    mongo.getOrders().find().toObservable().map{
                        OrderWrapper(
                            it.getInteger("id"),
                            it.getString("name"),
                            converter.convert(it.getDouble("priceDollar"), currency),
                            currency.toString()
                        )
                    }
                }
    }


}