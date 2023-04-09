package database

import entity.Order
import entity.OrderWrapper
import entity.User
import rx.Observable

interface MarketInterface {

    fun addOrder(order : Order) : Observable<Boolean>
    fun addUser(user : User)  : Observable<Boolean>
    fun getOrdersByUserLogin(login : String) : Observable<OrderWrapper>

}