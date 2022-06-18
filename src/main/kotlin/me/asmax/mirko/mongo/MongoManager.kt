package me.asmax.mirko.mongo

import com.kotlindiscord.kord.extensions.utils.env
import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import com.mongodb.client.MongoCollection
import org.bson.Document

object MongoManager {

    private lateinit var collection: MongoCollection<Document>

    private fun getMongoURL(): String {
        if (env("MONGO_URL") == null) {
            return "error"
        }
        return env("MONGO_URL")
    }

    private fun getDatabaseString(): String {
        if (env("MONGO_DATABASE") == null) {
            return "error"
        }
        return env("MONGO_DATABASE")
    }

    fun connect(collectionName: String) {
        val url = getMongoURL()

        val clientURI = MongoClientURI(url)
        val mongoClient = MongoClient(clientURI)

        val mongoDatabase = mongoClient.getDatabase(getDatabaseString())
        collection = mongoDatabase.getCollection(collectionName)

        println("Database connected successfully.")
    }

    fun getCollectionSize(): Long {
        return collection.countDocuments()
    }

    fun getDocument(search: Document): Document? {
        return collection.find(search).first()
    }
}