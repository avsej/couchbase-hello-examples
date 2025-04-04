package com.example

import com.couchbase.client.kotlin.Cluster
import com.couchbase.client.kotlin.query.execute
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.seconds

fun main() {
    val cluster = Cluster.connect(
        connectionString = "couchbase://192.168.106.130,192.168.106.129,192.168.106.128",
        username = "Administrator", // Replace with credentials
        password = "password", // of a database user account.
    )

    try {
        runBlocking {
            val collection = cluster
                .bucket("travel-sample")
                .waitUntilReady(10.seconds)
                .defaultCollection()

            // Execute a N1QL query
            val queryResult = cluster
                .query("select * from `travel-sample` limit 3")
                .execute()
            queryResult.rows.forEach { println(it) }
            println(queryResult.metadata)

            // Get a document from the K/V service
            val getResult = collection.get("airline_10")
            println(getResult)
            println(getResult.contentAs<Map<String, Any?>>())
        }
    } finally {
        runBlocking { cluster.disconnect() }
    }
}
