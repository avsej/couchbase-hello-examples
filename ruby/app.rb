# frozen_string_literal: true

require "couchbase"

# Update these credentials for your Local instance!
options = Couchbase::Cluster::ClusterOptions.new
options.authenticate("Administrator", "password")
cluster = Couchbase::Cluster.connect("couchbase://192.168.106.130,192.168.106.129,192.168.106.128", options)

# get a bucket reference
bucket = cluster.bucket("travel-sample")

# get a user-defined collection reference
scope = bucket.scope("tenant_agent_00")
collection = scope.collection("users")

# Upsert Document
upsert_result = collection.upsert(
  "my-document-key",
  {
    "name" => "Ted",
    "age" => 31,
  }
)
p cas: upsert_result.cas
#=> {:cas=>223322674373654}

# Get Document
get_result = collection.get("my-document-key")
p cas: get_result.cas,
  name: get_result.content["name"]
#=> {:cas=>223322674373654, :name=>"Ted"}

inventory_scope = bucket.scope("inventory")
result = inventory_scope.query("SELECT * FROM airline WHERE id = 10;")
result.rows.each do |row|
  p row
end

cluster.disconnect
