package com.johnturkson.tripwatch.server.database.data

import com.johnturkson.awstools.dynamodb.data.BooleanAttributeValue
import com.johnturkson.awstools.dynamodb.data.StringAttributeValue
import kotlinx.serialization.Serializable

@Serializable
data class UserData(
    val id: StringAttributeValue,
    val email: StringAttributeValue,
    val password: StringAttributeValue,
    val verified: BooleanAttributeValue
) {
    constructor(id: String, email: String, password: String, verified: Boolean) : this(
        StringAttributeValue(id),
        StringAttributeValue(email),
        StringAttributeValue(password),
        BooleanAttributeValue(verified)
    )
}
