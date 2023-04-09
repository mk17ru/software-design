package database

import com.mongodb.rx.client.MongoClient
import com.mongodb.rx.client.MongoClients
import com.mongodb.rx.client.MongoCollection
import org.bson.Document

class Mongo {

    private val db = createMongoClient().getDatabase("test")

    fun getOrders(): MongoCollection<Document> {
        return db.getCollection("orders")
    }

    fun getUsers(): MongoCollection<Document> {
        return db.getCollection("users")
    }

    private fun createMongoClient(): MongoClient {
        return MongoClients.create("mongodb://localhost:27017")
    }

}