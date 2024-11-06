package com.example

import com.couchbase.client.scala.Cluster
import com.couchbase.client.scala.ClusterOptions
import com.couchbase.client.scala.env.ClusterEnvironment

object App {
  def main(args: Array[String]): Unit = {
    // Update this to your cluster
    val username = "Administrator"
    val password = "password"
    val bucketName = "travel-sample"

    val env = ClusterEnvironment
      .builder
      .build
      .get

    val cluster = Cluster
      .connect(
        // For a secure cluster connection, use `couchbases://<your-cluster-ip>` instead.
        "couchbase://192.168.106.130,192.168.106.129,192.168.106.128",
        ClusterOptions
          .create(username, password)
          .environment(env)
      )
      .get
  }
}
