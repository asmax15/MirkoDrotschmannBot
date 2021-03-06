package me.asmax.mirko.facts

import org.bson.Document
import me.asmax.mirko.mongo.MongoManager

object FactManager {

    fun getRandomFact(): String {
        val maxFacts = MongoManager.getCollectionSize()
        val factNumber = (1..maxFacts).random().toString()

        val searchDocument = Document(factNumber, factNumber)
        val data = MongoManager.getDocument(searchDocument)

        if (data != null) {
            return data.getString("Fact")
        }
        return "No fact found"
    }
}