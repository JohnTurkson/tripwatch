package com.johnturkson.tripwatch.common.responses

import com.johnturkson.tripwatch.common.data.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
@SerialName("Response")
sealed class Response {
    abstract val statusCode: Int
    
    @Serializable
    @SerialName("Success")
    sealed class Success : Response() {
        @Serializable
        @SerialName("OK")
        sealed class OK : Success() {
            @Transient
            override val statusCode: Int = 200
            
            @Serializable
            @SerialName("CreateUserResponse")
            data class CreateUserResponse(val user: User) : OK()
        }
    }
    
    @Serializable
    @SerialName("Error")
    sealed class Error : Response() {
        @Serializable
        @SerialName("BadRequest")
        sealed class BadRequest : Error() {
            @Transient
            override val statusCode: Int = 400
            
            @Serializable
            @SerialName("InvalidRequestError")
            object InvalidRequestError : BadRequest()
            
            @Serializable
            @SerialName("EmailAlreadyTakenError")
            data class EmailAlreadyTakenError(val email: String) : BadRequest()
            
            @Serializable
            @SerialName("UsernameAlreadyTakenError")
            data class UsernameAlreadyTakenError(val username: String) : BadRequest()
            
            @Serializable
            @SerialName("PasswordTooShortError")
            data class PasswordTooShortError(val password: String) : BadRequest()
            
            @Serializable
            @SerialName("BreachedPasswordError")
            data class BreachedPasswordError(val password: String) : BadRequest()
        }
        
        @Serializable
        @SerialName("Unauthorized")
        sealed class Unauthorized : Error() {
            @Transient
            override val statusCode: Int = 401
            
        }
        
        @Serializable
        @SerialName("Forbidden")
        sealed class Forbidden : Error() {
            @Transient
            override val statusCode: Int = 403
            
        }
        
        @Serializable
        @SerialName("NotFound")
        sealed class NotFound : Error() {
            @Transient
            override val statusCode: Int = 404
            
        }
        
        @Serializable
        @SerialName("TooManyRequests")
        sealed class TooManyRequests : Error() {
            @Transient
            override val statusCode: Int = 429
            
        }
    }
}
