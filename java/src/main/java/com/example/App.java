package com.example;

import com.couchbase.client.java.*;
import com.couchbase.client.java.kv.*;
import com.couchbase.client.java.json.*;
import com.couchbase.client.java.query.*;

import java.time.Duration;

public class App {
  // Update these variables to point to your Couchbase Server instance and credentials.
  static String connectionString = "couchbase://192.168.106.130,192.168.106.129,192.168.106.128";
  static String username = "Administrator";
  static String password = "password";
  static String bucketName = "travel-sample";

  public static void main(String... args) {
    Cluster cluster = Cluster.connect(
        connectionString,
        ClusterOptions.clusterOptions(username, password).environment(env -> {
          // Customize client settings by calling methods on the "env" variable.
        })
    );

    // get a bucket reference
    Bucket bucket = cluster.bucket(bucketName);
    bucket.waitUntilReady(Duration.ofSeconds(10));

    // get a user-defined collection reference
    Scope scope = bucket.scope("tenant_agent_00");
    Collection collection = scope.collection("users");

    // Upsert Document
    MutationResult upsertResult = collection.upsert(
        "my-document",
        JsonObject.create().put("name", "mike")
    );

    // Get Document
    GetResult getResult = collection.get("my-document");
    String name = getResult.contentAsObject().getString("name");
    System.out.println(name); // name == "mike"

    // Call the query() method on the scope object and store the result.
    Scope inventoryScope = bucket.scope("inventory");
    QueryResult result = inventoryScope.query("SELECT * FROM airline WHERE id = 10;");

    // Return the result rows with the rowsAsObject() method and print to the terminal.
    System.out.println(result.rowsAsObject());

    cluster.disconnect();
  }
}
