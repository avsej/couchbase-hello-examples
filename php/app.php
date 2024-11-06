<?php

require_once 'couchbase/Couchbase/autoload.php';

use Couchbase\ClusterOptions;
use Couchbase\Cluster;

// Update these credentials for your Local instance!
$connectionString = "couchbase://192.168.106.130,192.168.106.129,192.168.106.128";
$options = new ClusterOptions();

$options->credentials("Administrator", "password");
$cluster = new Cluster($connectionString, $options);

// get a bucket reference
$bucket = $cluster->bucket("travel-sample");

// get a user-defined collection reference
$scope = $bucket->scope("tenant_agent_00");
$collection = $scope->collection("users");

$upsertResult = $collection->upsert("my-document-key", ["name" => "Ted", "Age" => 31]);

$getResult = $collection->get("my-document-key");

print_r($getResult->content());

$inventoryScope = $bucket->scope("inventory");
$queryResult = $inventoryScope->query("SELECT * FROM airline WHERE id = 10");

// Print result data to the terminal.
foreach ($queryResult->rows() as $row) {
    print_r($row);
}
