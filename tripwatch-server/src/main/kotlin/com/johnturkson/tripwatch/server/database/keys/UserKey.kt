package com.johnturkson.tripwatch.server.database.keys

import com.johnturkson.awstools.dynamodb.data.StringAttributeValue
import kotlinx.serialization.Serializable

@Serializable
data class UserKey(val id: StringAttributeValue) {
    constructor(id: String) : this(StringAttributeValue(id))
}
