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
    @SerialName("ClientError")
    sealed class ClientError : Response() {
        @Serializable
        @SerialName("BadRequest")
        sealed class BadRequest : ClientError() {
            @Transient
            override val statusCode: Int = 400
            
            @Serializable
            @SerialName("InvalidRequestError")
            object InvalidRequestError : BadRequest()
            
            @Serializable
            @SerialName("InvalidEmailError")
            object InvalidEmailError : BadRequest()
            
            @Serializable
            @SerialName("EmailAlreadyTakenError")
            object EmailAlreadyTakenError : BadRequest()
            
            @Serializable
            @SerialName("PasswordTooShortError")
            object PasswordTooShortError : BadRequest()
    
            @Serializable
            @SerialName("PasswordTooLongError")
            object PasswordTooLongError : BadRequest()
            
            @Serializable
            @SerialName("CommonPasswordError")
            object CommonPasswordError : BadRequest()
            
            @Serializable
            @SerialName("BreachedPasswordError")
            object BreachedPasswordError : BadRequest()
        }
        
        @Serializable
        @SerialName("Unauthorized")
        sealed class Unauthorized : ClientError() {
            @Transient
            override val statusCode: Int = 401
            
        }
        
        @Serializable
        @SerialName("Forbidden")
        sealed class Forbidden : ClientError() {
            @Transient
            override val statusCode: Int = 403
            
        }
        
        @Serializable
        @SerialName("NotFound")
        sealed class NotFound : ClientError() {
            @Transient
            override val statusCode: Int = 404
            
        }
        
        @Serializable
        @SerialName("TooManyRequests")
        sealed class TooManyRequests : ClientError() {
            @Transient
            override val statusCode: Int = 429
            
        }
    }
    
    @Serializable
    @SerialName("ServerError")
    sealed class ServerError : Response() {
        @Serializable
        @SerialName("InternalServerError")
        object InternalServerError : ServerError() {
            @Transient
            override val statusCode: Int = 500
        }
    }
}
