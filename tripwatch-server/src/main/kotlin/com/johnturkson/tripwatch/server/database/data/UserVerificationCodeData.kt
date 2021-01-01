package com.johnturkson.tripwatch.server.database.data

import com.johnturkson.awstools.dynamodb.data.StringAttributeValue
import kotlinx.serialization.Serializable

@Serializable
data class UserVerificationCodeData(val id: StringAttributeValue, val code: StringAttributeValue) {
    constructor(id: String, code: String) : this(StringAttributeValue(id), StringAttributeValue(code))
}
