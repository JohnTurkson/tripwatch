package com.johnturkson.tripwatch.server.configuration

import com.johnturkson.awstools.dynamodb.requesthandler.DynamoDBConfiguration

object DatabaseConfiguration {
    val instance: DynamoDBConfiguration = DynamoDBConfiguration("us-west-2")
}
