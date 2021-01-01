package com.johnturkson.tripwatch.server.database.data

import com.johnturkson.awstools.dynamodb.data.StringAttributeValue
import kotlinx.serialization.Serializable

@Serializable
data class UserEmailData(val email: StringAttributeValue) {
    constructor(email: String) : this(StringAttributeValue(email))
}
